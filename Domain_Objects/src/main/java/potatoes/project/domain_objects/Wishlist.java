package potatoes.project.domain_objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Wishlist {
	
	@OneToOne
	Content content;
	
	@OneToOne
	User user;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	public Wishlist() {}
	
	public Wishlist(User user, Content content) {
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
