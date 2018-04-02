/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

/**
 *
 * @author kdill
 */
public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    HORROR("Horror"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    DOCUMENTARY("Documentary")
    ;
    
    //reference: https://en.wikipedia.org/wiki/List_of_genres
    
    private final String text;
    
    Genre(String txt){text=txt;}
    
    @Override
    public String toString(){
        return text;
    }
}
