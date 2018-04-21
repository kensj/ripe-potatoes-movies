package potatoes.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Media;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.MediaRepository;
import potatoes.project.repository.ReviewRepository;

@Controller
public class IndexController {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private ReviewRepository revRepo;
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {
		System.out.println("in controller");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("currentWeek",mediaRepo.findByCurrentWeek());
		for (Media x : mediaRepo.findByCurrentWeek()) {
			System.out.println(x.getName());
		}
		mav.addObject("topBox", mediaRepo.findTopBoxOffice());
		for (Object[] x : mediaRepo.findTopBoxOffice()) {
			for (Object y : x) {
				System.out.println(y);
			}
		}
		mav.addObject("latestReviews", revRepo.findFirst5ByOrderByReviewDateDesc());
		for (Review r : revRepo.findFirst5ByOrderByReviewDateDesc()) {
			System.out.println(r.getJustificationText());
		}
		
		return mav;
	}
	
	@RequestMapping("/movie")
	public String index() {
		return "movie";
	}
	
	@RequestMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	/*@RequestMapping("/profile")
	public ModelAndView profile(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		System.out.println(((User)session.getAttribute("user")).getName());
		if (session.getAttribute("user") != null) {
			mav.addObject("user",(User) session.getAttribute("user"));
		}
		return mav;
	}*/
}
