/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.ContentManager;
import potatoes.project.domain_objects.Film;
import potatoes.project.domain_objects.Media;
import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.ReportQueue;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.TVSeries;
import potatoes.project.domain_objects.User;
import org.springframework.ui.Model;

/**
 *
 * @author kdill
 */
@RestController
public class SearchController {
	
	/*
	 * THIS IS TEMPORARY TO ADD OBJECTS TO SIMULATE SEARCH
	 */
//	SearchController() {
//		data.add(new Film("Moana"));
//		data.add(new TVSeries("Game of Thrones"));
//		data.add(new Film("Harry Potter 1"));
//		data.add(new TVSeries("Game of Something"));
//		data.add(new Film("Harry Potter 2"));
//		data.add(new Film("Monsters Inc."));
//		data.add(new Film("Avengers 1"));
//		data.add(new Film("Avengers 2"));
//	}
    /*
     * DELETE ABOVE WHEN DONE WITH TESTING
     * 
     * RAY:
     * Use this on the HTML:
   
 
  $(document).ready(function() {

	$('#w-input-search').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getTags',
		paramName: "tagName",
		delimiter: ",",
	   transformResult: function(response) {
		    	
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
		            	
		      return { value: item.tagName, data: item.id };
		   })
		            
		 };
		        
            }
		    
	 });
				
  });
  

     * 
     */
    @Autowired
    private HttpSession session;
    List<Content> data = new ArrayList<Content>();
    
    @RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public @ResponseBody
	List<Content> getContents(@RequestParam String contentName) {

		return getSearchResult(contentName);

	}
    private List<Content> getSearchResult(String contentName) {

		List<Content> result = new ArrayList<Content>();
		for (Content content : data) {
			if (content.getName().contains(contentName)) {
				result.add(content);
			}
		}

		return result;
	}
    /*
    * @args: 
    */	
    /*@GetMapping("/")
    public String search(Model m) {
    	m.addAttribute("title", "autocomplete countries example");
    	return "autocomplete";
    }
    
    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public autocompleteWrapper autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
      System.out.println("searchstr: " + searchstr);
      
      // HERE WE NEED TO GET LIST OF CONTENT 
      ArrayList<Content> content = new ArrayList<>(); 

      int n = content.size() > 20 ? 20 : content.size();
      List<Content> sulb = new ArrayList<>(content.subList(0, n));

      autocompleteWrapper sw = new autocompleteWrapper();
      sw.setContent(sulb);
      return sw;
  }*/
}
/*
class searchManager {
	public List<String> search(String q) {
		List<Content> s = new ArrayList<>();
		Content c = new Content(q);
		s.add(c);
		return s;
	}
}

class autocompleteWrapper {
	List<Content> content;
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}
}*/