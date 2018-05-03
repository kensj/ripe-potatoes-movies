package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Block;

public interface BlockRepository extends JpaRepository<Block, Integer>{
	
	Block findByBlockerUserIDAndBlockedUserID(int blockerUserid, int blockedUserid);
	
	List<Block> findByBlockerUserID(int blockerUserid);
	
}
