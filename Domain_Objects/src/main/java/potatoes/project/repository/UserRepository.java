package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByNameIgnoreCaseContaining(String name);
	
	User findByName(String name);
	
    User findByUserID(int userID);
    
    User findByEmail(String email);
    
    //@Query("SELECT followed_users_userid FROM user_followed_users WHERE user_userid = ?1")
  	//List<Integer> findFollowedUsers(int userID);
}
