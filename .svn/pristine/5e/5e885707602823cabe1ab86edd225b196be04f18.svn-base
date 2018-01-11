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



	<img id="blah" src="publicSession/organiser/photo/publicSessionId.do?publicSessionId=${publicSession.id}" alt="your image" class="img-responsive" width="304" height="236" />
	<br/>
	<br/>
	
	<b><spring:message code="publicSession.chairman" />:</b>
	<jstl:out value="${publicSession.chairman.name}"></jstl:out>
	<br />
	
	<b><spring:message code="publicSession.startDate" />:</b>
	<jstl:out value="${publicSession.startDate}"></jstl:out>
	<br />
	
	<b><spring:message code="publicSession.endDate" />:</b>
	<jstl:out value="${publicSession.endDate}"></jstl:out>
	<br />
	
	<b><spring:message code="publicSession.capacity" />:</b>
	<jstl:out value="${publicSession.capacity}"></jstl:out>
	<br />
	
	<b><spring:message code="publicSession.essayNumber" />:</b>
	<jstl:out value="${publicSession.essayNumber}"></jstl:out>
	<br />
	
	<b><spring:message code="publicSession.contest" />:</b>
	<jstl:out value="${publicSession.contest.name}"></jstl:out>
	<br />
	
		<display:table name="essays" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<!-- Attributes -->
	
	<spring:message code="essay.title" var="title" />
	<display:column title="${title}" property="title" />
	
	<spring:message code="essay.submissionDate" var="submissionDate" />
	<display:column title="${submissionDate}" property="submissionDate" />
	
	<spring:message code="essay.contest.name" var="contest" />
	<display:column title="${contest}" property="contest.name" />
	
	
</display:table>
	
	<security:authorize access="hasRole('ORGANISER')">
			<input type="button" value="<spring:message	code="publicSession.cancel" />" 
		onclick="javascript: window.location.replace('publicSession/organiser/list.do')">
	</security:authorize>
	
	<security:authorize access="hasRole('ORGANISER')">
			<input type="button" value="<spring:message	code="publicSession.edit" />" 
		onclick="javascript: window.location.replace('publicSession/organiser/edit.do?publicSessionId=${publicSession.id}')">
	</security:authorize>
	
	<security:authorize access="hasRole('ORGANISER')">
	<jstl:if test="${check == false}">
	
			<input type="button" value="<spring:message	code="publicSession.addEssays" />" 
		onclick="javascript: window.location.replace('publicSession/organiser/addEssay.do?publicSessionId=${publicSession.id}')">
		
		</jstl:if>
		</br>
		<jstl:if test="${check == true}">
		<h2><spring:message	code="publicSession.full" /></h2>
		</jstl:if>
	</security:authorize>
	
	
	<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(150);
			};
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

	
	


