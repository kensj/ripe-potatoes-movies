/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author kdill
 */

@Entity
public class TVSeries extends Media{
    

    private String network;
    @OneToMany
    private List<Season> seasons;
    private String photo;
    public TVSeries() {
    	
    }
    
    public TVSeries(String name){
        super(name);
    }
    
    public List<Season> getSeasons(){
    	return seasons;
    }
    public String getPhoto() {
    	return photo;
    }
    public String getNetwork() {
    	return network;
    }
    public void setNetwork(String newNetwork) {
    	network = newNetwork;
    }
    public void setSeasons(List<Season> newSeasons) {
    	seasons = newSeasons;
    }
}
