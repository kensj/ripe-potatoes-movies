package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	Follow findByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
	List<Follow> findByFollowerUserID(int followerUserid);
	List<Follow> findByFollowedUserID(int followedUserid);
	
	@Transactional
	void deleteByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
}
