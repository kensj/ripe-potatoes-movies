package potatoes.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.Season;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.BlockRepository;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.FollowRepository;
import potatoes.project.repository.MessageRepository;
import potatoes.project.repository.NotInterestedRepository;
import potatoes.project.repository.RatingRepository;
import potatoes.project.repository.ReportRepository;
import potatoes.project.repository.ReviewRepository;
import potatoes.project.repository.UserRepository;
import potatoes.project.repository.VerificationTokenRepository;
import potatoes.project.repository.WishlistRepository;

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
	
	@PostMapping("/changePage")
	public ResponseEntity<?> changePageInfo(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam(value="synopsis",required=false) String synopsis, @RequestParam(value="cast[]",required=false) List<String> cast, @RequestParam("releaseDate") String releaseDate, @RequestParam(value="revenue",required=false) String revenue, @RequestParam(value="budget",required=false) String budget, @RequestParam(value="network",required=false) String network, @RequestParam(value="seasons[]",required=false) List<String> seasons) {
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
			java.sql.Date newDate = parseDate(releaseDate);
			if (newDate != null) {
				if (contentRepo.existsByContentID(id)) {
					Content toChange = contentRepo.findByContentID(id);
					if (toChange instanceof Film) {
						Film filmToChange = (Film) toChange;
						filmToChange.setName(name);
						filmToChange.setSynopsis(synopsis);
						filmToChange.setCast(cast);
						try {
							filmToChange.setBudget(Integer.parseInt(budget));
							filmToChange.setRevenue(Integer.parseInt(revenue));
						}
						catch (Exception e) {
							response.put("success", "false");
							response.put("reason", "number");
							return ResponseEntity.ok(response);
						}
						filmToChange.setReleaseDate(newDate);
						
						contentRepo.save(filmToChange);
						response.put("success", "true");
						return ResponseEntity.ok(response);
					}
					else if (toChange instanceof TVSeries) {
						TVSeries tvToChange = (TVSeries) toChange;
						tvToChange.setName(name);
						tvToChange.setSynopsis(synopsis);
						tvToChange.setCast(cast);
						tvToChange.setReleaseDate(newDate);
						tvToChange.setNetwork(network);
						List<Season> oldSeasons = tvToChange.getSeasons();
						if (oldSeasons.size() <= seasons.size()) {
							for(int i = 0; i < oldSeasons.size(); i++) {
								oldSeasons.get(i).setSynopsis(seasons.get(i));
							}
							int b = oldSeasons.size();
							for(int i = seasons.size() - b; i > 0; i--) {
								oldSeasons.add(new Season(seasons.get(seasons.size() - i)));
							}
						}
						else {
							int i = 0;
							for(;i < seasons.size(); i++) {
								oldSeasons.get(i).setSynopsis(seasons.get(i));
							}
							int j = oldSeasons.size();
							for(; i < j; i++) {
								oldSeasons.remove(oldSeasons.size() - 1);
							}
						}
						
						contentRepo.save(tvToChange);
						response.put("success","true");
						return ResponseEntity.ok(response);
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
			else {
				response.put("success", "false");
				response.put("reason", "date");
				return ResponseEntity.ok(response);
			}
		}
	}
	
	public java.sql.Date parseDate(String toParse) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date = format.parse(toParse);
			return new java.sql.Date(date.getTime());
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public void deleteUserExistence(User toDelete) {
		
		blockRepo.deleteAll(blockRepo.findByBlockerUserID(toDelete.getUserID()));
		blockRepo.deleteAll(blockRepo.findByBlockedUserID(toDelete.getUserID()));
		
		followRepo.deleteAll(followRepo.findByFollowerUserID(toDelete.getUserID()));
		followRepo.deleteAll(followRepo.findByFollowedUserID(toDelete.getUserID()));
		
		messageRepo.deleteAll(messageRepo.findBySenderUserID(toDelete.getUserID()));
		messageRepo.deleteAll(messageRepo.findByReceiverUserID(toDelete.getUserID()));
		
		ratingRepo.deleteAll(ratingRepo.findByRaterUserID(toDelete.getUserID()));
		
		revRepo.deleteAll(revRepo.findByAuthorUserID(toDelete.getUserID()));
		
		wishlistRepo.deleteAll(wishlistRepo.findByUserUserID(toDelete.getUserID()));
		
		notInterestedRepo.deleteAll(notInterestedRepo.findByUserUserID(toDelete.getUserID()));
		
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