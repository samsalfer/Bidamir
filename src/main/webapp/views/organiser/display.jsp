<%--
 * show.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	
	<b><spring:message code="organiser.name" />:</b>
	<jstl:out value="${organiser.name}"></jstl:out>
	<br />
	
	<b><spring:message code="organiser.surname" />:</b>
	<jstl:out value="${organiser.surname}"></jstl:out>
	<br />
	
	<b><spring:message code="organiser.email" />:</b>
	<jstl:out value="${organiser.email}"></jstl:out>
	<br />
	
	<b><spring:message code="organiser.phone" />:</b>
	<jstl:out value="${organiser.phone}"></jstl:out>
	
	<b><spring:message code="organiser.homePage" />:</b>
	<jstl:out value="${organiser.homePage}"></jstl:out>


	

	
	

	
