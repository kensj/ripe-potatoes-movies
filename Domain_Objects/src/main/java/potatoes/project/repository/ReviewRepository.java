package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Review;
import potatoes.project.domain_objects.User;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review findByReviewID(int reviewID);
	
	Review findByAuthorUserIDAndContentContentID(int author, int content);
	
	List<Review> findByAuthorUserID(int AuthorUserID);
	List<Review> findByAuthor(User u);
	
	List<Review> findByContentContentID(int id);
	
	List<Review> findFirst5ByOrderByReviewDateDesc();
	
	List<Review> findByContent(Content c);
	
}
