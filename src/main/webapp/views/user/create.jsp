<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bidamir | Registration Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition register-page">
    <div class="register-box">
      <div class="register-logo">
        <a href=".."><b>Registro</b>Usuario</a>
      </div>

      <div class="register-box-body">
        <p class="login-box-msg">Registrar un nuevo miembro</p>
        <form:form action="user/create.do" modelAttribute="registerUserForm">
        
          <div class="form-group has-feedback">
            <form:input path="username" class="form-control" placeholder="Usuario" />
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <form:errors cssClass="error" path="username" />
          </div>
          <div class="form-group has-feedback">
            <form:password path="password" class="form-control" placeholder="Password" />
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <form:errors cssClass="error" path="password" />
          </div>
          <div class="form-group has-feedback">
            <form:password path="passwordVerificada" class="form-control" placeholder="Repita su Password" />
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            <form:errors cssClass="error" path="passwordVerificada" />
          </div>
          <div class="form-group has-feedback">
            <form:input path="name" class="form-control" placeholder="Nombre" />
            <span class="glyphicon glyphicon-info-sign form-control-feedback"></span>
            <form:errors cssClass="error" path="name" />
          </div>
          <div class="form-group has-feedback">
            <form:input path="surname" class="form-control" placeholder="Apellidos" />
            <span class="glyphicon glyphicon-question-sign form-control-feedback"></span>
            <form:errors cssClass="error" path="surname" />
          </div>
          <div class="form-group has-feedback">
            <form:input path="email" class="form-control" placeholder="Email" />
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            <form:errors cssClass="error" path="email" />
          </div>
          <div class="form-group has-feedback">
            <form:input type="number" path="phone" class="form-control" placeholder="Teléfono"/>
            <span class="glyphicon glyphicon-earphone form-control-feedback"></span>
            <form:errors cssClass="error" path="phone" />
          </div>




          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
          
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
               <input type="submit"  name="save" class="btn btn-primary btn-block btn-flat" value="Registrar"/>
            </div><!-- /.col -->
          </div>
        </form:form>


      </div><!-- /.form-box -->
    </div><!-- /.register-box -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>
