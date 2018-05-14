package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Message;
import potatoes.project.domain_objects.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query("select distinct m.sender as User from Message m where m.receiver=?1 or m.sender=?1")
	List<User> findUnique1(User r);
	
	@Query("select distinct m.receiver as User from Message m where m.sender=?1 or m.receiver=?1")
	List<User> findUnique2(User r);
	
	@Query("select m from Message m where (m.sender=?1 or m.receiver=?1) and (m.sender=?2 or m.receiver=?2) order by timeStamp asc")
	List<Message> findConvo(User s, User r);

	List<Message> findBySenderUserID(int senderUserID);
	List<Message> findByReceiverUserID(int receiverUserID);
	
	List<Message> findBySender(User u);
	List<Message> findByReceiver(User u);
}
