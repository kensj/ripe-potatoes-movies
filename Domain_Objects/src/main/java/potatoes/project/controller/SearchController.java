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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
		data.add(new TVSeries("Moana"));

	}
	
    @Autowired
    private HttpSession session;
    List<Content> data = new ArrayList<Content>();
    
    @RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<SearchResults> getContents(@RequestParam String search) {
    	SearchResults newResults = new SearchResults(getSearchResult(search));
		return new ResponseEntity<SearchResults>(newResults, HttpStatus.OK);
		//return getSearchResult(search);

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
    private List<Content> resultList;
    public SearchResults() {}
    public SearchResults(List<Content> content) {
        this.resultList = content;
    }
    public List<Content> getContent() {
    	return resultList;
    }
    public List<Content> setContent(List<Content> content) {
    	return this.resultList = content;
    }
}