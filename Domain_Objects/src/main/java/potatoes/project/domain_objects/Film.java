/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Entity;

/**
 *
 * @author kdill
 */

@Entity
public class Film extends Media{
    
    private int boxOfficeRevenue;
    private int budget;
    private String photo;
    protected Film() {
    	
    }
    
    public Film(String name){
        super(name);
        boxOfficeRevenue=-1;
        budget=-1;
    }

	public int getBudget() {
		return budget;
	}
    
	public int getRevenue() {
		return boxOfficeRevenue;
	}
	public String getPhoto() {
		return photo;
	}
	public void setBudget(int newBudget) {
		budget = newBudget;
	}
	public void setRevenue(int newRevenue) {
		boxOfficeRevenue = newRevenue;
	}
}
