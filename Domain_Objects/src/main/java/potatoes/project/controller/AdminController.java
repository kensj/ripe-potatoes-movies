package potatoes.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
	
	@RequestMapping("/admin")
	public ModelAndView adminPage() {
		return new ModelAndView("admin");
	}
	
}