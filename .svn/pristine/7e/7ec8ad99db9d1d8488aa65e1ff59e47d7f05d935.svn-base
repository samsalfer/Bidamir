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

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Essay extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Essay() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String title;
	private String summary;
	private Date submissionDate;
	private String content;
	private Boolean isPublished;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	@NotBlank
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
	
	
	
	
	//Relaciones--------------------------------------
	
	private Author author;
	private Contest contest;
	private PublicSession publicSession;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Contest getContest() {
		return contest;
	}
	public void setContest(Contest contest) {
		this.contest = contest;
	}
	
    @Valid
    @ManyToOne(optional=true)
    public PublicSession getPublicSession() {
            return publicSession;
    }

    public void setPublicSession(PublicSession publicSession) {
            this.publicSession = publicSession;
    }

	
	
	// Equality ---------------------------------------------------------------

	

}
