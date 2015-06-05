<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="99" />
	</jsp:include>
	
	<div class="main_bg">
		<div class="container">
			<div class="col-md-6 margentb2">
				<h3 class="tituloSeccion margentb2"><s:text name="viajero.registrolbl" /></h3>				
				<s:fielderror />			
				<s:form cssClass="form-signin" theme="simple" role="form" action="registrarUsuario" method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<label><s:text name="viajero.nombre" /></label>
						<s:textfield cssClass="form-control" name="nombreUsuario" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.apellido" /></label>
						<s:textfield cssClass="form-control" name="apellidoUsuario" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.fechanacimiento" /></label>
						<div class='input-group date datepicker'>
							<s:textfield cssClass="form-control" label="Fecha de nacimiento" name="fechaUsuario" data-date-format="YYYY-MM-DD"/>
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>					
					</div>					
					<div class="form-group">
						<label><s:text name="viajero.telefono" /></label>
						<s:textfield cssClass="form-control" name="telefonoUsuario" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.mail" /></label>
						<s:textfield cssClass="form-control" name="mailUsuario" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.nombreusuario" /></label>
						<s:textfield cssClass="form-control" name="usuario" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.clave" /></label>
						<s:password cssClass="form-control" name="clave" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.reingresarclave" /></label>
						<s:password cssClass="form-control" name="rClave" />						
					</div>					
					<div class="form-group">
						<label><s:text name="viajero.preferencias" /></label>
						<s:textarea cssClass="form-control" name="prefUsuario" rows="10" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.imagenperfil" /></label>
						<s:file name="fperfilUsuario"/>
					</div>
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="%{getText('global.registrar')}" />
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>