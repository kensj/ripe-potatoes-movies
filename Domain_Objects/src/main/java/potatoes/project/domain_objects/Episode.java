/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author kdill
 */
@Entity
public class Episode {
	@Id
    private int seasonID;
    private String synopsis;
    private int episodeNum;
    
    public String getSynopsis() {return synopsis;}
    public int getEpisodeNum() { return episodeNum;}
    public int getSeasonID() {return seasonID;}
}
