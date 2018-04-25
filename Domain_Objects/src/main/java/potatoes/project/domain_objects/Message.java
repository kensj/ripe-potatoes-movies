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

/**
 *
 * @author kdill
 */
@Entity
public class Message {
    private boolean read;
    private String body;
    
    @OneToOne
    private User sender;
    @OneToOne
    private User receiver;
    
    @Temporal(TemporalType.DATE)
    private Date timeStamp;
    
    private boolean isReprimand;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageID;
    
    public Message() {
    	read=false;
    }
    
    public Message(User sender, User receiver, String body, boolean isReprimand) {
    	this.sender=sender;
    	this.receiver=receiver;
    	this.body=body;
    	this.isReprimand=isReprimand;
    	read=false;
    	timeStamp = new Date();
    }
    
    public void markAsRead(){
        read=true;
    }
    
    public void markAsUnread() {
    	read=false;
    }
    
    public String getBody() {
    	return body;
    }
    
    public User getSender() {
    	return sender;
    }
    
    public User getReceiver() {
    	return receiver;
    }
    
    public boolean isReprimand() {
    	return isReprimand;
    }
    
    public boolean isRead() {
    	return read;
    }
    
    public void setRead() {
    	read=true;
    }
    
    public void setUnread() {
    	read=false;
    }
}
