package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review findByReviewID(int reviewID);
}
