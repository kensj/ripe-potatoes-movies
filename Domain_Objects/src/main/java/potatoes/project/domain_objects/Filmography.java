package potatoes.project.domain_objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Filmography {

	@OneToOne
	Media media;
	@OneToOne
	Celebrity celebrity;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	public Filmography() {}
	
	public Filmography(Media media, Celebrity celebrity) {
		this.media=media;
		this.celebrity=celebrity;
	}
	public Media getMedia() {
		return media;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}
	
	
}
