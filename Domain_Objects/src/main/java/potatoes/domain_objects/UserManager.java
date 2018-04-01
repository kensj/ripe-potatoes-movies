/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.domain_objects;

import java.util.Hashtable;

/**
 *
 * @author kdill
 */
public class UserManager {
    private static Hashtable<Integer,User> user_cache;
    
    public static int addNewContent(User u){
        Integer key = 0;
        // add to dbase and add to hashtable using ID taken from dbase
        add(key,u);
        return key;
    }
    
    public static User getUser(int id){
        User c = user_cache.get(id);
        if (c == null){
            //retrieve from dbase and add to cache
        }
        return c;
    }
    
    // returns true if the key,value pairing was successfully added
    // returns false if the pairing was previously present
    private static boolean add(Integer key, User value){
        return user_cache.putIfAbsent(key, value) != null ? false : true;
    }
    
}
