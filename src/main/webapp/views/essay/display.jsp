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
	
	
	<b><spring:message code="essay.title" />:</b>
	<jstl:out value="${essay.title}"></jstl:out>
	<br />
	
	<b><spring:message code="essay.summary" />:</b>
	<jstl:out value="${essay.summary}"></jstl:out>
	<br />
	
	<b><spring:message code="essay.submissionDate" />:</b>
	<jstl:out value="${essay.submissionDate}"></jstl:out>
	<br />
	
	<b><spring:message code="essay.content" />:</b>
	<jstl:out value="${essay.content}"></jstl:out>
	<br />
	
	<b><spring:message code="essay.contest.name" />:</b>
	<jstl:out value="${essay.contest.name}"></jstl:out>
	<br />
	
	<b><spring:message code="essay.isPublished" />:</b>
	<jstl:out value="${essay.isPublished}"></jstl:out>
	<br />

	<security:authorize access="hasRole('ORGANISER')">
		<jstl:if test="${essay.isPublished==false}">
			<a href="essay/organiser/publish.do?essayId=${essay.id}">
				<spring:message code="essay.publish" />
			</a>
		</jstl:if>
	</security:authorize>
	
