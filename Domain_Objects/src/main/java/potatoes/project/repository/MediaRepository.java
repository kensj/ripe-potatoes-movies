package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Media;

public interface MediaRepository extends JpaRepository<Media,Integer>{
	Media findByContentID(int contentID);
	
	@Query("select m from Media m where MONTH(m.releaseDate) = ?1 and YEAR(m.releaseDate) = ?2")
	List<Media> findByMonth(int month, int year);
	
	@Query("select m from Media m where YEARWEEK(m.releaseDate) = YEARWEEK(NOW())")
	List<Media> findByCurrentWeek();
	
	@Query(value="select media.*, film.box_office_revenue, content.name from media left join content on media.contentid = content.contentid left join film on media.contentid = film.contentid where MONTH(media.release_date) >= MONTH(NOW() - INTERVAL 2 MONTH) AND (YEAR(media.release_date) = YEAR(NOW()) OR YEAR(media.release_date) = YEAR(NOW() - INTERVAL 2 MONTH)) order by film.box_office_revenue desc limit 5", nativeQuery=true)
	List<Object[]> findTopBoxOffice();
}
