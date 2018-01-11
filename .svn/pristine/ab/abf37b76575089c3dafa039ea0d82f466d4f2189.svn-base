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

<form:form action="author/create.do" modelAttribute="registerAuthorForm">

	<acme:textbox code="userAccount.username" path="username" />
	<acme:password code="userAccount.password" path="password" />
	<acme:password code="userAccount.password.repeat" path="passwordVerificada" />
	<acme:textbox code="author.name" path="name" />
	<acme:textbox code="author.surname" path="surname" />
	<acme:textbox code="author.email" path="email" />	
	<acme:textbox code="author.phone" path="phone" />
	<acme:textbox code="author.homePage" path="homePage" />
	<acme:textbox code="author.nacionality" path="nationality" />
	<acme:textbox code="author.birthDate" path="birthDate" /> <spring:message code="form.date"/>

	<h3><spring:message code="author.creditCard" /></h3>
	<acme:textbox code="author.creditcard.holdername" path="creditCard.holderName" />
	<acme:textbox code="author.creditcard.brandName" path="creditCard.brandName" />
	<acme:textbox code="author.creditcard.number" path="creditCard.number" />
	<acme:textbox code="author.creditcard.expirationMonth" path="creditCard.expirationMonth" />
	<acme:textbox code="author.creditcard.expirationYear" path="creditCard.expirationYear" />
	<acme:textbox code="author.creditcard.cvv" path="creditCard.cvv" />
	
	<br />
	<br />	
	<br />	
	
	<a href="privacy/lopd-lssi.do" target="_blank"><acme:checkbox code="terms.accept" path="condition"/></a>
	
	<br />		
	<br />
	
		<acme:submit name="save" code="author.save"/>

	
	<input type="button" name="cancel" value="<spring:message code="author.cancel"/>" 
	onclick="javascript: window.location.replace('../AcmeEssay')"/>	

</form:form>


