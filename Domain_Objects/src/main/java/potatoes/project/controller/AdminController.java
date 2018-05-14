package potatoes.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import potatoes.project.repository.UserRepository;
import potatoes.project.repository.VerificationTokenRepository;
import potatoes.project.repository.WishlistRepository;
import potatoes.project.repository.BlockRepository;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.FollowRepository;
import potatoes.project.repository.MessageRepository;
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
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private BlockRepository blockRepo;
	
	@Autowired
	private VerificationTokenRepository verRepo;
	
	@Autowired
	private FollowRepository followRepo;
	
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
				response.put("success", "true");
				response.put("title", toDelete.getName());
				
				deleteContentExistence(toDelete);
				return ResponseEntity.ok(response);
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> adminDeleteUser(@RequestParam int id) {
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
			if (userRepo.existsByUserID(id)) {
				User toDelete = userRepo.findByUserID(id);
				
				deleteUserExistence(toDelete);
				
				response.put("success", "true");
				response.put("name", toDelete.getName());
				return ResponseEntity.ok(response);
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	@GetMapping("/pageInfo")
	public ResponseEntity<?> fetchPageInfo(@RequestParam int id) {
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
				Content toReturn = contentRepo.findByContentID(id);
				if (toReturn instanceof Film) {
					return ResponseEntity.ok((Film) toReturn);
				}
				else if (toReturn instanceof TVSeries) {
					return ResponseEntity.ok((TVSeries) toReturn);
				}
				else {
					response.put("success", "false");
					return ResponseEntity.ok(response);
				}
			}
			else {
				response.put("success", "false");
				response.put("reason", "exist");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	public void deleteUserExistence(User toDelete) {
		
		blockRepo.deleteAll(blockRepo.findByBlocker(toDelete));
		blockRepo.deleteAll(blockRepo.findByBlocked(toDelete));
		
		followRepo.deleteAll(followRepo.findByFollower(toDelete));
		followRepo.deleteAll(followRepo.findByFollowed(toDelete));
		
		messageRepo.deleteAll(messageRepo.findBySender(toDelete));
		messageRepo.deleteAll(messageRepo.findByReceiver(toDelete));
		
		ratingRepo.deleteAll(ratingRepo.findByRater(toDelete));
		
		revRepo.deleteAll(revRepo.findByAuthor(toDelete));
		
		wishlistRepo.deleteAll(wishlistRepo.findByUser(toDelete));
		
		notInterestedRepo.deleteAll(notInterestedRepo.findByUser(toDelete));
		
		verRepo.deleteAll(verRepo.findByUserList(toDelete));
		
		repRepo.deleteAll(repRepo.findByReporter(toDelete));
		repRepo.deleteAll(repRepo.findByReported(toDelete));
		
		userRepo.delete(toDelete);
	}
	
	public void deleteContentExistence(Content toDelete) {
		ratingRepo.deleteAll(ratingRepo.findByContent(toDelete));
		revRepo.deleteAll(revRepo.findByContent(toDelete));
		wishlistRepo.deleteAll(wishlistRepo.findByContent(toDelete));
		notInterestedRepo.deleteAll(notInterestedRepo.findByContent(toDelete));
		
		contentRepo.delete(toDelete);
	}
}