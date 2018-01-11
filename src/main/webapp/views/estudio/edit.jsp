<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css"></link>

 <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <header class="main-header">
        <!-- Logo -->
        <a href="/Bidamir" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>B</b>D<b>M</b></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>BI</b>DA<b>MIR</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
            
            
              <!-- Control Sidebar Toggle Button -->

            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
  

          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">Men�</li>
            <li  >
              <a href="/Bidamir">
                <i class="fa fa-home"></i> <span>Inicio</span>
              </a>

            </li>
			<security:authorize access="hasRole('ADMIN')">

            <li class="treeview active" >
              <a href="#">
                <i class="fa fa-database"></i>
                <span>Estudio</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a class="fNiv" href="user/estudio/select.do"><i class="fa fa-circle-o"></i> Crear Estudio</a></li>
                <li><a class="fNiv" href="user/estudio/list.do"><i class="fa fa-circle-o"></i> Mis estudios</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-clipboard"></i>
                <span>Resumen</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/resumen/display.do"><i class="fa fa-circle-o"></i> Crear Resumen</a></li>
                <li><a class="fNiv" href="user/resumen/list.do"><i class="fa fa-circle-o"></i> Mis Res�menes</a></li>
              </ul>
            </li>
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-tags"></i>
                <span>Detecci�n Negaci�n</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/negacion/edit.do"><i class="fa fa-circle-o"></i> Detectar Negaci�n</a></li>
              </ul>
            </li>
            
            <li class="treeview">
              <a href="#">
                <i class="fa  fa-search"></i>
                <span>B�squeda Documentos</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/busqueda/edit.do"><i class="fa fa-circle-o"></i> Buscar Documentos</a></li>
            	<li><a class="fNiv" href="user/busqueda/indexar.do"><i class="fa fa-circle-o"></i> Indexar Documentos</a></li>

              </ul>
            </li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-user"></i> <span>Usuario</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="j_spring_security_logout"><i class="fa fa-circle-o"></i> Desconectar</a></li>
               
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="isAnonymous()">
            
                        <li class="treeview">
              <a href="#">
                <i class="fa fa-user"></i> <span>Usuario</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/create.do"><i class="fa fa-circle-o"></i> Crear usuario</a></li>
                <li><a class="fNiv" href="security/login.do"><i class="fa fa-circle-o"></i> Acceder</a></li>
               
              </ul>
            </li>
            
            </security:authorize>
          
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>
      
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
                    <h1><span class="fa fa-hand-peace-o"></span>
            Crear Estudio 2/3
			    </h1>
          <ol class="breadcrumb">
            <li><i class="fa fa-home"></i> Crear estudio</li>
            
          </ol>
        </section>
            <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- left column -->
            <div class="col-md-12">
              <!-- general form elements -->
 			<div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Formulario estudio</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form:form action="user/estudio/create.do" enctype="multipart/form-data" method="post" modelAttribute="estudioForm">
                  <form:hidden path="estudioId"/>
                  
                  <div class="box-body">
                    <div class="form-group">
                      <label for="title">T�tulo</label>
                      <form:input class="form-control" id="title" path="title"/>
                      <form:errors cssClass="error" path="title" />
                    </div>
                    <div class="form-group">
                      <label for="tipo">Tipo</label>
                      <jstl:if test="${estudioForm.tipe == 'asociacion'}">
													<input class="form-control" id="tipo" readonly="true" value="Asociaci�n"></jstl:if>
					<jstl:if test="${estudioForm.tipe == 'clasificacion'}">
													<input class="form-control" id="tipo" readonly="true" value="Predicci�n"></jstl:if>
                      
                      <form:input style="display: none;" class="form-control" id="tipo" path="tipe" readonly="true" />
                    </div>
                    <div class="form-group">
                      <label for="input">Datos Excel </label><i class="fa  fa-question-circle"  data-toggle="tooltip" title="Seleccione un documento excel que contenga los datos necesarios para realizar el estudio deseado."></i>
                      <input type="file" class="filestyle" id="input" name="file" id="blah" onchange="readURL(this);">
                     
                    </div>
                    
                    
						<jstl:if test="${estudioForm.tipe == 'clasificacion'}">
	                    <div class="form-group">
	                      <label>Algoritmo
							<a data-toggle="modal" href="#myModal2">�Necesita Ayuda?</a></label>
	                      <form:select path="algoritmo" class="form-control">
	                        <option>�rbol de decisi�n</option>
	                        <option>M�quina de soporte vectorial</option>
	                      </form:select>
	                    </div>
	                    </jstl:if>
	                    
	                   <jstl:if test="${estudioForm.tipe == 'asociacion'}">
	                    <div class="form-group">
	                      <label>Algoritmo
	                      <a data-toggle="modal" href="#myModal2">�Necesita Ayuda?</a></label>
	                      <form:select path="algoritmo" class="form-control">
	                        <option>Subgroup Discovery</option>
	                        <option>Regresi�n Lineal</option>
	                        
	                      </form:select>
	                    </div>
	                    </jstl:if>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                  <!-- Button trigger modal -->



                  	<acme:submit name="save" code="estudio.next"/>
                  
                  <input type="button" name="cancel" value="<spring:message code="estudio.cancel"/>" 
	onclick="javascript: window.location.replace('../Bidamir')" class="btn btn-primary"/>
                  </div>
                  
					  <!-- Modal -->
					<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">Tipos de algoritmo</h4>
					      </div>
					      <div class="modal-body">
					      <b><u>Clasificaci�n</u></b><br><br>
					       <b>�rbol de decisi�n:</b> t�cnica predictiva (clasificaci�n) en la que de forma jer�rquica (�rbol) se plantean sucesivas decisiones sobre las variables, hasta llegar a un resultado final de predicci�n.<br><br>

							<b>M�quina de soporte vectorial: </b>  se clasifica mediante un conjunto de hiperplanos en un espacio de alta dimensionalidad. Una buena separaci�n entre las clases permitir� un clasificaci�n correcta.<br><br>
						  <b><u>Asociaci�n</u></b><br><br>
							<b>Subgroup Discovery:</b> obtenci�n de subgrupos (reglas) con propiedades estad�sticamente interesantes, con una distribuci�n estad�stica inusual respecto a cierta propiedad de inter�s.<br><br>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					      </div>
					    </div>
					  </div>
					</div>
				</form:form>
				</div>

              <!-- Form Element sizes -->


              <!-- Input addon -->

            </div><!--/.col (left) -->

          </div>   <!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.3
        </div>
        <strong>Copyright 2015-2016 <a href="http://digitalicahealth.com">Digitalica Salud</a>.</strong> All rights reserved.
      </footer>

      <!-- Control Sidebar -->
     
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
  </body>
</html>
