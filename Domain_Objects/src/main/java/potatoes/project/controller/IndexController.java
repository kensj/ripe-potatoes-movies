package potatoes.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.User;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {
		System.out.println("in controller");
		ModelAndView mav = new ModelAndView();
		User test = new User("a", "b", "c");
		
		mav.setViewName("index");
		mav.addObject("user", test);
		return mav;
	}
	
	@RequestMapping("/movie")
	public String index() {
		return "movie";
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		System.out.println(((User)session.getAttribute("user")).getName());
		if (session.getAttribute("user") != null) {
			mav.addObject("user",(User) session.getAttribute("user"));
		}
		return mav;
	}
}
