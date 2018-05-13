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
    
    private Rank userRank;
    private ImageIcon icon;
    
    private int reprimands;
    
    private boolean verified;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    
    public User() {}
    
    @JsonCreator
    public User (@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("password") String password){
        this.name=name;
        this.email=email;
        this.password=password;
        this.verified=false;
        this.userRank = Rank.LEVEL0;
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
    
    public void setEmail(String email) {
    	this.email = email;
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
    
    public void updateRank(int numfollowers) {
    	if (numfollowers >= Rank.LEVEL8.getValue()) {
    		userRank = Rank.LEVEL8;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL7.getValue()) {
    		userRank = Rank.LEVEL7;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL6.getValue()) {
    		userRank = Rank.LEVEL6;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL5.getValue()) {
    		userRank = Rank.LEVEL5;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL4.getValue()) {
    		userRank = Rank.LEVEL4;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL3.getValue()) {
    		userRank = Rank.LEVEL3;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL2.getValue()) {
    		userRank = Rank.LEVEL2;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL1.getValue()) {
    		userRank = Rank.LEVEL1;
    		return;
    	}
    	if (numfollowers >= Rank.LEVEL0.getValue()) {
    		userRank = Rank.LEVEL0;
    	}
    }
    
    public Rank getRank() {
    	return userRank;
    }
    
    public boolean isVerified() {
    	return this.verified;
    }
    
    public void setVerified() {
    	this.verified = true;
    }
    
}
