package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Follow {
	
	@OneToOne
	User follower;
	
	@OneToOne
	User followed;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	public Follow() {}
	
	public Follow(User follower, User followed) {
		this.follower = follower;
		this.followed = followed;
	}
	
	public User getFollower() {
		return this.follower;
	}
	
	public User getFollowed() {
		return this.followed;
	}
}
