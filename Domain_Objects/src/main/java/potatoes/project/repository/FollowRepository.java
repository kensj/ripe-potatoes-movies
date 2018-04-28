package potatoes.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	Follow findByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
	@Transactional
	void deleteByFollowerUserIDAndFollowedUserID(int followerUserid, int followedUserid);
	
}
