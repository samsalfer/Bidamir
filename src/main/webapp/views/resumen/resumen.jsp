<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



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
            <li class="header">Menú</li>
            <li  >
              <a href="/Bidamir">
                <i class="fa fa-home"></i> <span>Inicio</span>
              </a>

            </li>
			<security:authorize access="hasRole('ADMIN')">

            <li class="treeview">
              <a href="#">
                <i class="fa fa-database"></i>
                <span>Estudio</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/estudio/select.do"><i class="fa fa-circle-o"></i> Crear Estudio</a></li>
                <li><a class="fNiv" href="user/estudio/list.do"><i class="fa fa-circle-o"></i> Mis estudios</a></li>
              </ul>
            </li>
            <li class="treeview  active">
              <a href="#">
                <i class="fa fa-clipboard"></i>
                <span>Resumen</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a class="fNiv" href="user/resumen/display.do"><i class="fa fa-circle-o"></i> Crear Resumen</a></li>
                <li><a class="fNiv" href="user/resumen/list.do"><i class="fa fa-circle-o"></i> Mis Resúmenes</a></li>
              </ul>
            </li>
            
            
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-tags"></i>
                <span>Detección Negación</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/negacion/edit.do"><i class="fa fa-circle-o"></i> Detectar Negación</a></li>
              </ul>
            </li>
            
            <li class="treeview">
              <a href="#">
                <i class="fa  fa-search"></i>
                <span>Búsqueda Documentos</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a class="fNiv" href="user/busqueda/edit.do"><i class="fa fa-circle-o"></i> Buscar Documentos</a></li>
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
                    <h1><span class="fa fa-book"></span>
            Realizar un resumen
			    </h1>
          <ol class="breadcrumb">
            <li><i class="fa fa-home"></i> Resumen</li>
            
          </ol>
        </section>
            <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- left column -->
            <div class="col-md-12">
              <!-- general form elements -->
 		<form:form action="user/resumen/display.do" enctype="multipart/form-data" method="post" modelAttribute="resumenForm">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Textos resumidos</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                 


           <div class="col-md-12">
              <!-- Custom Tabs -->
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  
                  
                  <jstl:if test="${resumenForm.texto.size()>0}">
                 	 <jstl:forEach begin="0" end="${resumenForm.texto.size()-1}" var="val">
							<jstl:if test="${val!=resumenForm.texto.size()-1}">
							
							<li><a href="#tab_${val}" data-toggle="tab">${resumenForm.nombre[val]}</a></li>
							</jstl:if>
							<jstl:if test="${val==resumenForm.texto.size()-1}">
							
							<li class="active"><a href="#tab_${val}" data-toggle="tab">${resumenForm.nombre[val]}</a></li>
							</jstl:if>
						</jstl:forEach>
					</jstl:if> 
                </ul>
                <div class="tab-content">
                 	 <jstl:if test="${resumenForm.texto.size()>0}">
                 	 <jstl:forEach begin="0" end="${resumenForm.texto.size()-1}" var="val">
	                  <jstl:if test="${val!=resumenForm.texto.size()-1}">
	                 
	                      <div class="tab-pane" id="tab_${val}">
                                   	 
	                      ${resumenForm.texto[val]}
						  </div><!-- /.tab-pane -->
						</jstl:if>
						<jstl:if test="${val==resumenForm.texto.size()-1}">
	                 
	                      <div class="tab-pane active" id="tab_${val}">
	                      ${resumenForm.texto[val]}
						  </div><!-- /.tab-pane -->
						</jstl:if>
						</jstl:forEach>
					</jstl:if> 
                      <div class="form-group">

                    </div>

                  <div class="tab-pane" id="tab_2">
                      <div class="form-group">
                      <label for="title">Texto</label>

                    </div>

                  </div><!-- /.tab-pane -->
                  <div class="tab-pane" id="tab_3">
                      <div class="form-group">
                      <label for="title">Texto</label>

                    </div>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- nav-tabs-custom -->
            </div><!-- /.col -->

                  <div class="box-footer">
                  
  
                  </div>
              </div><!-- /.box -->
		</form:form>

              <!-- Form Element sizes -->


              <!-- Input addon -->

            </div><!--/.col (left) -->

<div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                
                  <h3 class="box-title">Resumen</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                 


           <div class="col-md-12">
              <!-- Custom Tabs -->
                          <div class="box box-solid">
                
                <div class="box-body">
                 
				<jstl:forEach var="linea" items="${resumen.sentencias}">
				<label data-toggle="tooltip" title="${linea.hojaEvolucion}">${linea.texto}</label>
				<br>
					
				
				</jstl:forEach>


                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->

                  <div class="box-footer">
                 
                  </div>
              </div><!-- /.box -->

              <!-- Form Element sizes -->


              <!-- Input addon -->

            </div>
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