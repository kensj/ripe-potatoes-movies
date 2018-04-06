/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.ContentManager;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.User;

/**
 *
 * @author kdill
 */
@RestController
public class ContentController {
    
    @Autowired
    private HttpSession session;
    
    /*
    * @args: 
    */
    @PostMapping("/content/{id}/add-review")
    public void postReview(@PathVariable int id, @RequestParam String justificationText){
        User u = (User) session.getAttribute("User");
        Content c = ContentManager.getContent(id);
        c.review(justificationText, u);
    }
    
    @PostMapping("/content/{id}/edit-review")
    public void editReview(@PathVariable int id, @RequestParam String justificationText){
        User u = (User) session.getAttribute("User");
        Content c = ContentManager.getContent(id);
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                r.setJustificationText(justificationText);
                return;
            }
        }
    }
    
    @DeleteMapping("/content/{id}/delete-review")
    public boolean deleteReview(@PathVariable int id){
        User u = (User) session.getAttribute("User");
        Content c = ContentManager.getContent(id);
        
        for (Review r : c.getReviews()){
            if (r.getAuthor().equals(u)){
                return c.getReviews().remove(r);
            }
        }
        return false;
    }
    
    @PostMapping("/content/{id}/rate")
    public boolean addRating(@PathVariable int id, @RequestParam int rating){
        User u = (User) session.getAttribute("User");
        Content c = ContentManager.getContent(id);
        
        if (rating < 1 || rating > 5)
            return false;
        c.rate(rating);
        return true;
    }
    
    
    @GetMapping("/content/{id}")
    public Content getContent(@PathVariable int id){
        return ContentManager.getContent(id);
    }
}
