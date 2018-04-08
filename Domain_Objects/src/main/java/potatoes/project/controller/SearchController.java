/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import potatoes.project.domain_objects.Celebrity;
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
		data.add(new Film("Moana"));
		data.add(new Film("Big Hero 6"));
		data.add(new Film("Logan"));
		data.add(new TVSeries("Fantastic Beasts"));
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
	ResponseEntity<?>/*List<Content>*/ getContents(@RequestParam String search) {
    	SearchResults newResults = new SearchResults(getSearchResult(search));
		return new ResponseEntity<SearchResults>(newResults, HttpStatus.OK);
		//return getSearchResult(search);
    	//return new ResponseEntity<List<Content>>(getSearchResult(search), HttpStatus.OK);

	}
    private List<Content> getSearchResult(String search) {

		List<Content> result = new ArrayList<Content>();
		for (Content content : data) {
			if (content.getName().contains(search)) {
				result.add(content);
				//System.out.println(content.getName());
			}
		}
		return result;
	}
}

class SearchResults {
    private List<Content> suggestions;
    public SearchResults() {}
    public SearchResults(List<Content> content) {
        this.suggestions = content;
    }
    public List<Content> getContent() {
    	return suggestions;
    }
    public List<Content> setContent(List<Content> content) {
    	return this.suggestions = content;
    }
}