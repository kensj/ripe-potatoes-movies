	package potatoes.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Block;
import potatoes.project.domain_objects.Follow;
import potatoes.project.domain_objects.Message;
import potatoes.project.domain_objects.NotInterested;
import potatoes.project.domain_objects.User;
import potatoes.project.domain_objects.Wishlist;
import potatoes.project.repository.BlockRepository;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.FollowRepository;
import potatoes.project.repository.MessageRepository;
import potatoes.project.repository.NotInterestedRepository;
import potatoes.project.repository.RatingRepository;
import potatoes.project.repository.ReviewRepository;
import potatoes.project.repository.WishlistRepository;
import potatoes.project.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private NotInterestedRepository notInterestedRepository;
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContentRepository contentRepo;
        
    @Autowired
    private HttpSession session;

	@PostMapping("/register")
	public ResponseEntity<?> registerRequest(@Valid @RequestBody User user, Errors errors, HttpSession session) {
		Map<String, String> response = new HashMap<String, String>();
		
		if (errors.hasErrors()) {
			response.put("error", "ajax register request failed");
			return ResponseEntity.badRequest().body(response);
        }
		
		
		if (userService.findByUsername(user.getName()) != null) {
			response.put("success", "false");
			response.put("reason", "username");
		} 
		else if (userService.findByEmail(user.getEmail()) != null) {
			response.put("success", "false");
			response.put("reason", "email");
		}
		else if (!user.getPassword().equals(user.getConfirmPassword())) {
			response.put("success", "false");
			response.put("reason", "password");
		}
		else {
			response.put("success", "true");
			userService.save(user);
			session.setAttribute("user", user);
		}

		return ResponseEntity.ok(response);
	}
        
    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable int id){
    	ModelAndView mav = new ModelAndView("profile");
    	User toGet = userService.findByUserID(id);
    	mav.addObject("user", toGet);
    	
    	mav.addObject("ratinglist", ratingRepository.findByRaterUserID(toGet.getUserID()));
    	mav.addObject("reviewlist", reviewRepository.findByAuthorUserID(toGet.getUserID()));
    	mav.addObject("wishlist", wishlistRepository.findByUserUserID(toGet.getUserID()));
    	mav.addObject("followlist", followRepository.findByFollowerUserID(toGet.getUserID()));
    	mav.addObject("followedlist", followRepository.findByFollowedUserID(toGet.getUserID()));
    	mav.addObject("nilist", notInterestedRepository.findByUserUserID(toGet.getUserID()));
    	
    	if(session.getAttribute("user") != null) {
    		User u = (User) session.getAttribute("user");
    		if(u.getUserID() != toGet.getUserID()) {
        		if(followRepository.findByFollowerUserIDAndFollowedUserID(u.getUserID(),toGet.getUserID()) != null) {
        			mav.addObject("following", true);
        		}
        		else {
        			mav.addObject("following", false);
        		}
        		
        		if(blockRepository.findByBlockerUserIDAndBlockedUserID(u.getUserID(),toGet.getUserID()) != null) {
        			mav.addObject("blocking", true);
        		}
        		else {
        			mav.addObject("blocking", false);
        		}
        	}
    		else {
    			mav.addObject("blocklist", blockRepository.findByBlockerUserID(u.getUserID()));
    		}    		
    	}
    	return mav;
    }
        
	@PostMapping("/login")
	public ResponseEntity<?> loginRequest(@Valid @RequestBody User user, Errors errors, HttpSession session) {
		Map<String,String> response = new HashMap<String, String>();
		
		if (errors.hasErrors()) {
			response.put("error", "ajax login request failed");
			return ResponseEntity.badRequest().body(response);
		}
		
		User targetUser = userService.findByUsername(user.getName());
		if (targetUser != null) {
			if (userService.authenticate(user.getName(), user.getPassword())) {
				response.put("success", "true");
				session.setAttribute("user", targetUser);
			}
			else {
				response.put("success", "false");
				response.put("reason", "password");
			}
		}
		else {
			response.put("success", "false");
			response.put("reason", "username");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logoutRequest(HttpSession session) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			session.invalidate();
		}
		catch (Exception e) {}
		
		response.put("success", "true");
		return ResponseEntity.ok(response);
	}
	/*
	@PostMapping("/update-password")
	public ResponseEntity<?> updatePassword(@RequestParam String oldPass, @RequestParam String newPass){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null || !userService.authenticate(u.getName(), oldPass)) {
			response.put("success", "false");
		} else {
			u.changePassword(newPass);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}*/
	
	@PostMapping("/update-email")
	public ResponseEntity<?> updatePassword(@RequestParam String password, @RequestParam String newEmail){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null || !userService.authenticate(u.getName(), password)) {
			response.put("success", "false");
		} else {
			u.setEmail(newEmail);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/wishlist/{contentID}")
	public ResponseEntity<?> addToWishlist(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			//u.addToWishlist((Media) contentRepo.findByContentID(contentID));
			if(wishlistRepository.findByUserUserIDAndContentContentID(u.getUserID(),contentRepo.findByContentID(contentID).getContentID()) == null) 
				wishlistRepository.save(new Wishlist(u,contentRepo.findByContentID(contentID)));	
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/unwishlist/{contentID}")
	public ResponseEntity<?> removeFromWishlist(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			Wishlist w = wishlistRepository.findByUserUserIDAndContentContentID(u.getUserID(),contentRepo.findByContentID(contentID).getContentID()); 
			if(w != null)
				wishlistRepository.delete(w);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/notinterested/{contentID}")
	public ResponseEntity<?> addToNotInterestedList(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			if(notInterestedRepository.findByUserUserIDAndContentContentID(u.getUserID(),contentRepo.findByContentID(contentID).getContentID()) == null) 
				notInterestedRepository.save(new NotInterested(u,contentRepo.findByContentID(contentID)));	
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/unnotinterested/{contentID}")
	public ResponseEntity<?> removeFromNotInterestedList(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			NotInterested ni = notInterestedRepository.findByUserUserIDAndContentContentID(u.getUserID(),contentRepo.findByContentID(contentID).getContentID());
			if(ni != null) 
				notInterestedRepository.delete(ni);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/follow/{userID}")
	public ResponseEntity<?> followUser(@PathVariable int userID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User f = (User) userService.findByUserID(userID);
		if (u == null || f == null) response.put("success", "false");
		else {
			if(followRepository.findByFollowerUserIDAndFollowedUserID(u.getUserID(),f.getUserID()) == null) 
				followRepository.save(new Follow(u,f));
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/unfollow/{userID}")
	public ResponseEntity<?> unfollowUser(@PathVariable int userID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User uf = (User) userService.findByUserID(userID);
		if (u == null || uf == null) response.put("success", "false");
		else {
			Follow f = followRepository.findByFollowerUserIDAndFollowedUserID(u.getUserID(),uf.getUserID());
			if(f != null) 
				followRepository.delete(f);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/block/{userID}")
	public ResponseEntity<?> blockUser(@PathVariable int userID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User b = (User) userService.findByUserID(userID);
		if (u == null || b == null) response.put("success", "false");
		else {
			if(blockRepository.findByBlockerUserIDAndBlockedUserID(u.getUserID(),b.getUserID()) == null) 
				blockRepository.save(new Block(u,b));			
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/unblock/{userID}")
	public ResponseEntity<?> unblockUser(@PathVariable int userID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User ub = (User) userService.findByUserID(userID);
		if (u == null || ub == null) response.put("success", "false");
		else {
			Follow f = followRepository.findByFollowerUserIDAndFollowedUserID(u.getUserID(),ub.getUserID());
			Block b = blockRepository.findByBlockerUserIDAndBlockedUserID(u.getUserID(),ub.getUserID());
			if(f != null) 
				followRepository.delete(f);
			if(b != null) 
				blockRepository.delete(b);
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping("/inbox")
	public ModelAndView inboxPage() {
		ModelAndView mav = new ModelAndView("inbox");
		User u = (User) session.getAttribute("user");
		if( u == null ) return mav;
		
		List<User> m = messageRepository.findUnique1(u);
		m.addAll(messageRepository.findUnique2(u));
		m.removeAll(Collections.singleton(u));
		m = m.stream().distinct().collect(Collectors.toList());
		
		Collections.sort(m, new Comparator<User>() {
		    @Override
		    public int compare(User u1, User u2) {
		        return u1.getName().compareToIgnoreCase(u2.getName());
		    }
		});
		
		List<List<Message>> convos = new ArrayList<List<Message>>();
		
		for(User s : m) {
			convos.add(messageRepository.findConvo(u,s));
		}
		mav.addObject("messageList", convos);
		return mav;
	}
	
	@PostMapping("/message/{userID}")
	public ResponseEntity<?> messageUser(@PathVariable int userID, @RequestParam String body){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User r = (User) userService.findByUserID(userID);
		if (u == null || r == null) response.put("success", "false");
		else {
			response.put("success", "true");
			messageRepository.save(new Message(u,r, body, false));
			System.out.println(body);
		}
		return ResponseEntity.ok(response);
	}
}
