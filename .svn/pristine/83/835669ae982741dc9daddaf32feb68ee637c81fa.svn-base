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
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;




@Entity
@Access(AccessType.PROPERTY)
public class Sentencia extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Sentencia() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String texto;
	private String hojaEvolucion;
	private Integer idSentencia;

	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getHojaEvolucion() {
		return hojaEvolucion;
	}
	public void setHojaEvolucion(String hojaEvolucion) {
		this.hojaEvolucion = hojaEvolucion;
	}
	public Integer getIdSentencia() {
		return idSentencia;
	}
	public void setIdSentencia(Integer idSentencia) {
		this.idSentencia = idSentencia;
	}

	
	//Relaciones--------------------------------------
	




	private Resumen resumen;
	

	@NotNull
	@Valid
	@ManyToOne(optional=true)
	public Resumen getResumen() {
		return resumen;
	}
	public void setResumen(Resumen resumen) {
		this.resumen = resumen;
	}

		
	
	
	
	// Equality ---------------------------------------------------------------

	

}
