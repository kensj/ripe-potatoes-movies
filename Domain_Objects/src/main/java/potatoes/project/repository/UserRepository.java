package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByNameIgnoreCaseContaining(String name);
	
	User findByName(String name);
	
    User findByUserID(int userID);
    
    User findByEmail(String email);
}
