package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Filmography;

public interface FilmographyRepository extends JpaRepository<Filmography ,Integer>{

	
	List<Filmography> findByCelebrityContentID(int celebrityID);
	
	List<Filmography> findByMediaContentID(int mediaID);
}
