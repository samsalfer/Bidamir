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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;





@Entity
@Access(AccessType.PROPERTY)
public class Resumen extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Resumen() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String paciente;
	private Date fechaFinal;
	private Date fechaIngreso;
	private String tipoResumen;
	private Integer nSentencias;
	private Integer nPalabras;
	private Integer idDocumento;
	
	

	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getTipoResumen() {
		return tipoResumen;
	}
	public void setTipoResumen(String tipoResumen) {
		this.tipoResumen = tipoResumen;
	}
	public Integer getnSentencias() {
		return nSentencias;
	}
	public void setnSentencias(Integer nSentencias) {
		this.nSentencias = nSentencias;
	}
	public Integer getnPalabras() {
		return nPalabras;
	}
	public void setnPalabras(Integer nPalabras) {
		this.nPalabras = nPalabras;
	}
	public Integer getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	
	//Relaciones--------------------------------------
	


	private Collection<Sentencia> sentencias;
	private User user;

	@Valid
	@OneToMany(mappedBy = "resumen", cascade =  CascadeType.REMOVE)
	public Collection<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(Collection<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

		
	
	
	
	// Equality ---------------------------------------------------------------

	

}
