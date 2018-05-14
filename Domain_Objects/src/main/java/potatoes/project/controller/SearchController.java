/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Celebrity;
import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Film;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.ContentRepository;
import potatoes.project.repository.UserRepository;

/**
 *
 * @author kjapra
 */
@RestController
public class SearchController {
    @Autowired
    private HttpSession session;
    
    @Autowired
    private ContentRepository contentRepo;
    @Autowired
    private UserRepository userRepo;
    
    // Used for Navbar
    @RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getContents(@RequestParam String search) {
    	SearchResults results = new SearchResults(getSearchResult(search));
		return new ResponseEntity<SearchResults>(results, HttpStatus.OK);
	}
    
    private List<SearchEntity> getSearchResult(String search) {
		List<SearchEntity> result = new ArrayList<>();
		List<Content> iterate = contentRepo.findByNameIgnoreCaseContaining(search);
		for (Content suggestions : iterate) {
			if (suggestions.getName().toLowerCase().contains(search.toLowerCase())) {
				result.add(new SearchEntity(suggestions.getName(),suggestions.getContentID()));
			}
		}
		return result;
	}
    
    // Used for Search Page
 /*   @RequestMapping(value = "/getSearchList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getSearches(@RequestParam String search) {
    	ModelAndView mav = new ModelAndView("search");
    	mav.addObject("resultList", contentRepo.findByNameIgnoreCaseContaining(search));
		return mav;
	}*/
    
    @RequestMapping(value = "/search", params = "searchBar", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView getSearchQuery(@RequestParam("searchBar") String searchBar) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("searchBar", searchBar);
		List<Content> content = contentRepo.findByNameIgnoreCaseContaining(searchBar);
		/*content.removeAll(null);
		for(Content c : content) {
			if(c instanceof Film) System.out.println("film");
			else if(c instanceof TVSeries) System.out.println("tv");
			else if(c instanceof Celebrity) System.out.println("celeb");
			//else if(c instanceof Media) System.out.println("media");
			else {
				System.out.println("Deleting content ID: "+c.getContentID());
				contentRepo.delete(c);
			}
		}*/
		mav.addObject("resultList", content);
		return mav;
	}
    
    // user search
    @RequestMapping(value = "/usersearch", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView getUserSearchQuery() {
		ModelAndView mav = new ModelAndView("usersearch");
		mav.addObject("searchBar", "");
		mav.addObject("resultList", new ArrayList<User>());
		return mav;
	}
    
    @RequestMapping(value = "/usersearch", params = "searchBar", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView getUserSearchQuery(@RequestParam("searchBar") String searchBar) {
		ModelAndView mav = new ModelAndView("usersearch");
		mav.addObject("searchBar", searchBar);
		mav.addObject("resultList", userRepo.findByNameIgnoreCaseContaining(searchBar));
		return mav;
	}
}

class SearchResults {
    private List<SearchEntity> suggestions;
    
    public SearchResults() {}
    
    public SearchResults(List<SearchEntity> suggestions) {
        this.suggestions = suggestions;
    }
    
    public List<SearchEntity> getSuggestions() {
    	return this.suggestions;
    }
}

class SearchEntity {
    private String value;
    private int data;
    
    public SearchEntity() {}
    
    public SearchEntity(String value, int data) {
        this.value = value;
        this.data = data;
    }
    
    public String getValue() {
    	return this.value;
    }
    
    public int getData() {
    	return this.data;
    }
}
