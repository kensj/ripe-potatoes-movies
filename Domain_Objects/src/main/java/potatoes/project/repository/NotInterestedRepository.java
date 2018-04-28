package potatoes.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.NotInterested;
import potatoes.project.domain_objects.Wishlist;

public interface NotInterestedRepository extends JpaRepository<NotInterested, Integer>{
	
	Wishlist findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	@Transactional
	void deleteByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
}
