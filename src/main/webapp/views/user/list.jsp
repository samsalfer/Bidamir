<%--
 * list.jsp
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

<!-- Listing grid -->

<display:table name="authors" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<!-- Attributes -->
	
	<spring:message code="author.name" var="name" />
	<display:column title="${name}" property="name" />
	
	<spring:message code="author.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="author.email" var="email" />
	<display:column title="${email}" property="email" />
	
	<spring:message code="author.phone" var="phone" />
	<display:column title="${phone}" property="phone" />
	
	<spring:message code="author.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" />
	
	<spring:message code="author.nacionality" var="nationality" />
	<display:column title="${nationality}" property="nationality" />
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
			<a href="author/display.do?authorId=${row.id}">
				<spring:message code="author.profile" />
			</a>
	</display:column>
	</security:authorize>
	
</display:table>

