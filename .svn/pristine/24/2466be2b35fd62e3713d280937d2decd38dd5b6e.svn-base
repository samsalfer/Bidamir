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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.*;

@XmlRootElement(name="sentencias")
public class ResumenXML {

	private String paciente;
	private String fechaFinal;
	private String fechaIngreso;
	private String tipoResumen;
	private Integer nSentencias;
	private Integer nPalabras;
	private Integer idDocumento;
	private Collection<SentenciaXML> sentencias;
	
	public String getPaciente() {
		return paciente;
	}
	@XmlAttribute
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	@XmlAttribute
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	@XmlAttribute
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getTipoResumen() {
		return tipoResumen;
	}
	@XmlAttribute
	public void setTipoResumen(String tipoResumen) {
		this.tipoResumen = tipoResumen;
	}
	public Integer getnSentencias() {
		return nSentencias;
	}
	@XmlAttribute
	public void setnSentencias(Integer nSentencias) {
		this.nSentencias = nSentencias;
	}
	public Integer getnPalabras() {
		return nPalabras;
	}
	@XmlAttribute
	public void setnPalabras(Integer nPalabras) {
		this.nPalabras = nPalabras;
	}
	public Integer getIdDocumento() {
		return idDocumento;
	}
	@XmlAttribute
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Collection<SentenciaXML> getSentencias() {
		return sentencias;
	}
	@XmlElement(name="sentencia")
	public void setSentencias(Collection<SentenciaXML> sentencias) {
		this.sentencias = sentencias;
	}
	
	
	

}
