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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Estudio extends DomainEntity{

	// Constructors -----------------------------------------------------------

	public Estudio() {
		super();
	}

	// Identification ---------------------------------------------------------

	private String title;
	private Date creation_date;
	private String tipe;
	private String path;
	private String label;
	private Boolean finalizado;
	private ArrayList<String> excel;
	private ArrayList<String> atributosSeleccionados;
	private String path2;
	private String algoritmo;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
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
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}	
	

	public ArrayList<String> getExcel() {
		return excel;
	}
	public void setExcel(ArrayList<String> excel) {
		this.excel = excel;
	}
	
	
	public ArrayList<String> getAtributosSeleccionados() {
		return atributosSeleccionados;
	}
	public void setAtributosSeleccionados(ArrayList<String> atributosSeleccionados) {
		this.atributosSeleccionados = atributosSeleccionados;
	}
	
	public String getPath2() {
		return path2;
	}
	public void setPath2(String path2) {
		this.path2 = path2;
	}
	
	

	public String getAlgoritmo() {
		return algoritmo;
	}
	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}
	
	
		
	//Relaciones--------------------------------------
	
	private User user;
	private Collection<Resultado>  resultados;

	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	@Valid
	@OneToMany(mappedBy = "estudio", cascade=CascadeType.REMOVE )
	public Collection<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(Collection<Resultado> resultados) {
		this.resultados = resultados;
	}
	
	

		
	
	// Equality ---------------------------------------------------------------

	

}
