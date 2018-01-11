

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message code="dashboard.presidents"/>
<display:table name="presidents" id="row" pagesize="5" requestURI="administrator/dashboard2.do" class="displaytag">
	
	<spring:message code="organiser.name" var="name" />
	<display:column title="${name}" property="name" />
	
	<spring:message code="organiser.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="organiser.email" var="email" />
	<display:column title="${email}" property="email" />
	
	<spring:message code="organiser.phone" var="phone" />
	<display:column title="${phone}" property="phone" />
	
	<spring:message code="organiser.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" />
	
	<spring:message code="organiser.nacionality" var="nationality" />
	<display:column title="${nationality}" property="nationality" />
	
	<spring:message code="organiser.toChair" var="toChair" />
	<display:column title="${toChair}"> ${fn:length(row.publicSessions)}</display:column>
	
	
</display:table>
<br />
