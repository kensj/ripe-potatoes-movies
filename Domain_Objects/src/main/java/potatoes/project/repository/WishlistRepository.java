package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	
	Wishlist findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	List<Content> findByUserUserID(int UserUserid);
	
	@Transactional
	void deleteByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
}
