package potatoes.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.NotInterested;

public interface NotInterestedRepository extends JpaRepository<NotInterested, Integer>{
	
	NotInterested findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	@Transactional
	void deleteByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
}
