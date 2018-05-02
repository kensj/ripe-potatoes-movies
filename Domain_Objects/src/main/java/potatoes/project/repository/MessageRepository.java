package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Message;
import potatoes.project.domain_objects.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query("select COALESCE(receiver, sender) as User from Message where receiver=?1 or sender=?1 group by sender,receiver order by timeStamp asc")
	List<User> findUnique(User r);
	
	@Query("select m from Message m where (m.sender=?1 or m.receiver=?1) and (m.sender=?2 or m.receiver=?2) order by timeStamp asc")
	List<Message> findConvo(User s, User r);

}
