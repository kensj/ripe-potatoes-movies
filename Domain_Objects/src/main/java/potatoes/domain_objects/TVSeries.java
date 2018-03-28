/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.domain_objects;

import java.util.List;

/**
 *
 * @author kdill
 */
public class TVSeries extends Media{
    
    private String network;
    private List<Season> seasons;
    
    public TVSeries(String name){
        super(name);
    }
}
