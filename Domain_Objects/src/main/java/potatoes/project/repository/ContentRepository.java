/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Content;

/**
 *
 * @author kdill
 */
public interface ContentRepository extends JpaRepository<Content, Integer>{

    List<Content> findByNameIgnoreCaseContaining(String name);
    
    Content findByContentID(int userID);
    
    boolean existsByContentID(int contentID);
       
}
