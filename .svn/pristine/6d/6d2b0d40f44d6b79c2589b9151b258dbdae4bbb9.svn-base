package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = @Index(columnList = "name"))
public class Author extends Customer {

	// Constructors -----------------------------------------------------------

	public Author() {

	}

	// Attributes -------------------------------------------------------------

	

	// Relationships ----------------------------------------------------------
	
	private Collection<Essay>  essays;
	
	@Valid
	@OneToMany(mappedBy = "author")
	public Collection<Essay> getEssays() {
		return essays;
	}

	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}
	
	
	
}
