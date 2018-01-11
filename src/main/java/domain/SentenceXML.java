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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.*;

@XmlRootElement(name="sentence")
public class SentenceXML {

	private String id;
	private Collection<XcopeXML> xcopes = new ArrayList<XcopeXML>();
	private Collection<FactualXML> factuals = new ArrayList<FactualXML>();

	
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}


	public Collection<XcopeXML> getXcopes() {
		return xcopes;
	}

	@XmlElement(name="xcope")
	public void setXcopes(Collection<XcopeXML> xcopes) {
		this.xcopes = xcopes;
	}
	
	public Collection<FactualXML> getFactuals() {
		return factuals;
	}
	@XmlElement(name="factual")
	public void setFactuals(Collection<FactualXML> factuals) {
		this.factuals = factuals;
	}
	
	
	
	
	

}
