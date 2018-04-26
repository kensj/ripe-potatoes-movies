package potatoes.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Media;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ContentRepository;
import potatoes.project.service.UserService;

@RestController
public class UserController {
    
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
    @GetMapping("/users/{id}")
    public ModelAndView getUser(@PathVariable int id, Model model){
    	ModelAndView mav = new ModelAndView();
    	User toGet = userService.findByUserID(id);
    	mav.setViewName("profile");
    	mav.addObject("user", toGet);
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
	
	@PostMapping("/content/{id}/add-to-wishlist")
	public ResponseEntity<?> addToWishlist(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			u.addToWishlist((Media) contentRepo.findByContentID(contentID));
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/content/{id}/remove-from-wishlist")
	public ResponseEntity<?> removeFromWishlist(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			u.removeFromWishlist((Media) contentRepo.findByContentID(contentID));
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/content/{id}/add-to-not-interested-list")
	public ResponseEntity<?> addToNotInterestedList(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			u.addToNotInterestedList((Media) contentRepo.findByContentID(contentID));
			response.put("success", "true");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/content/{id}/remove-from--not-interested-list")
	public ResponseEntity<?> removeFromNotInterestedList(@PathVariable int contentID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		
		if (u == null) {
			response.put("success", "false");
		} else {
			u.removeFromNotInterestedList((Media) contentRepo.findByContentID(contentID));
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
		else response.put("success", "true");	
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/unfollow/{userID}")
	public ResponseEntity<?> unfollowUser(@PathVariable int userID){
		Map<String,String> response = new HashMap<>();
		User u = (User) session.getAttribute("user");
		User f = (User) userService.findByUserID(userID);
		if (u == null || f == null) response.put("success", "false");
		else response.put("success", "true");	
		return ResponseEntity.ok(response);
	}
}
