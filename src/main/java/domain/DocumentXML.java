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

@XmlRootElement(name="Document")
public class DocumentXML {

	private String text;
	private String name;
	private SentenceSetXML sentenceSet;
	
	public String getName() {
		return name;
	}
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	@XmlElement(name="text")
	public void setText(String text) {
		this.text = text;
	}
	public SentenceSetXML getSentenceSet() {
		return sentenceSet;
	}
	
	@XmlElement(name="sentenceSet")
	public void setSentenceSet(SentenceSetXML sentenceSet) {
		this.sentenceSet = sentenceSet;
	}
	
	
	
	

	


	
	

}
