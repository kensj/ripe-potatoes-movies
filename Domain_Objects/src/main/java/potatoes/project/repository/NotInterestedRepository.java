package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Content;
import potatoes.project.domain_objects.NotInterested;
import potatoes.project.domain_objects.User;

public interface NotInterestedRepository extends JpaRepository<NotInterested, Integer>{
	
	NotInterested findByUserUserIDAndContentContentID(int UserUserid, int ContentContentid);
	
	List<NotInterested> findByUserUserID(int UserUserid);
	List<NotInterested> findByUser(User u);
		
	List<NotInterested> findByContent(Content c);
	
}
