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

@XmlRootElement(name="DocumentSet")
public class DocumentSetXML {

	private Collection<DocumentXML> documentos;
	
	public Collection<DocumentXML> getDocumentos() {
		return documentos;
	}
	@XmlElement(name="Document")
	public void setDocumentos(Collection<DocumentXML> documentos) {
		this.documentos = documentos;
	}
	
	

}
