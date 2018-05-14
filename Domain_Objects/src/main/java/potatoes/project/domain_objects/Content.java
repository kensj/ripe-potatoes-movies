/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author kdill
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {
	
	protected double rating;
	protected int totalRatings;
	protected int totalReviews;
    
    private boolean isFeatured;
    
    protected String name;
 
    @Id
    protected int contentID;
    
    protected Content() {}
    
    protected Content(String name){
        isFeatured=false;
        this.name=name;
    }
    
    public String getName() {return this.name;}

    
    public int getContentID() {
    	return contentID;
    }

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setContentID(int contentID) {
		this.contentID = contentID;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}
	
	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}
	
	public int getTotalRatings() {
		return this.totalRatings;
	}
}
