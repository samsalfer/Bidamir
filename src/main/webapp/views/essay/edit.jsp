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

	
<form:form action="essay/author/edit.do" modelAttribute="essay">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="author"/>
	<form:hidden path="contest"/>
	<form:hidden path="submissionDate"/>
	<form:hidden path="isPublished"/>
	
	<form:label path="title">
		<spring:message code="essay.title" />
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="summary">
		<spring:message code="essay.summary" />
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary"/>
	<br />
	
	<form:label path="content">
		<spring:message code="essay.content" />
	</form:label>
	<form:textarea path="content"/>
	<form:errors cssClass="error" path="content"/>
	<br />
	
	<input type="submit" name="save" value="<spring:message code="essay.save"/>" />
	&nbsp;
	
	<input type="button" name="cancel" value="<spring:message code="essay.cancel"/>" 
	onclick="javascript: window.location.replace('contest/list.do')"/>
	
	<jstl:if test="${ haHabidoError eq true}">
	<br>
	<b><spring:message code="error" />:</b>
	<br>
	<spring:message code="essay.deadline" />
	<br>
	<spring:message code="essay.same.title" />
	<br>
	<spring:message code="essay.published" />
	
	</jstl:if>
	
</form:form>
	
