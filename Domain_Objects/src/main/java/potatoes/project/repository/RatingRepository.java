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
	
	@Query("select r.score from Rating r where r.score>=1 and r.score<2")
	List<Rating> findOneStar(int content);
	@Query("select r.score from Rating r where r.score>=2 and r.score<3")
	List<Rating> findTwoStar(int content);
	@Query("select r.score from Rating r where r.score>=3 and r.score<4")
	List<Rating> findThreeStar(int content);
	@Query("select r.score from Rating r where r.score>=4 and r.score<5")
	List<Rating> findFourStar(int content);
	@Query("select r.score from Rating r where r.score>=5 and r.score<6")
	List<Rating> findFiveStar(int content);
}
