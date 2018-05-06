/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import potatoes.project.domain_objects.Media;
import potatoes.project.domain_objects.Rating;
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.MediaRepository;
import potatoes.project.repository.NotInterestedRepository;
import potatoes.project.repository.RatingRepository;
import potatoes.project.repository.ReportRepository;
import potatoes.project.repository.ReviewRepository;
import potatoes.project.repository.WishlistRepository;

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
    
    @Autowired 
    private MediaRepository medRepo;
    
    @Autowired
    private WishlistRepository wishlistRepo;
    
    @Autowired
    private NotInterestedRepository notIntRepo;
    
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
        Review r = revRepo.findByAuthorUserIDAndContentContentID(u.getUserID(),c.getContentID());
        
        if (r != null) {
        	r.setJustificationText(justificationText);
        	revRepo.save(r);
        }
        else revRepo.save(new Review(justificationText,c,u));
        
        c.setTotalReviews(revRepo.findByContentContentID(id).size());
    	contentRepo.save(c);
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/content/{id}/delete-review")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
    	Map<String, String> response = new HashMap<>();
        User u = (User) session.getAttribute("user");
        Content c = contentRepo.findByContentID(id);   
        Review r = revRepo.findByAuthorUserIDAndContentContentID(u.getUserID(),c.getContentID());
        if (r != null) {
        	revRepo.delete(r);
        	c.setTotalReviews(revRepo.findByContentContentID(id).size());
        	contentRepo.save(c);
        	response.put("success", "true");
        	return ResponseEntity.ok(response);
        }
        response.put("success", "false");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/content/{id}/add-rating")
    public ResponseEntity<?> addRating(@PathVariable int id, @RequestParam int rating){
    	
    	Map<String, String> response = new HashMap<>();
        User u = (User) session.getAttribute("user");
        
        if (u == null) {
        	response.put("success", "false");
        	response.put("reason", "login");
        	return ResponseEntity.ok(response);
        }
        
        if (rating < 1 || rating > 5){
            response.put("success", "false");
            response.put("reason", "invalid");
            return ResponseEntity.ok(response);
        }
        
        Content c = contentRepo.findByContentID(id);
        Rating r = rateRepo.findByRaterUserIDAndContentContentID(u.getUserID(), c.getContentID());
        if (r != null) {
        	r.setScore(rating);
        	rateRepo.save(r);
        }
        else rateRepo.save(new Rating(rating,c,u));

        c.setTotalRatings(rateRepo.findByContentContentID(id).size());
        c.setRating(rateRepo.getSumScore(c)/c.getTotalRatings());  
        contentRepo.save(c);
        
        response.put("success", "true");  
        return ResponseEntity.ok(response);
    }
    
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
        Rating r = rateRepo.findByRaterUserIDAndContentContentID(u.getUserID(), c.getContentID());
        
        if (r != null) {
        	rateRepo.delete(r);
        	
        	c.setTotalRatings(rateRepo.findByContentContentID(id).size());
            c.setRating(rateRepo.getSumScore(c)/c.getTotalRatings());  
            contentRepo.save(c);
        	
        	resp.put("success", "true");
        	return ResponseEntity.ok(resp);
    	}
        
        resp.put("success", "false");
        return ResponseEntity.ok(resp);
    }
    
    @PostMapping("/content/{id}/report")
    public ResponseEntity<?> reportReview(@PathVariable int id, @RequestParam int reviewID, @RequestParam String description) {
    	Map<String, String> response = new HashMap<String, String>();
        User u = (User) session.getAttribute("user");
        
        if (u == null) {
        	response.put("success", "false");
        	response.put("reason", "login");
        	return ResponseEntity.ok(response);
        }
        
        Report report = reportRepo.findByReporterUserIDAndContextReviewID(u.getUserID(),reviewID);
        if(report!=null) {
        	response.put("success","false");
    		response.put("reason", "repeat");
    		return ResponseEntity.ok(response);
        }
        reportRepo.save(new Report(description, u, revRepo.findByReviewID(reviewID)));
        System.out.println(revRepo.findByReviewID(reviewID).getJustificationText());
        System.out.println("REPORT HERE!!!!!!! " + reviewID);
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/content/{id}")
    public ModelAndView getContent(@PathVariable int id){
        ModelAndView mav = new ModelAndView();
    	Content toGet = contentRepo.findByContentID(id);
    	
        if (toGet instanceof Film){
            mav.setViewName("movie");
            mav.addObject("content", (Film) toGet);
        } else if (toGet instanceof TVSeries){
            mav.setViewName("tv");
            mav.addObject("content", (TVSeries) toGet);
        } else if (toGet instanceof Celebrity){
            mav.setViewName("celebrity");
            mav.addObject("content", (Celebrity) toGet);
        }
        
        //--THIS IS TERRIBLY INEFFICIENT
        int total = rateRepo.findByContentContentID(id).size();
        int oneStar = 0;
        int twoStar = 0;
        int threeStar = 0;
        int fourStar = 0;
        int fiveStar = 0;
        if(total!=0) {
	        oneStar = (int)(((double)rateRepo.findOneStar(toGet).size()/total)*100.0);
	        twoStar = (int)(((double)rateRepo.findTwoStar(toGet).size()/total)*100.0);
	        threeStar = (int)(((double)rateRepo.findThreeStar(toGet).size()/total)*100.0);
	        fourStar = (int)(((double)rateRepo.findFourStar(toGet).size()/total)*100.0);
	        fiveStar = (int)(((double)rateRepo.findFiveStar(toGet).size()/total)*100.0);
        }
        mav.addObject("oneStar",oneStar);
        mav.addObject("twoStar",twoStar);
        mav.addObject("threeStar",threeStar);
        mav.addObject("fourStar",fourStar);
        mav.addObject("fiveStar",fiveStar);
        
        //------------------------------
        
        User u = (User) session.getAttribute("user");
        if(u != null) {
        	Review review = revRepo.findByAuthorUserIDAndContentContentID(u.getUserID(), id);
        	Rating rating = rateRepo.findByRaterUserIDAndContentContentID(u.getUserID(), id);
        	
        	if(review != null) {
        		mav.addObject("ownreview", review);
        	}
        	else mav.addObject("ownreview", null);
        	
        	if(rating != null) {
        		mav.addObject("ownrating", rating);
        	}
        	else mav.addObject("ownrating", null);
        	
        	if(wishlistRepo.findByUserUserIDAndContentContentID(u.getUserID(), toGet.getContentID()) != null) {
        		mav.addObject("wishlisting", true);
        	}
        	else mav.addObject("wishlisting", false);
        	
        	if(notIntRepo.findByUserUserIDAndContentContentID(u.getUserID(), toGet.getContentID()) != null) {
        		mav.addObject("notinteresteding", true);
        	}
        	else mav.addObject("notinteresteding", false);
        }
        
        List<Review> allReviews = revRepo.findByContentContentID(id);
    	mav.addObject("allReviews",allReviews);
    	
        return mav;
    }
    
    @GetMapping("/releaseCalendar")
    public ResponseEntity<?> getReleaseCalendar() {
    	Calendar c = Calendar.getInstance();
    	int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    	int month = c.get(Calendar.MONTH) + 1;
    	int year = c.get(Calendar.YEAR);
    	List<Media> mediaList = medRepo.findByMonth(month,year);
    	Map<String,String[]> response = new HashMap<String,String[]>();
    	for (Media m : mediaList) {
    		c.setTime(m.getReleaseDate());
    		String[] temp = new String[2];
    		temp[0] = "" + m.getContentID();
    		temp[1] = "" + c.get(Calendar.DAY_OF_MONTH);
    		response.put(m.getName(), temp);
    	}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
