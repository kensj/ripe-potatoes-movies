/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.domain_objects;

/**
 *
 * @author kdill
 */
public class Review {
    
    private String justificationText;
    private Content content;
    private User author;
    private int reviewID;
    
    public Review(String justificationText, Content content, User author){
        this.justificationText=justificationText;
        this.content=content;
        this.author=author;
    }
}
