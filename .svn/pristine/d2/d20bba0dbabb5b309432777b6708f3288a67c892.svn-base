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

<display:table name="essays" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<!-- Attributes -->
	
	<spring:message code="essay.title" var="title" />
	<display:column title="${title}" property="title" />
	
	<spring:message code="essay.submissionDate" var="submissionDate" />
	<display:column title="${submissionDate}" property="submissionDate" />
	
	<spring:message code="essay.contest.name" var="contest" />
	<display:column title="${contest}" property="contest.name" />
	
	<display:column>
			<a href="essay/notAuthenticated/display.do?essayId=${row.id}">
					<spring:message code="essay.display" />
			</a>
	</display:column>
	<security:authorize access="hasRole('AUTHOR')">
	<jstl:if test="${row.publicSession != null}">
			
			<spring:message code="essay.publicSession.startDate" var="publicSession" />
			<display:column title="${publicSession}" property="publicSession.startDate" />
			
			<spring:message code="essay.publicSession.endDate" var="publicSession" />
			<display:column title="${publicSession}" property="publicSession.endDate" />
			
	</jstl:if>	
	</security:authorize>
	
	<%-- 	Edit Essays	 --%>
	<security:authorize access="hasRole('AUTHOR')">
		<display:column>
		<jstl:if test="${now < row.contest.deadlineDate && row.isPublished == false}">
			<a href="essay/author/edit.do?essayId=${row.id}"><spring:message code="essay.edit" /></a>
		</jstl:if>	
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ORGANISER')">
		<display:column>
		<jstl:if test="${requestURI == 'publicSession/organiser/addEssay.do'}">
			<a href="publicSession/organiser/add.do?essayId=${row.id}&publicSessionId=${publicSession.id}"><spring:message code="essay.add" /></a>
		</jstl:if>	
		</display:column>
	</security:authorize>
	
</display:table>


