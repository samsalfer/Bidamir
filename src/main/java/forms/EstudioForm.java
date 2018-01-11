package forms;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class EstudioForm {

	// Constructors -----------------------------------------------------------

	public EstudioForm() {
		super();
	}

	// Attributes--------------------------------------------------------------

	private int estudioId;
	private String title;
	private String tipe;
	private String path;
	private CommonsMultipartFile file;
	private String algoritmo;

		
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
			
	public int getEstudioId() {
		return estudioId;
	}
	public void setEstudioId(int estudioId) {
		this.estudioId = estudioId;
	}
	
	@NotNull
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getAlgoritmo() {
		return algoritmo;
	}
	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}
	
	
	
	
	
	//Relaciones--------------------------------------
	
	

	
	

}
