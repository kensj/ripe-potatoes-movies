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
    private int numRating;
    private double sumRating;
    
    
    protected String name;
    @OneToMany
    protected List<Review> reviews;
    @ElementCollection
    protected List<byte[]> photos;
    @Id
    protected int contentID;
    
    protected Content(String name){
        isFeatured=false;
        numRating=0;
        sumRating=0.0;
        this.name=name;
        reviews=new ArrayList<>();
        photos=new ArrayList<>();
        ContentManager.addNewContent(this);
    }
    
    public double getRating(){return sumRating/numRating;}
    
    public void rate(int rating){
        numRating++;
        sumRating+=rating;
    }
    
    public void review(String justificationText, User author){
        reviews.add(new Review(justificationText, this, author));
    }
}
