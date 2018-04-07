/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.io.File;
import java.sql.Date; // extends java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author kdill
 */

@Entity
public abstract class Media extends Content{
    
	@ElementCollection
    protected List<String> cast;
    protected String synopsis;
    protected int year;
    protected double criticRating;
    
    @ElementCollection
    protected List<File> trailers;
    protected Date releaseDate;
    
    @OneToMany
    protected List<Genre> genres;
    
    protected Media() {
    	
    }
    
    protected Media(String name){
        super(name);
    }
}
