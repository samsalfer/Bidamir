

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="dashboard.allContestOrderBySubmited"/>
<display:table name="allContestOrderBySubmited" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
	
	<spring:message code="contest.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="contest.description" var="description" />
	<display:column title="${description}" property="description" />

	<spring:message code="contest.holdingDate" var="holdingDate" />
	<display:column title="${holdingDate}" property="holdingDate" />

	<spring:message code="contest.deadlineDate" var="deadlineDate" />
	<display:column title="${deadlineDate}" property="deadlineDate" />
	
</display:table>
<br />
<spring:message code="dashboard.authorWithMoreEssays"/>
<display:table name="authorWithMoreEssays" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
	
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
	
</display:table>

<br />

<spring:message code="dashboard.authorWithMoreEssaysPublished"/>
<display:table name="authorWithMoreEssaysPublished" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
	
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
	
</display:table>

<spring:message code="dashboard.authorWithLessEssaysPublished"/>
<display:table name="authorWithLessEssaysPublished" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
	
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
	
</display:table>

<spring:message code="dashboard.avgEssaysByAuthor"/>
<jstl:out value="${avgEssaysByAuthor}"></jstl:out>

<br />

<spring:message code="dashboard.avgContestByOrganiser"/>
<jstl:out value="${avgContestByOrganiser}"></jstl:out>

<br />
<spring:message code="dashboard.contestDuringLastMonth"/>
<display:table name="contestDuringLastMonth" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
	
	<spring:message code="contest.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="contest.description" var="description" />
	<display:column title="${description}" property="description" />

	<spring:message code="contest.holdingDate" var="holdingDate" />
	<display:column title="${holdingDate}" property="holdingDate" />

	<spring:message code="contest.deadlineDate" var="deadlineDate" />
	<display:column title="${deadlineDate}" property="deadlineDate" />
	
</display:table>
