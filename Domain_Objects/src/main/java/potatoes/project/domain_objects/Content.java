/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.ArrayList;
import java.util.List;
//import javafx.scene.image.Image;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
    
    @OneToMany
    protected List<Review> reviews;
    
    @OneToMany
    protected List<Rating> ratings;
    
    @ElementCollection
    protected List<byte[]> photos;
    @Id
    protected int contentID;
    
    protected Content() {
    	
    }
    
    protected Content(String name){
        isFeatured=false;
        sumRatings=0.0;
        this.name=name;
        reviews=new ArrayList<>();
        ratings=new ArrayList<>();
        photos=new ArrayList<>();
    }
    
<<<<<<< HEAD
    public String getName() {return this.name;}
    
    public void rate(int rating){
        numRating++;
        sumRating+=rating;
=======
    public double getRating(){
        return ratings.isEmpty() ? sumRatings/ratings.size() : -1;
    }
    
    public void addRating(int rating, User rater){
        ratings.add(new Rating(rating, this, rater));
        sumRatings+=rating;
    }
    
    public void changeRating(int newRating, User rater){
        for (Rating r : ratings){
            if (r.getRater().equals(rater)){
                sumRatings-=r.getScore();
                sumRatings+=newRating;
                r.setScore(newRating);
                return;
            }
        }
        addRating(newRating, rater);
    }
    
    public boolean removeRating(User rater){
        for (Rating r : ratings){
            if (r.getRater().equals(rater)){
                sumRatings-=r.getScore();
                ratings.remove(r);
                return true;
            }
        }
        return false;
>>>>>>> branch 'master' of https://kensj@bitbucket.org/KarlDill/ripe-potatoes.git
    }
    
    public int getContentID() {
    	return contentID;
    }
    
    public void review(String justificationText, User author){
        reviews.add(new Review(justificationText, this, author));
    }

    public List<Review> getReviews() {
        return reviews;
    }

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<byte[]> getPhotos() {
		return photos;
	}

	public void setPhotos(List<byte[]> photos) {
		this.photos = photos;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setContentID(int contentID) {
		this.contentID = contentID;
	}
}
