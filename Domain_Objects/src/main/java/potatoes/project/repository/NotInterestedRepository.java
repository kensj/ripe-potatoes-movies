package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.NotInterested;

public interface NotInterestedRepository extends JpaRepository<NotInterested, Integer>{
	
	NotInterested findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	List<NotInterested> findByUserUserID(int UserUserid);
	
}
