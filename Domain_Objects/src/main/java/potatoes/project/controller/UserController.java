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

import potatoes.project.domain_objects.PasswordAuthentication;
import potatoes.project.domain_objects.User;
import potatoes.project.domain_objects.UserManager;
import potatoes.project.service.UserService;
import potatoes.project.validator.UserValidator;

@RestController
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
			response.put("error", "ajax request failed");
			return ResponseEntity.badRequest().body(response);
                }
		
		
		if (userService.findByUsername(user.getName()) != null) {
			response.put("success", "false");
                        
		} else {
                    
			response.put("success", "true");
			userService.save(user);
		}
		return ResponseEntity.ok(response);
	}
        
        @GetMapping("/users/{id}")
        public User getUser(@PathVariable int id){
            return UserManager.getUser(id);
        }
        
}
