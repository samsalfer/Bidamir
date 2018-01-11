package forms;


import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ResumenForm {

	// Constructors -----------------------------------------------------------

	public ResumenForm() {
		super();
		texto = new ArrayList<String>();
		nombre = new ArrayList<String>();
	}

	// Attributes--------------------------------------------------------------

	private int resumenId;

	private CommonsMultipartFile file;
	private ArrayList<String> texto;
	private ArrayList<String> nombre;


		

	@NotNull
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public int getResumenId() {
		return resumenId;
	}
	public void setResumenId(int resumenId) {
		this.resumenId = resumenId;
	}
	public ArrayList<String> getTexto() {
		return texto;
	}
	public void setTexto(ArrayList<String> texto) {
		this.texto = texto;
	}
	public ArrayList<String> getNombre() {
		return nombre;
	}
	public void setNombre(ArrayList<String> nombre) {
		this.nombre = nombre;
	}

	
	
	
	
	
	
	//Relaciones--------------------------------------
	
	

	
	

}
