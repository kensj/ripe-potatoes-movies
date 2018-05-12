package potatoes.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Film;
import potatoes.project.domain_objects.Rating;
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ReportRepository;
import potatoes.project.repository.ReviewRepository;
import potatoes.project.repository.WishlistRepository;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.NotInterestedRepository;
import potatoes.project.repository.RatingRepository;

@RestController
public class AdminController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReportRepository repRepo;
	
	@Autowired
	private ReviewRepository revRepo;
	
	@Autowired
	private ContentRepository contentRepo;
	
	@Autowired
	private RatingRepository ratingRepo;
	
	@Autowired
	private WishlistRepository wishlistRepo;
	
	@Autowired
	private NotInterestedRepository notInterestedRepo;
	
	@RequestMapping("/admin")
	public ModelAndView adminPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		mav.addObject("reports",repRepo.findByResolvedOrderByReportDateAsc(false));
		return mav;
	}
	
	@PostMapping("/resolve/{id}")
	public ResponseEntity<?> resolveReport(@PathVariable int id) {
		Map<String, String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			response.put("success", "false");
			response.put("reason", "login");
			return ResponseEntity.ok(response);
		}
		else if (!u.isSuperUser()) {
			response.put("success", "false");
			response.put("reason", "permission");
			return ResponseEntity.ok(response);
		}
		else {
			Report toResolve = repRepo.findByReportID(id);
			if (toResolve != null) {
				toResolve.setResolved(true);
				repRepo.save(toResolve);
				response.put("success", "true");
				return ResponseEntity.ok(response);
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	@DeleteMapping("/delete/review")
	public ResponseEntity<?> adminDeleteReview(@RequestParam int reviewID) {
		Map<String, String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			response.put("success", "false");
			response.put("reason", "login");
			return ResponseEntity.ok(response);
		}
		else if (!u.isSuperUser()) {
			response.put("success", "false");
			response.put("reason", "permission");
			return ResponseEntity.ok(response);
		}
		else {
			Review toDelete = revRepo.findByReviewID(reviewID);
			if (toDelete != null) {
				revRepo.delete(toDelete);
				response.put("success", "true");
				return ResponseEntity.ok(response);
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	@PostMapping("/createPage")
	public ResponseEntity<?> adminCreatePage(@RequestParam String type, @RequestParam String title, @RequestParam int id) {
		Map<String, String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			response.put("success", "false");
			response.put("reason", "login");
			return ResponseEntity.ok(response);
		}
		else if (!u.isSuperUser()) {
			response.put("success", "false");
			response.put("reason", "permission");
			return ResponseEntity.ok(response);
		}
		else {
			if (type.equals("film")) {
				Film toAdd = new Film(title);
				toAdd.setContentID(id);
				if (!contentRepo.existsByContentID(toAdd.getContentID())) {
					Film newHandle = contentRepo.save(toAdd);
					response.put("success", "true");
					response.put("result", "" + newHandle.getContentID());
					return ResponseEntity.ok(response);
				}
				else {
					response.put("success", "false");
					response.put("reason", "exist");
					return ResponseEntity.ok(response);
				}
			}
			else if (type.equals("tv")) {
				TVSeries toAdd = new TVSeries(title);
				toAdd.setContentID(id);
				if (!contentRepo.existsByContentID(toAdd.getContentID())) {
					TVSeries newHandle = contentRepo.save(toAdd);
					response.put("success","true");
					response.put("result", "" + newHandle.getContentID());
					return ResponseEntity.ok(response);
				}
				else {
					response.put("success", "false");
					response.put("reason","exist");
					return ResponseEntity.ok(response);
				}
			}
			else {
				response.put("success", "false");
				response.put("reason","type");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	@DeleteMapping("/deletePage")
	public ResponseEntity<?> adminDeletePage(@RequestParam int id) {
		Map<String, String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			response.put("success", "false");
			response.put("reason", "login");
			return ResponseEntity.ok(response);
		}
		else if (!u.isSuperUser()) {
			response.put("success", "false");
			response.put("reason", "permission");
			return ResponseEntity.ok(response);
		}
		else {
			if (contentRepo.existsByContentID(id)) {
				Content toDelete = contentRepo.findByContentID(id);
				ratingRepo.removeByContent(toDelete);
				revRepo.removeByContent(toDelete);
				wishlistRepo.removeByContent(toDelete);
				notInterestedRepo.removeByContent(toDelete);
				List<Content> deletedPage = contentRepo.removeByContentID(id);
				
				response.put("success", "true");
				response.put("title", deletedPage.get(0).getName());
				return ResponseEntity.ok(response);
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
}