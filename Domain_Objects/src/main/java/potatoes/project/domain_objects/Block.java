package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Block {
	
	@OneToOne
	User blocker;
	
	@OneToOne
	User blocked;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	public Block() {}
	
	public Block(User blocker, User blocked) {
		this.blocker = blocker;
		this.blocked = blocked;
	}
	
	public User getBlocker() {
		return this.blocker;
	}
	
	public User getBlocked() {
		return this.blocked;
	}
}
