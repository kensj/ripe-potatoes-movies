package potatoes.project.domain_objects;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VerificationToken {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private String token;
   
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestStamp;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireStamp;
    
    private boolean used;
    
    public VerificationToken() { }
    
    public VerificationToken(User user) {
    	this.user = user;
    	token = UUID.randomUUID().toString();
    	requestStamp = new Date(System.currentTimeMillis());
    	expireStamp = new Date(System.currentTimeMillis()+86400000);
    	used = false;
    }
    
    public String getToken() {
    	return this.token;
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public boolean isExpired() {
    	if(requestStamp.getTime() > expireStamp.getTime()) return true;
    	return false;
    }
    
    public void setExpired() {
    	expireStamp.setTime(0);
    }
    
    public void setUsed(boolean used) {
    	this.used = used;
    }
    
    public boolean isUsed() {
    	return this.used;
    }
}