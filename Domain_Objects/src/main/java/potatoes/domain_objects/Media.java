/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.domain_objects;

import java.io.File;
import java.sql.Date; // extends java.util.Date;
import java.util.List;

/**
 *
 * @author kdill
 */
public abstract class Media extends Content{
    
    protected List<String> cast;
    protected String synopsis;
    protected int year;
    protected double criticRating;
    protected List<File> trailers;
    protected Date releaseDate;
    protected List<Genre> genres;
    
    protected Media(String name){
        super(name);
    }
}
