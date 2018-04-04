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
    private int seasonId;
    private String synopsis;
    private int episodeNum;
}
