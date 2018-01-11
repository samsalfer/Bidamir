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

<display:table name="publicSessions" id="row" pagesize="5" requestURI="${RequestURI}" class="displaytag">
	
	<!-- Attributes -->
	
	<spring:message code="publicSession.startDate" var="startDate" />
	<display:column title="${startDate}" property="startDate" />
	
	<spring:message code="publicSession.endDate" var="endDate" />
	<display:column title="${endDate}" property="endDate" />
	
	<spring:message code="publicSession.capacity" var="capacity" />
	<display:column title="${capacity}" property="capacity" />
	
	<spring:message code="publicSession.essayNumber" var="essayNumber" />
	<display:column title="${essayNumber}" property="essayNumber" />
	
	<spring:message code="publicSession.contest" var="contest" />
	<display:column title="${contest}" property="contest.name" />


	<security:authorize access="hasRole('ORGANISER')">
	<display:column>
		<a href="publicSession/organiser/display.do?publicSessionId=${row.id}">
			<spring:message code="publicSession.display" />
		</a>
	</display:column>
	</security:authorize>
	

</display:table>

	<security:authorize access="hasRole('ORGANISER')">
	<a href="publicSession/organiser/create.do">
					<spring:message code="publicSession.create" />
				</a>
	</security:authorize>
	
	


