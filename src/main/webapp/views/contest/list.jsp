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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table name="contests" id="row" pagesize="5"
	requestURI="${requestURI}" class="displaytag">

	<!-- Attributes -->

	<spring:message code="contest.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="contest.description" var="description" />
	<display:column title="${description}" property="description" />

	<spring:message code="contest.holdingDate" var="holdingDate" />
	<display:column title="${holdingDate}" property="holdingDate" />

	<spring:message code="contest.deadlineDate" var="deadlineDate" />
	<display:column title="${deadlineDate}" property="deadlineDate" />

	<jstl:if test="${ contest.result != null}">

		<spring:message code="contest.result" var="result" />
		<display:column title="${result}" property="result" />

	</jstl:if>
	
	<security:authorize access="hasRole('ORGANISER')">
		<display:column>
			<a href="publicSession/organiser/listbycontest.do?contestId=${row.id}"><spring:message code="contest.publicseassons" /></a>
			
		</display:column>
		
		
			<display:column>
			
			<a href="organiser/selectOrganiser.do?contestId=${row.id}"><spring:message code="contest.addorganiser" /></a>
			
			</display:column>
		
	</security:authorize>
	<%-- 	Essays	 --%>
	<security:authorize access="hasRole('AUTHOR')">
		<display:column>
			<a href="essay/author/list.do?contestId=${row.id}"><spring:message code="contest.essays" /></a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="permitAll">
		<display:column>
			<a href="essay/notAuthenticated/list.do?contestId=${row.id}"><spring:message code="contest.essaysPublics" /></a>
		</display:column>
	</security:authorize>
	<%-- 	Submit  contest	 --%>
	<security:authorize access="hasRole('AUTHOR')">
		
		<display:column>
		<jstl:if test="${now < row.deadlineDate}">
			<a href="essay/author/submit.do?contestId=${row.id}"><spring:message code="contest.submit" /></a>
		</jstl:if>	
		</display:column>
		
	</security:authorize>
	
	

	<%-- 	Display  contest	 --%>
	<security:authorize access="hasRole('ORGANISER')">
	<spring:message code="contest.display" var="display" />
	<display:column title="${display}">
		<a href="contest/organiser/display.do?contestId=${row.id}"><input
			type="button" value="
				<spring:message code="contest.display" />">
		</a>
	</display:column>
	</security:authorize>


</display:table>

<%-- 	Create  contest	 --%>

<security:authorize access="hasRole('ORGANISER')">
	<a href="contest/organiser/create.do"><input type="button"
		name="create" value="
					<spring:message code="contest.create" />">
					<jstl:if test="${seHaAnadidoConExito == true}">
			<spring:message code="contest.seHaAnadidoConExito" />
</jstl:if>	
	</a>
</security:authorize>


