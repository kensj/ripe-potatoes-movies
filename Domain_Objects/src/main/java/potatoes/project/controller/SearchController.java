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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Film;
import potatoes.project.domain_objects.TVSeries;

/**
 *
 * @author kjapra
 */
@RestController
public class SearchController {
	
	/*
	 * THIS IS TEMPORARY TO ADD OBJECTS TO SIMULATE SEARCH
	 */
	SearchController() {
		Film Moana = new Film("Moana");
		Moana.setContentID(277834);
		data.add(Moana);
		data.add(new Film("Big Hero 6"));
		data.add(new Film("Logan"));
		data.add(new TVSeries("The Walking Dead"));
		data.add(new Film("Zootopia"));
		data.add(new Film("Robocop"));
		data.add(new Film("Early Man"));
		data.add(new TVSeries("Baywatch"));
		data.add(new Film("Suicide Squad"));
	}
	
    @Autowired
    private HttpSession session;
    List<Content> data = new ArrayList<Content>();
    
    @RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getContents(@RequestParam String search) {
    	SearchResults results = new SearchResults(getSearchResult(search));
		return new ResponseEntity<SearchResults>(results, HttpStatus.OK);

	}
    
    private List<SearchEntity> getSearchResult(String search) {
		List<SearchEntity> result = new ArrayList<>();
		for (Content suggestions : data) {
			if (suggestions.getName().contains(search)) {
				result.add(new SearchEntity(suggestions.getName(),suggestions.getContentID()));
			}
		}
		return result;
	}
    
    @RequestMapping(value = "/search", params = "searchBar", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView getSearchQuery(@RequestParam("searchBar") String searchBar) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("search");
		mav.addObject("searchBar", searchBar);
		return mav;
	}
    
    @RequestMapping(value = "/getSearchList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getSearches(@RequestParam String search) {
    	ModelAndView mav = new ModelAndView("search");
    	mav.addObject("resultList", getSearchPageResult(search));
		return mav;
	}
    private List<Content> getSearchPageResult(String search) {
		List<Content> result = new ArrayList<>();
		for (Content suggestions : data) {
			if (suggestions.getName().contains(search)) {
				result.add(suggestions);
			}
		}
		return result;
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
