package potatoes.project.domain_objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class NotInterested {
	
	@OneToOne
	Content content;
	
	@OneToOne
	User user;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	public NotInterested() {}
	
	public NotInterested(User user, Content content) {
		this.user = user;
		this.content = content;
	}

	public User getUser() {
		return this.user;
	}
	
	public Content getContent() {
		return this.content;
	}
}
