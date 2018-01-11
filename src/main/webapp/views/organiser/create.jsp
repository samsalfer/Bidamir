<%--
 * create.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="organiser/create.do" modelAttribute="registerOrganiserForm">

	<acme:textbox code="userAccount.username" path="username" />
	<acme:password code="userAccount.password" path="password" />
	<acme:password code="userAccount.password.repeat" path="passwordVerificada" />
	<acme:textbox code="organiser.name" path="name" />
	<acme:textbox code="organiser.surname" path="surname" />
	<acme:textbox code="organiser.email" path="email" />	
	<acme:textbox code="organiser.phone" path="phone" />
	<acme:textbox code="organiser.homePage" path="homePage" />
	<acme:textbox code="organiser.nacionality" path="nationality" />
	<acme:textbox code="organiser.birthDate" path="birthDate" /> <spring:message code="form.date"/>

	<h3><spring:message code="organiser.creditCard" /></h3>
	<acme:textbox code="organiser.creditcard.holdername" path="creditCard.holderName" />
	<acme:textbox code="organiser.creditcard.brandName" path="creditCard.brandName" />
	<acme:textbox code="organiser.creditcard.number" path="creditCard.number" />
	<acme:textbox code="organiser.creditcard.expirationMonth" path="creditCard.expirationMonth" />
	<acme:textbox code="organiser.creditcard.expirationYear" path="creditCard.expirationYear" />
	<acme:textbox code="organiser.creditcard.cvv" path="creditCard.cvv" />
	
	<br />
	<br />	
	<br />	
	
	<a href="privacy/lopd-lssi.do" target="_blank"><acme:checkbox code="terms.accept" path="condition"/></a>
	
	<br />		
	<br />
	
		<acme:submit name="save" code="organiser.save"/>

	
	<input type="button" name="cancel" value="<spring:message code="organiser.cancel"/>" 
	onclick="javascript: window.location.replace('../AcmeEssay')"/>	

</form:form>


