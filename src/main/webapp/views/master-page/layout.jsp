<%--
 * layout.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- <base -->
<!-- 	href="https://www.juntadeandalucia.es/servicioandaluzdesalud/hhuuvr/esaludpre/Bidamir/" /> -->

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="favicon.ico"/> 

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jmenu.js"></script>

<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<script type="text/javascript">
	$(document).ready(function() {
		$("#jMenu").jMenu();
	});

	function askSubmission(msg, form) {
		if (confirm(msg))
			form.submit();
	}
	
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>

<body>

	<div>
		<tiles:insertAttribute name="header" />

	</div>
	<div>
	
		<tiles:insertAttribute name="body" />	
		<jstl:if test="${message != null}">
			<br />

			
				<script>
				$(document).ready(function(){
				    
				        $("#myModal").modal();
				   
				});
				</script>
				
				  <!-- Modal -->
			 <div class="modal modal-danger" id="myModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Aviso!</h4>
			        </div>
			        <div class="modal-body">
			          <span ><spring:message code="${message}" /></span>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>
		</jstl:if>	

	</div>
	

	<div>
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>