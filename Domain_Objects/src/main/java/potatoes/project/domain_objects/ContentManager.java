/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.Hashtable;

/**
 *
 * @author kdill
 */
public class ContentManager {
    private static Hashtable<Integer,Content> content_cache;
    
    public static int addNewContent(Content c){
        Integer key = 0;
        // add to dbase and add to hashtable using ID taken from dbase
        add(key,c);
        return key;
    }
    
    public static Content getContent(int id){
        Content c = content_cache.get(id);
        if (c == null){
            //retrieve from dbase and add to cache
        }
        return c;
    }
    
    // returns true if the key,value pairing was successfully added
    // returns false if the pairing was previously present (no dups)
    private static boolean add(Integer key, Content value){
        return content_cache.putIfAbsent(key, value) != null ? false : true;
    }
    
}
