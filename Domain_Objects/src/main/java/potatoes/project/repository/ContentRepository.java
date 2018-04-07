/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potatoes.project.domain_objects.Content;

/**
 *
 * @author kdill
 */
public interface ContentRepository extends JpaRepository<Content, Integer>{
	Content findByName(String name);
        Content findByContentID(int userID);
}
