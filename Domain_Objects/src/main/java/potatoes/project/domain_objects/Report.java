/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kdill
 */

public class Report {
    
    private String description;
    private User reporter;
    private User reported;
    private Review context;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public Report(String description, User reporter, Review context){
        this.description=description;
        this.reporter=reporter;
        this.reported=context.getAuthor();
    }

    public String getDescription() {
        return description;
    }

    public User getReporter() {
        return reporter;
    }

    public User getReported() {
        return reported;
    }

    public Review getContext() {
        return context;
    }

    public int getId() {
        return id;
    }
    
}
