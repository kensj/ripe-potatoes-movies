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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import potatoes.project.domain_objects.PasswordAuthentication;
import potatoes.project.domain_objects.User;
import potatoes.project.domain_objects.UserManager;
import potatoes.project.service.UserService;
import potatoes.project.validator.UserValidator;

@RestController
@SessionAttributes("user")
public class UserController {
    
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private PasswordAuthentication passwordAuthentication;
        
        @Autowired
        private HttpSession session;

	@PostMapping("/register")
	public ResponseEntity<?> registerRequest(@Valid @RequestBody User user, Errors errors) {
		Map<String, String> response = new HashMap<String, String>();
		
		if (errors.hasErrors()) {
			response.put("error", "ajax register request failed");
			return ResponseEntity.badRequest().body(response);
                }
		
		/*
		if (userService.findByUsername(user.getName()) != null) {
			response.put("success", "false");
<<<<<<< HEAD
                        
		} else {
                    
=======
			response.put("reason", "username");
		}
		else {
>>>>>>> e3de9d7d963494d4052fc14f3441843ba3d7d8e8
			response.put("success", "true");
			userService.save(user);
		}
*/
		return ResponseEntity.ok(response);
	}
        
        @GetMapping("/users/{id}")
        public User getUser(@PathVariable int id){
            return UserManager.getUser(id);
        }
        
	@PostMapping("/login")
	public ResponseEntity<?> loginRequest(@Valid @RequestBody User user, Errors errors, Model model) {
		Map<String,String> response = new HashMap<String, String>();
		
		if (errors.hasErrors()) {
			response.put("error", "ajax login request failed");
			return ResponseEntity.badRequest().body(response);
		}
		
		User targetUser = userService.findByUsername(user.getName());
		if (targetUser != null) {
			if (userService.authenticate(user.getName(), user.getPassword())) {
				response.put("success", "true");
				model.addAttribute("user", targetUser);
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
}
