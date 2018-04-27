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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kdill
 */
@Entity
public class Message {
	
	private boolean isReprimand;
    private boolean messageRead;
    private String body;
    
    @OneToOne
    private User sender;
    @OneToOne
    private User receiver;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageID;    
    @Temporal(TemporalType.DATE)
    private Date timeStamp;
    
    public Message() {
    	messageRead=false;
    }
    
    public Message(User sender, User receiver, String body, boolean isReprimand) {
    	this.sender=sender;
    	this.receiver=receiver;
    	this.body=body;
    	this.isReprimand=isReprimand;
    	messageRead=false;
    	timeStamp = new Date();
    }
    
    public void markAsRead(){
        messageRead=true;
    }
    
    public void markAsUnread() {
    	messageRead=false;
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
    	return messageRead;
    }
    
    public void setRead() {
    	messageRead=true;
    }
    
    public void setUnread() {
    	messageRead=false;
    }
}
