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
import org.springframework.ui.Model;
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
import potatoes.project.domain_objects.Rating;
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.ReportQueue;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.RatingRepository;
import potatoes.project.repository.ReportRepository;
import potatoes.project.repository.ReviewRepository;

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
    
    @Autowired
    private ReviewRepository revRepo;
    
    @Autowired
    private RatingRepository rateRepo;
    
    @Autowired
    private ReportRepository reportRepo;
    
    /*
    * @args: 
    */
    @PostMapping("/content/{id}/add-review")
    public ResponseEntity<?> postReview(@PathVariable int id, @RequestParam String justificationText){
    	Map<String, String> response = new HashMap<String, String>();
        User u = (User) session.getAttribute("user");
        if (u == null) {
        	response.put("success", "false");
        	response.put("reason", "login");
        	return ResponseEntity.ok(response);
        }
        Content c = contentRepo.findByContentID(id);
        for (Review x : c.getReviews()) {
        	if (x.getAuthor().equals(u)) {
        		c.editReview(justificationText, u);
        		contentRepo.save(c);
        		response.put("success", "true");
        		return ResponseEntity.ok(response);
        	}
        }
        c.review(justificationText, u);
        contentRepo.save(c);
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/content/{id}/edit-review")
    public void editReview(@PathVariable int id, @RequestParam String justificationText){
        User u = (User) session.getAttribute("user");
        Content c = contentRepo.findByContentID(id);
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                r.setJustificationText(justificationText);
                contentRepo.save(c);
                return;
            }
        }
        //write a new review in the case that there apparently wasn't an existing one
        c.review(justificationText, u);
        contentRepo.save(c);
    }
    
    //true return: boolean
    @DeleteMapping("/content/{id}/delete-review")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
        User u = (User) session.getAttribute("user");
        Content c = contentRepo.findByContentID(id);
        System.out.println(c.getReviews().size());
        Map<String, Boolean> resp = new HashMap<>();
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                resp.put("success", c.getReviews().remove(r));
                contentRepo.save(c);
                revRepo.delete(r);
                return ResponseEntity.ok(resp);
            }
        }
        resp.put("success", false);
        return ResponseEntity.ok(resp);
    }
    
    //true return: boolean
    @PostMapping("/content/{id}/add-rating")
    public ResponseEntity<?> addRating(@PathVariable int id, @RequestParam int rating){
        User u = (User) session.getAttribute("user");
        Map<String, String> resp = new HashMap<>();
        
        if (u == null) {
        	resp.put("success", "false");
        	resp.put("reason", "login");
        	return ResponseEntity.ok(resp);
        }
        
        if (rating < 1 || rating > 5){
            resp.put("success", "false");
            resp.put("reason", "invalid");
            return ResponseEntity.ok(resp);
        }
        
        Content c = contentRepo.findByContentID(id);
        for (Rating r : c.getRatings()) {
        	if (r.getRater().equals(u)) {
        		c.changeRating(rating, u);
        		contentRepo.save(c);
        		resp.put("success", "true");
        		return ResponseEntity.ok(resp);
        	}
        }
        
        c.addRating(rating, u);
        contentRepo.save(c);
        resp.put("success", "true");
        
        return ResponseEntity.ok(resp);
    }
    
    //true return: boolean
    @PostMapping("/content/{id}/change-rating")
    public ResponseEntity<?> changeRating(@PathVariable int id, @RequestParam int rating){
        User u = (User) session.getAttribute("user");
        Content c = contentRepo.findByContentID(id);
        Map<String, String> resp = new HashMap<>();
        
        if (rating < 1 || rating > 5){
            resp.put("success", "false");
            return ResponseEntity.ok(resp);
        }
        
        c.changeRating(rating, u);
        resp.put("success", "true");
        contentRepo.save(c);
        
        return ResponseEntity.ok(resp);
    }
    
    //true return: boolean
    @DeleteMapping("/content/{id}/delete-rating")
    public ResponseEntity<?> deleteRating(@PathVariable int id){
        User u = (User) session.getAttribute("user");
        Map<String, String> resp = new HashMap<>();
        
        if (u == null) {
        	resp.put("success", "false");
        	resp.put("reason", "login");
        	return ResponseEntity.ok(resp);
        }
        
        Content c = contentRepo.findByContentID(id);
        for (Rating r : c.getRatings()) {
        	if (r.getRater().equals(u)) {
        		if (c.removeRating(u)) {
        			resp.put("success", "true");
        			contentRepo.save(c);
        			rateRepo.delete(r);
        			return ResponseEntity.ok(resp);
        		}
        		else {
        			resp.put("success", "false");
        			return ResponseEntity.ok(resp);
        		}
        	}
        }
        
        resp.put("success", "false");
        return ResponseEntity.ok(resp);
    }
    
    @PostMapping("/content/{id}/report")
    public ResponseEntity<?> reportReview(@PathVariable int id, @RequestParam int reviewID, @RequestParam String description){
        User reporter = (User) session.getAttribute("user");
        Map<String, String> response = new HashMap<String, String>();
        if (reporter == null) {
        	response.put("success", "false");
        	response.put("reason", "login");
        	return ResponseEntity.ok(response);
        }
        
        for(Review r : contentRepo.findByContentID(id).getReviews()) {
            for (Report s : reportRepo.findByReporter(reporter)) {
            	if (s.getReported().equals(r.getAuthor())) {
            		response.put("success", "false");
            		response.put("reason", "repeat");
            		return ResponseEntity.ok(response);
            	}
            }        	
        	if (r.getReviewID() == reviewID) {
        		reportRepo.save(new Report(description, reporter, r));
        		response.put("success", "true");
        		return ResponseEntity.ok(response);
        	}
        }
        
        response.put("success", "false");
        response.put("reason", "unknown");
        return ResponseEntity.ok(response);
//        ReportQueue.queueReport(new Report(description, reporter, context));
    }
    
    //true return: Content object stored in ModelAndView, View name dependent on type of content
    @GetMapping("/content/{id}")
    public ModelAndView getContent(@PathVariable int id, Model model){
        ModelAndView mav = new ModelAndView();
    	Content toGet = contentRepo.findByContentID(id);
        if (toGet instanceof Film){
            mav.setViewName("movie");
            mav.addObject("content", (Film) toGet);
            System.out.println(toGet.getReviews().size());
        } else if (toGet instanceof TVSeries){
            mav.setViewName("tv");
            mav.addObject("content", (TVSeries) toGet);
        } else if (toGet instanceof Celebrity){
            mav.setViewName("celebrity");
            mav.addObject("content", (Celebrity) toGet);
        }
    	
        return mav;
    }
}
