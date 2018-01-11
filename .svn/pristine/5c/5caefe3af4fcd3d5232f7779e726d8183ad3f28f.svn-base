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

<display:table name="organisers" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<!-- Attributes -->
	
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
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
			<a href="organiser/display.do?organiserId=${row.id}">
				<spring:message code="organiser.profile" />
			</a>
	</display:column>
	</security:authorize>
		<jstl:if test="${contestId != null}">
		<security:authorize access="hasRole('ORGANISER')">
	<display:column>
			<a href="organiser/addorganiser.do?contestId=${contestId}&organiserId=${row.id}">
				<spring:message code="organiser.selectOrganiser" />
			</a>
	</display:column>
	</security:authorize>
	</jstl:if>
	
</display:table>

<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>

<jstl:if test="${empty organisers }">	
<spring:message code="organiser.noBodyToListOrAdd" />
</jstl:if>

