/* DomainEntity.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Contest() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String name;
	private String description;
	private Date holdingDate;
	private Date deadlineDate;
	private String result;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getHoldingDate() {
		return holdingDate;
	}
	public void setHoldingDate(Date holdingDate) {
		this.holdingDate = holdingDate;
	}
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	//Relaciones--------------------------------------
	
	private Collection<Organiser> organisers;
	private Collection<Essay> essays;
	private Collection<PublicSession> publicSessions;
	
	
	@NotNull
	@Valid
	@ManyToMany
	public Collection<Organiser> getOrganisers() {
		return organisers;
	}
	public void setOrganisers(Collection<Organiser> organisers) {
		this.organisers = organisers;
	}
	
	@Valid
	@OneToMany(mappedBy = "contest")
	public Collection<Essay> getEssays() {
		return essays;
	}
	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}
	
    @Valid
    @OneToMany(mappedBy = "contest")
    public Collection<PublicSession> getPublicSessions() {
            return publicSessions;
    }

    public void setPublicSessions(Collection<PublicSession> publicSessions) {

            this.publicSessions = publicSessions;
    }
	
	
	
	
	// Equality ---------------------------------------------------------------

	

}
