package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	Rating findByRatingID(int ratingID);
	
	List<Rating> findByRaterUserID(int RaterUserID);
	
	Rating findByRaterUserIDAndContentContentID(int rater, int content);
	
	List<Rating> findByContentContentID(int content);
	
	@Query("select sum(r.score) from Rating r where r.content=?1")
	Double getSumScore(Content c);
	
	@Query("select r.score from Rating r where r.score>=1 and r.score<1.5 and r.content=?1")
	List<Rating> findOneStar(Content c);
	@Query("select r.score from Rating r where r.score>=1.5 and r.score<2.5 and r.content=?1")
	List<Rating> findTwoStar(Content c);
	@Query("select r.score from Rating r where r.score>=2.5 and r.score<3.5 and r.content=?1")
	List<Rating> findThreeStar(Content c);
	@Query("select r.score from Rating r where r.score>=3.5 and r.score<4.5 and r.content=?1")
	List<Rating> findFourStar(Content c);
	@Query("select r.score from Rating r where r.score>=4.5 and r.score<=5 and r.content=?1")
	List<Rating> findFiveStar(Content c);
}
