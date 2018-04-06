/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    private int reviewID;
    
    public Review(String justificationText, Content content, User author){
        this.justificationText=justificationText;
        this.content=content;
        this.author=author;
    }

    public String getJustificationText() {
        return justificationText;
    }

    public void setJustificationText(String justificationText) {
        this.justificationText = justificationText;
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
