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
import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
@Access(AccessType.PROPERTY)
public class PublicSession extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public PublicSession() {
		super();
	}

	// Identification ---------------------------------------------------------

	private Date startDate;
	private Date endDate;
	private Integer capacity;
	 private Blob photo;
	 private CommonsMultipartFile photoFile;
	private Integer essayNumber;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Min(1)
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public CommonsMultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(CommonsMultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	@Min(1)
	public Integer getEssayNumber() {
		return essayNumber;
	}

	public void setEssayNumber(Integer essayNumber) {
		this.essayNumber = essayNumber;
	}

	// Relaciones
	// ---------------------------------------------------------------

	private Organiser chairman;
	private Contest contest;
	private Collection<Essay> essays;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Organiser getChairman() {
		return chairman;
	}

	public void setChairman(Organiser chairman) {
		this.chairman = chairman;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	@Valid
	@OneToMany(mappedBy = "publicSession")
	public Collection<Essay> getEssays() {
		return essays;
	}

	public void setEssays(Collection<Essay> essays) {
		this.essays = essays;
	}

}
