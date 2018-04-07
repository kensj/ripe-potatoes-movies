/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author kdill
 */
public class Rating {
    
    private int score;
    
    @OneToOne
    private final Content content;
    
    @OneToOne
    private final User rater;
    @Id
    private int ratingID;
    
    public Rating(int score, Content content, User rater){
        this.score=score;
        this.content=content;
        this.rater=rater;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Content getContent() {
        return content;
    }

    public User getRater() {
        return rater;
    }

    public int getRatingID() {
        return ratingID;
    }    
    
}