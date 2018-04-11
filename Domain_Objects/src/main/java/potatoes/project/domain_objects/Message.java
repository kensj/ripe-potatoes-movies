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
public class Message {
    private boolean read;
    private String body;
    private User from;
    private User to;
    private boolean isReprimand;
    
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
    	return from;
    }
    
    public User getReciever() {
    	return to;
    }
    
    public boolean isReprimand() {
    	return isReprimand;
    }
}
