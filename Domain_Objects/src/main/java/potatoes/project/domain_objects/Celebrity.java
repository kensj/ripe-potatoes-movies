/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import javafx.scene.image.Image;

/**
 *
 * @author kdill
 */
@Entity
public class Celebrity extends Content {
	
	@Temporal(TemporalType.DATE)
    private Date birthday;
	
    private String bio;
    private byte[] picture;
    
    private boolean isActor;
    private boolean isDirector;
    private boolean isWriter;
    private boolean isMisc;
    
    @OneToMany
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
