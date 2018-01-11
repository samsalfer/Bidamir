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

<security:authorize access="hasRole('ORGANISER')"> 

<form:form action="contest/organiser/edit.do" modelAttribute="contest">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="organisers"/>
	<form:hidden path="essays"/>
	<form:hidden path="name"/>
	<form:hidden path="description"/>
	<form:hidden path="holdingDate"/>
	<form:hidden path="deadlineDate"/>

	<br /><br />
	<security:authorize access="hasRole('ORGANISER')">
	<acme:textbox code="contest.result" path="result" />	
	
	<br /><br />
	
	
	
	<acme:submit name="save" code="contest.save"/>

	
	<input type="button" name="cancel" value="<spring:message code="contest.cancel"/>" 
	onclick="javascript: window.location.replace('contest/organiser/my-contests.do')"/>
	<br>
	</security:authorize>
	
</form:form>

</security:authorize>