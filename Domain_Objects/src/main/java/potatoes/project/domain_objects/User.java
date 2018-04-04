/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.List;

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
    
    @OneToMany
    private List<Media> wishList;
    @OneToMany
    private List<Media> notInterestedList;
    @OneToMany
    private List<User> blockedUsers;
    @OneToMany
    private List<User> followedUsers;
    
    private int reprimands;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    
    public User() {
    	
    }
    
    @JsonCreator
    public User (@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("password") String password){
        this.name=name;
        this.email=email;
        PasswordAuthentication auth = new PasswordAuthentication();
        this.password = auth.hash(password.toCharArray());
    }
    
    public String getName() {
    	return name;
    }

	public String getPassword() {
		return password;
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
