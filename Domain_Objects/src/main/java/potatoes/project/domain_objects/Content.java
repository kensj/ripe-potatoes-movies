/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.HashSet;
//import javafx.scene.image.Image;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author kdill
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {
    
    private boolean isFeatured;
    
    private double sumRatings;
    
    
    protected String name;
    
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL)
    protected Map<Integer, Review> reviews;
    
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL)
    protected Map<Integer, Rating> ratings;
   
    protected String photos;
    @Id
    protected int contentID;
    
    protected Content() {
    	
    }
    
    protected Content(String name){
        isFeatured=false;
        sumRatings=0.0;
        this.name=name;
        reviews=new ConcurrentHashMap<Integer, Review>();
        ratings=new ConcurrentHashMap<Integer, Rating>();
        photos="";
    }
    
    public String getName() {return this.name;}

    public double getRating(){
        return ratings.isEmpty() ? -1 : sumRatings/ratings.size() ;
    }
    
    public Map<Integer, Rating> getRatings() {
    	return ratings;
    }
    
    public void addRating(int rating, User rater){
        ratings.put(rater.getUserID(), new Rating(rating, this, rater));
        sumRatings+=rating;
    }
    
    public void changeRating(int newRating, User rater){
//        for (Rating r : ratings){
//            if (r.getRater().equals(rater)){
//                sumRatings-=r.getScore();
//                sumRatings+=newRating;
//                r.setScore(newRating);
//                return;
//            }
//        }
//        for(Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
//        	Rating r = entry.getValue();
//        	if (r.getRater().equals(rater)) {
//        		sumRatings-=r.getScore();
//        		sumRatings+=newRating;
//        		r.setScore(newRating);
//        		return;
//        	}
//        }
    	Rating r = ratings.get(rater.getUserID());
    	if (r != null) {
    		sumRatings -= r.getScore();
    		sumRatings += newRating;
    		r.setScore(newRating);
    		return;
    	}
    }
    
    public boolean removeRating(User rater){
//        for (Rating r : ratings){
//            if (r.getRater().equals(rater)){
//                sumRatings-=r.getScore();
//                ratings.remove(r);
//                return true;
//            }
//        }
//        return false;
//    	for (Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
//    		Rating r = entry.getValue();
//    		if (r.getRater().equals(rater)) {
//    			sumRatings-=r.getScore();
//    			ratings.remove(r.getRater().getUserID());
//    			return true;
//    		}
//    	}
    	Rating r = ratings.get(rater.getUserID());
    	if (r != null) {
    		sumRatings -= r.getScore();
    		ratings.remove(r.getRater().getUserID());
    		return true;
    	}
    	return false;
    }
    
    public int getContentID() {
    	return contentID;
    }
    
    public void review(String justificationText, User author){
        reviews.put(author.getUserID(), new Review(justificationText, this, author));
    }

    public void editReview(String newText, User author) {
//    	for (Review r : reviews) {
//    		if (r.getAuthor().getUserID() == author.getUserID()) {
//    			r.setJustificationText(newText);
//    			return;
//    		}
//    	}
//    	for (Map.Entry<Integer, Review> entry : reviews.entrySet()) {
//    		Review r = entry.getValue();
//    		if (r.getAuthor().getUserID() == author.getUserID()) {
//    			r.setJustificationText(newText);
//    			return;
//    		}
//    	}
    	Review r = reviews.get(author.getUserID());
    	if (r != null) {
    		r.setJustificationText(newText);
    		return;
    	}
    }
    
    public boolean checkForRater(User user) {
//    	for (Rating r : ratings) {
//    		if (r.getRater().equals(user)) {
//    			return true;
//    		}
//    	}
//    	return false;
//    	for (Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
//    		Rating r = entry.getValue();
//    		if (r.getRater().equals(user)) {
//    			return true;
//    		}
//    	}
    	Rating r = ratings.get(user.getUserID());
    	if (r == null) {
    		return false;
    	}
    	else if (r.getRater().equals(user)) {
    		return true;
    	}
    	return false;
    }
    
    public Map<Integer,Review> getReviews() {
        return reviews;
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

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public void setReviews(Map<Integer,Review> reviews) {
		this.reviews = reviews;
	}

	public void setContentID(int contentID) {
		this.contentID = contentID;
	}
}
