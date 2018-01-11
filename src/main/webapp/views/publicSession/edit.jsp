<%--
 * create.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" enctype="multipart/form-data" method="post" modelAttribute="publicSessionForm">

<form:hidden path="publicSessionId"/>
	<acme:textbox code="publicSession.startDate" path="startDate" />
	<acme:textbox code="publicSession.endDate" path="endDate" />
	<acme:textbox code="publicSession.capacity" path="capacity" />
	<input type="file" name="photo" id="blah" onchange="readURL(this);">
	<acme:textbox code="publicSession.essayNumber" path="essayNumber" />
	<acme:select items="${contests}" itemLabel="name" code="publicSession.contest" path="contest"/>
	<acme:select items="${organisers}" itemLabel="name" code="publicSession.chairman" path="chairman"/>

	<acme:submit name="save" code="publicSession.save"/>
	

	<input type="button" name="cancel" value="<spring:message code="publicSession.cancel"/>" 
	onclick="javascript: window.location.replace('../AcmeEssay')"/>	

</form:form>

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


