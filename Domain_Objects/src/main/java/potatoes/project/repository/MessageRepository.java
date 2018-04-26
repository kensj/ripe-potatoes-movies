package potatoes.project.repository;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.Message;
import potatoes.project.domain_objects.User;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	Message findByMessageID(int messageID);
	
	@OrderBy("timeStamp")
	List<Message> findByReceiver(User receiver);
}
