package potatoes.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Block;

public interface BlockRepository extends JpaRepository<Block, Integer>{
	
	Block findByBlockerUserIDAndBlockedUserID(int blockerUserid, int blockedUserid);
	
	@Transactional
	void deleteByBlockerUserIDAndBlockedUserID(int blockerUserid, int blockedUserid);
	
}
