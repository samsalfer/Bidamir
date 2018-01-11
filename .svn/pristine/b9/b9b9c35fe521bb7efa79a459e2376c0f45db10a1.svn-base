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

	<security:authorize access="hasRole('ORGANISER')">

	<b><spring:message code="contest.name" />:</b>
	<jstl:out value="${contest.name}"></jstl:out>
	<br />
	
	<b><spring:message code="contest.description" />:</b>
	<jstl:out value="${contest.description}"></jstl:out>
	<br />
	
	<b><spring:message code="contest.holdingDate" />:</b>
	<jstl:out value="${contest.holdingDate}"></jstl:out>
	<br />
	
	<b><spring:message code="contest.deadlineDate" />:</b>
	<jstl:out value="${contest.deadlineDate}"></jstl:out>
	<br />
	
	<b><spring:message code="contest.result" />:</b>
	<jstl:out value="${contest.result}"></jstl:out>
	<br />
	
	<br />
	
	<input type="button" name="cancel" value="<spring:message code="contest.back.to"/>" 
	onclick="javascript: window.location.replace('contest/organiser/my-contests.do')"/>
	
	    <jstl:choose>
        <jstl:when test="${ canPutResult == true and contest.id != 0 }">
		<a href="contest/organiser/putResult.do?contestId=${contest.id}"><input type="button" 
					value="
				<spring:message code="contest.putResult" />">
			</a>
        </jstl:when>
        <jstl:otherwise>
        <br></br>
         <spring:message code="contest.nosepuedeputresult"  />
        </jstl:otherwise>
    </jstl:choose>
    
   
	
	<br />
	
	<jstl:if test="${ sepuedeeditar == true}">
	<a href="contest/organiser/edit.do?contestId=${contest.id}"><input type="button" 
					value="
				<spring:message code="contest.edit" />">
			</a>
	</jstl:if>

		</security:authorize>
	
