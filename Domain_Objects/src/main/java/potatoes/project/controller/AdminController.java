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

import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ReportRepository;
import potatoes.project.repository.ReviewRepository;

@RestController
public class AdminController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReportRepository repRepo;
	
	@Autowired
	private ReviewRepository revRepo;
	
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
}