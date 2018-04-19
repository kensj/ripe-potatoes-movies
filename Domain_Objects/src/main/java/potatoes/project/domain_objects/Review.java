/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author kdill
 */
@Entity
public class Review {
    
    private String justificationText;
    
    @OneToOne
    private Content content;
    
    @OneToOne
    private User author;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewID;
    
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    
    public Review() {
    	reviewDate = new Date();
    }
        
    public Review(String justificationText, Content content, User author){
        this.justificationText=justificationText;
        this.content=content;
        this.author=author;
        reviewDate = new Date();
    }

    public String getJustificationText() {
        return justificationText;
    }

    public void setJustificationText(String justificationText) {
        this.justificationText = justificationText;
        reviewDate = new Date();
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getReviewID() {
        return reviewID;
    }    
    
}
