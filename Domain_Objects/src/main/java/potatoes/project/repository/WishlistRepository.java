package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	
	Wishlist findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	List<Wishlist> findByUserUserID(int UserUserid);
	
}
