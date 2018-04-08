package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	Rating findByRatingID(int ratingID);
}
