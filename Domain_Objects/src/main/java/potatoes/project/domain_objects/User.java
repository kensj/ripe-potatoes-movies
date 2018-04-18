/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author kdill
 */
@Entity
public class User {
    
    private String name;
    private String email;
    private String password;
    
    @Transient
    private String confirmPassword;
    
    private boolean isCritic;
    private boolean isSuperUser;
    
    private int numFollowers;
    private Rank userRank;
    private ImageIcon icon;
    
    @ElementCollection
    @OneToMany
    private Map<Integer,Media> wishList;
    
    @ElementCollection
    @OneToMany
    private Map<Integer,Media> notInterestedList;
    
    @ElementCollection
    @OneToMany
    private Map<Integer,User> blockedUsers;
    
    @ElementCollection
    @OneToMany
    private Map<Integer,User> followedUsers;
    
    private int reprimands;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    
    public User() {
    	wishList = new HashMap<Integer,Media>();
    	notInterestedList = new HashMap<Integer,Media>();
    	blockedUsers = new HashMap<Integer,User>();
    	followedUsers = new HashMap<Integer,User>();
    }
    
    @JsonCreator
    public User (@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("password") String password){
        this.name=name;
        this.email=email;
        this.password=password;
        wishList = new HashMap<Integer,Media>();
    	notInterestedList = new HashMap<Integer,Media>();
    	blockedUsers = new HashMap<Integer,User>();
    	followedUsers = new HashMap<Integer,User>();
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof User))
            return false;
        return ((User) o).userID == this.userID;
    }
    
    @Override
    public int hashCode(){
        return (int)userID;
    }
    
    public String getName() {
    	return name;
    }

    public String getPassword() {
        return password;
    }
    
    public int getUserID() {
    	return userID;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isCritic() {
	return isCritic;
    }

    public void setCritic(boolean isCritic) {
    	this.isCritic = isCritic;
    }

    public boolean isSuperUser() {
    	return isSuperUser;
    }

    public void setSuperUser(boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    public ImageIcon getIcon() {
    	return icon;
    }

    public void setIcon(ImageIcon icon) {
    	this.icon = icon;
    }
	
    public String getConfirmPassword() {
    	return confirmPassword;
    }    
    
}
