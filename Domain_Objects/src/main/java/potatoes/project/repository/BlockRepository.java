package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Block;
import potatoes.project.domain_objects.User;

public interface BlockRepository extends JpaRepository<Block, Integer>{
	
	Block findByBlockerUserIDAndBlockedUserID(int blockerUserid, int blockedUserid);
	
	List<Block> findByBlockerUserID(int blockerUserid);
	
	@Transactional
	List<Block> removeByBlocker(User u);
	
	@Transactional
	List<Block> removeByBlocked(User u);
}
