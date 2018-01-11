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

import java.util.ArrayList;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;




@Entity
@Access(AccessType.PROPERTY)
public class Resultado extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Resultado() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String path;
	private ArrayList<String> variables;
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	//Relaciones--------------------------------------
	


	private Estudio estudio;



	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	
	
	public ArrayList<String> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<String> variables) {
		this.variables = variables;
	}
		
	
	
	
	// Equality ---------------------------------------------------------------

	

}
