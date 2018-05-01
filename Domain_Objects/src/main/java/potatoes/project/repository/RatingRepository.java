package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	Rating findByRatingID(int ratingID);
	
	List<Rating> findByRaterUserID(int RaterUserID);
}
