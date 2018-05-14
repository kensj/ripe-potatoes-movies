package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import potatoes.project.domain_objects.Follow;
import potatoes.project.domain_objects.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	Follow findByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
	List<Follow> findByFollowerUserID(int followerUserid); //following
	List<Follow> findByFollowedUserID(int followedUserid); //followers
	
	List<Follow> findByFollower(User u); //following
	List<Follow> findByFollowed(User u); //followers
	
}
