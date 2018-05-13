package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Follow;
import potatoes.project.domain_objects.User;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	Follow findByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
	List<Follow> findByFollowerUserID(int followerUserid);
	List<Follow> findByFollowedUserID(int followedUserid);
	
	@Transactional
	List<Follow> removeByFollower(User u);
	
	@Transactional
	List<Follow> removeByFollowed(User u);
}
