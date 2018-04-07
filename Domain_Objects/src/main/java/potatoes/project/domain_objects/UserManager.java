/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import potatoes.project.repository.UserRepository;

/**
 *
 * @author kdill
 */
public class UserManager {
    private static Hashtable<Integer,User> user_cache;
    
    @Autowired
    static UserRepository db;
    
    public static int addNewUser(User u){
        Integer key = (int)u.getUserID();
        db.save(u);
        add(key,u);
        return key;
    }
    
    public static User getUser(int id){
        User c = user_cache.get(id);
        if (c == null){
            db.findByUserID(id);
        }
        return c;
    }
    
    // returns true if the key,value pairing was successfully added
    // returns false if the pairing was previously present
    private static boolean add(Integer key, User value){
        return user_cache.putIfAbsent(key, value) != null ? false : true;
    }
    
}
