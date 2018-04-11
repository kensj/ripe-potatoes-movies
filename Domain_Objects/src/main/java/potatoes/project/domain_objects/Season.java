/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author kdill
 */
@Entity
public class Season {
    @OneToMany
    private List<Episode> episodes;
    @Id
    private int seasonID;
    private int contentID;
    private String synopsis;
    
    public Season() {
    	
    }
    
    public String getSynopsis() {
    	return synopsis;
    }
    
    public int getContentID() {
    	return contentID;
    }
    
    public int getseasonID() {
    	return seasonID;
    }
}
