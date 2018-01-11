package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = @Index(columnList = "name"))
public class Organiser extends Customer {

	// Constructors -----------------------------------------------------------

	public Organiser() {

	}

	// Attributes -------------------------------------------------------------


	// Relationships ----------------------------------------------------------
	
	private Collection<Contest> contests;
	private Collection<PublicSession> publicSessions;
	
	@Valid
	@ManyToMany(mappedBy = "organisers")
	public Collection<Contest> getContests() {
		return contests;
	}

	public void setContests(Collection<Contest> contests) {
		this.contests = contests;
	}
	
	  @Valid 
      @OneToMany(mappedBy = "chairman")
      public Collection<PublicSession> getPublicSessions() {
	
        return publicSessions;
	
      }

	public void setPublicSessions(Collection<PublicSession> publicSessions) {
		this.publicSessions = publicSessions;
	}
	

	
	

}
