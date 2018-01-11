<%--
 * create.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ORGANISER')"> 

<form:form action="contest/organiser/edit.do" modelAttribute="contest">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="organisers"/>
	<form:hidden path="essays"/>


	<acme:textbox code="contest.name" path="name" />
	<acme:textbox code="contest.description" path="description" />
	<acme:textbox code="contest.holdingDate" path="holdingDate" />
	<acme:textbox code="contest.deadlineDate" path="deadlineDate" />


	<br /><br />
		
	
	<br /><br />

	
	<acme:submit name="save" code="contest.save"/>
	
	<security:authorize access="hasRole('ORGANISER')">
	
	<input type="button" name="cancel" value="<spring:message code="contest.cancel"/>" 
	onclick="javascript: window.location.replace('contest/organiser/my-contests.do')"/>
	
 
	<jstl:if test="${ canBeDeleted == true and contest.id != 0}">

	<input type="submit" name="delete"
			value="<spring:message code="contest.delete" />"
			onclick="return confirm('<spring:message code="contest.confirm.delete" />')" />&nbsp;		
	</jstl:if>
	<br>
	</security:authorize>
	
	
	<jstl:if test="${ haHabidoError eq true}">
	<br>
	<b><spring:message code="error" />:</b>
	<br>
	<spring:message code="contest.thereAreAPassDate" />
	<br>
	<spring:message code="contest.cantSaveBecauseHoldingDateIsBeforeDeadline" />
	<br>
	<spring:message code="contest.cantSaveBecauseHaveResultButNoEssay" />
	<br>
	<spring:message code="contest.cantSaveBecauseHaveResultButHoldingDateDontExpired" />
	<br>
	<spring:message code="contest.cantSaveBecauseDeadlineOrHoldingDateHaventBeenChangedToFuture" />
	
	</jstl:if>
</form:form>

</security:authorize>