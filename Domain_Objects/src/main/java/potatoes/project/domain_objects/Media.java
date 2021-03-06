/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.sql.Date; // extends java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author kdill
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media extends Content{
    
	@ElementCollection
    protected List<String> cast;
    protected String synopsis;
    protected double criticRating;
    
    protected Date releaseDate;
    
    protected Media() {
    	releaseDate = new Date(System.currentTimeMillis());
    }
    
    protected Media(String name){
        super(name);
        releaseDate = new Date(System.currentTimeMillis());
    }
    
    public String getSynopsis() {
    	return synopsis;
    }
    
    public void setSynopsis(String newSynopsis) {
    	synopsis = newSynopsis;
    }
    
    public List<String> getCast(){
    	return cast;
    }
    
    public void setCast(List<String> newCast) {
    	cast = newCast;
    }
    
    public Date getReleaseDate() {
    	return releaseDate;
    }
    
    public void setReleaseDate(Date newDate) {
    	releaseDate = newDate;
    }
}
