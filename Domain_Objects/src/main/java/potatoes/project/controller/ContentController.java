/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import potatoes.project.domain_objects.Celebrity;
import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Film;
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.ReportQueue;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ContentRepository;

/**
 *
 * @author kdill
 */
@RestController
public class ContentController {
    
    @Autowired
    private HttpSession session;
    
    @Autowired
    private ContentRepository contentRepo;
    
    /*
    * @args: 
    */
    @PostMapping("/content/{id}/add-review")
    public void postReview(@PathVariable int id, @RequestParam String justificationText){
        User u = (User) session.getAttribute("User");
        Content c = contentRepo.findByContentID(id);
        c.review(justificationText, u);
    }
    
    @PostMapping("/content/{id}/edit-review")
    public void editReview(@PathVariable int id, @RequestParam String justificationText){
        User u = (User) session.getAttribute("User");
        Content c = contentRepo.findByContentID(id);
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                r.setJustificationText(justificationText);
                return;
            }
        }
    }
    
    //true return: boolean
    @DeleteMapping("/content/{id}/delete-review")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
        User u = (User) session.getAttribute("User");
        Content c = contentRepo.findByContentID(id);
        Map<String, Boolean> resp = new HashMap<>();
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                resp.put("success", c.getReviews().remove(r));
                return ResponseEntity.ok(resp);
            }
        }
        resp.put("success", false);
        return ResponseEntity.ok(resp);
    }
    
    @PostMapping("/content/{id}/rate")
    public boolean addRating(@PathVariable int id, @RequestParam int rating){
        User u = (User) session.getAttribute("User");
        Content c = contentRepo.findByContentID(id);
        
        if (rating < 1 || rating > 5)
            return false;
        c.rate(rating);
        return true;
    }
    
    @PostMapping("/content/{id}/report")
    public boolean reportReview(@PathVariable int id, @RequestParam int reviewID, @RequestParam String description){
        User reporter = (User) session.getAttribute("User");
        Review context = contentRepo.findByContentID(id).getReviews().get(reviewID);
        
        ReportQueue.queueReport(new Report(description, reporter, context));
        return true;
    }
    
    @GetMapping("/content/{id}")
    public ModelAndView getContent(@PathVariable int id){
        ModelAndView mav = new ModelAndView();
    	Content toGet = contentRepo.findByContentID(id);
        if (toGet instanceof Film){
            mav.setViewName("movie");
        } else if (toGet instanceof TVSeries){
            mav.setViewName("tv");
        } else if (toGet instanceof Celebrity){
            mav.setViewName("celebrity");
        }
    	mav.addObject("content", toGet);
        return mav;
    }
}
