package potatoes.project.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

import potatoes.project.domain_objects.PasswordAuthentication;
import potatoes.project.domain_objects.User;
import potatoes.project.service.UserService;
import potatoes.project.validator.UserValidator;

@RestController
public class UserController {
    
	@Autowired
	private UserService userService;
        
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
    public ModelAndView getUser(@PathVariable long id, Model model){
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
}
