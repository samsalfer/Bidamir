

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="dashboard.publicSessions"/>
<display:table name="publicSessions" id="row" pagesize="5" requestURI="administrator/dashboard3.do" class="displaytag">
	
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
	
	<spring:message code="publicSession.chairman" var="chairman" />
	<display:column title="${chairman}" property="chairman.name" />
	
	
	
</display:table>
<br />
