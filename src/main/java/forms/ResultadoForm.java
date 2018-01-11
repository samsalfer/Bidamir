package forms;


import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ResultadoForm {

	// Constructors -----------------------------------------------------------

	public ResultadoForm() {
		super();
		datosAPredecir = new String[100];
		
	}
	

	// Attributes--------------------------------------------------------------

	private int resultadoId;
	private int estudioId;
	
	private String path;
	private CommonsMultipartFile file;
	
	private String [] datosAPredecir;

	
	
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}	
	public int getResultadoId() {
		return resultadoId;
	}
	public void setResultadoId(int resultadoId) {
		this.resultadoId = resultadoId;
	}
	@NotNull
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	@NotNull
	public int getEstudioId() {
		return estudioId;
	}
	public void setEstudioId(int estudioId) {
		this.estudioId = estudioId;
	}
	public String[] getDatosAPredecir() {
		return datosAPredecir;
	}
	public void setDatosAPredecir(String[] datosAPredecir) {
		this.datosAPredecir = datosAPredecir;
	}

	
	
	

		
	
	
	
	

	
	

}
