package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);
	
    User findByUserID(int userID);
    
    User findByEmail(String email);
}
