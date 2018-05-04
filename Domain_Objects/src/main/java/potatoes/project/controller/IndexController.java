package potatoes.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.repository.MediaRepository;
import potatoes.project.repository.ReviewRepository;

@Controller
public class IndexController {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private ReviewRepository revRepo;
	
	@RequestMapping("/")
	public ModelAndView index() {
		System.out.println("in controller");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("currentWeek",mediaRepo.findByCurrentWeek());
//		for (Media x : mediaRepo.findByCurrentWeek()) {
//			System.out.println(x.getName());
//		}
		mav.addObject("topBox", mediaRepo.findTopBoxOffice());
//		for (Object[] x : mediaRepo.findTopBoxOffice()) {
//			for (Object y : x) {
//				System.out.println(y);
//			}
//		}
		mav.addObject("latestReviews", revRepo.findFirst5ByOrderByReviewDateDesc());
//		for (Review r : revRepo.findFirst5ByOrderByReviewDateDesc()) {
//			System.out.println(r.getJustificationText());
//		}
		
		return mav;
	}
	
	@RequestMapping("/movie")
	public String movie() {
		return "movie";
	}
	
	@RequestMapping("/about")
	public String aboutPage() {
		return "about";
	}

}
