package forms;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import domain.Contest;
import domain.Organiser;


public class PublicSessionForm {

	// Constructors -----------------------------------------------------------

	public PublicSessionForm() {
		super();
	}

	// Attributes--------------------------------------------------------------

	private int publicSessionId;
	private Organiser chairman;
	private Date startDate;
	private Date endDate;
	private Integer capacity;
	private CommonsMultipartFile photo;
	private Integer essayNumber;
	private Contest contest;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

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

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	@Min(1)
	public Integer getEssayNumber() {
		return essayNumber;
	}

	public void setEssayNumber(Integer essayNumber) {
		this.essayNumber = essayNumber;
	}

	public int getPublicSessionId() {
		return publicSessionId;
	}

	public void setPublicSessionId(int publicSessionId) {
		this.publicSessionId = publicSessionId;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Organiser getChairman() {
		return chairman;
	}

	public void setChairman(Organiser chairman) {
		this.chairman = chairman;
	}
	

}
