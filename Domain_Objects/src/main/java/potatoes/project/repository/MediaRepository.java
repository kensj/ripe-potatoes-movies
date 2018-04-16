package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Media;

public interface MediaRepository extends JpaRepository<Media,Integer>{
	Media findByContentID(int contentID);
	
	@Query("select m from Media m where MONTH(m.releaseDate) = ?1 and YEAR(m.releaseDate) = ?2")
	List<Media> findByMonth(int month, int year);
}
