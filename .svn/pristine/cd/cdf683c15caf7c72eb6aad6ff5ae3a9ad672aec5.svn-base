package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Access(AccessType.PROPERTY)
public class ImageForm {

	// Constructors -----------------------------------------------------------

	public ImageForm() {
		super();
	}

	// Attributes--------------------------------------------------------------

	private int publicSessionId;
	
	private CommonsMultipartFile photo;

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	public int getPublicSessionId() {
		return publicSessionId;
	}

	public void setPublicSessionId(int publicSessionId) {
		this.publicSessionId = publicSessionId;
	}



}
