/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.sql.Date;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author kdill
 */
public class Celebrity extends Content {
    
    private Date birthday;
    private String bio;
    //private Media bestRated;
    //private Media worstRated;
    private Image picture;
    
    private boolean isActor;
    private boolean isDirector;
    private boolean isWriter;
    private boolean isMisc;
    
    private List<Media> filmography;
    
    public Celebrity() {
    	
    }
    
    public Celebrity(String name){
        super(name);
    }
    
    public List<Media> getFilmography(){
    	return filmography;
    }
    
    public String getBio() {
    	return bio;
    }
    
    public Date getBirthday() {
    	return birthday;
    }
    
    public boolean isActor() {return isActor;}
    public boolean isDirector() {return isDirector;}
    public boolean isWriter() {return isWriter;}
    public boolean isMisc() {return isMisc;}
}
