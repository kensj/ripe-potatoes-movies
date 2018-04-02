/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.List;
import javax.persistence.Id;
import javax.swing.ImageIcon;

/**
 *
 * @author kdill
 */
public class User {
    
    private String name;
    private String email;
    private String password;
    
    private boolean isCritic;
    private boolean isSuperUser;
    
    private int numFollowers;
    private Rank userRank;
    private ImageIcon icon;
    
    private List<Media> wishList;
    private List<Media> notInterestedList;
    private List<User> blockedUsers;
    private List<User> followedUsers;
    private int reprimands;
    
    @Id
    private int userID;
    
    public User (String name, String email, String password){
        this.name=name;
        this.email=email;
        PasswordAuthentication auth = new PasswordAuthentication();
        this.password = auth.hash(password.toCharArray());
    }
}
