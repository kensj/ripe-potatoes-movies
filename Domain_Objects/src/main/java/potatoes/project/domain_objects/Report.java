/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author kdill
 */

@Entity
public class Report {
    
    private String description;
    @OneToOne
    private User reporter;
    @OneToOne
    private User reported;
    @Transient
    private Review context;
    
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    
    private boolean resolved;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reportID;
    
    public Report() {
    	
    }
    
    public Report(String description, User reporter, Review context){
        this.description=description;
        this.reporter=reporter;
        this.reported=context.getAuthor();
        this.resolved = false;
        this.reportDate = new Date();
    }

    public boolean getResolved() {
    	return resolved;
    }
    
    public void setResolved(boolean newResolved) {
    	resolved = newResolved;
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
        return reportID;
    }
    
}
