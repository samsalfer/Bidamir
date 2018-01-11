<%--
 * create.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/create.do" modelAttribute="registerAdministratorForm" >
	
	
	<acme:textbox code="userAccount.username" path="username" />
	<acme:password code="userAccount.password" path="password" />
	<acme:password code="userAccount.password.repeat" path="passwordVerificada" />
	<acme:textbox code="administrator.name" path="name" />
	<acme:textbox code="administrator.surname" path="surname" />
	<acme:textbox code="administrator.email" path="email" />
	<acme:textbox code="administrator.phone" path="phone" />
	<acme:textbox code="administrator.homePage" path="homePage" />

		
	<a href="privacy/lopd-lssi.do" target="_blank"><acme:checkbox code="terms.accept" path="condition"/></a>
	
	<br />		
	<br />	
	
	<acme:submit name="save" code="administrator.save"/>

	<input type="button" name="cancel" value="<spring:message code="administrator.cancel"/>" 
	onclick="javascript: window.location.replace('../AcmeEssay')"/>	
	
</form:form>