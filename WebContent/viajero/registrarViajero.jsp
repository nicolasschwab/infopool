<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>
	
	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="88" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="col-sm-6 centrar">
			<h3 class="tituloSeccion"><s:text name="viajero.registrolbl" /></h3>
			<s:fielderror />
			<s:form cssClass="form-datos" theme="simple" role="form" id="registroForm"
				action="registrarUsuario" method="POST"
				enctype="multipart/form-data" onsubmit="return validarForm();">
				<fieldset id="datosPersonales">
					<legend><s:text name="global.datospersonales"></s:text></legend>
					<div class="form-group">
						<label><s:text name="viajero.nombre" /></label>
						<label id="errornombreUsuario" class="error"></label>						 
						<s:textfield cssClass="form-control" name="nombreUsuario" />
					</div>
					<div class="form-group">
						<label><s:text name="viajero.apellido" /></label>
						<label id="errorapellidoUsuario" class="error"></label>						
						<s:textfield cssClass="form-control" name="apellidoUsuario" />
					</div>
					<div class="form-group">
						<label><s:text name="viajero.fechanacimiento" /> (YYYY-MM-DD)</label>
						<label id="errorfNacimientoUsuario" class="error"></label>						
						<div class='input-group date datepicker'>
							<s:textfield cssClass="form-control" label="Fecha de nacimiento"
								name="fNacimientoUsuario" data-date-format="YYYY-MM-DD" />
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<div class="form-group">						
						<label><s:text name="viajero.telefono" /></label>
						<label id="errortelefonoUsuario" class="error"></label>
						<s:textfield cssClass="form-control" name="telefonoUsuario" />
					</div>
					<div class="form-group">
						<label><s:text name="viajero.mail" /></label>
						<label id="errormailUsuario" class="error"></label>						
						<s:textfield cssClass="form-control" name="mailUsuario" />
					</div>
					<div class="form-group">
						<s:textfield cssClass="btn btn-next" role="button"
							readonly="true" onclick="javascript: mostrarDatosUsuario()"
							value="Siguiente"></s:textfield>
					</div>
				</fieldset>				
				<fieldset id="datosUsuario">
					<legend><s:text name="global.datosusuario"></s:text></legend>
					<div class="form-group">						
						<label><s:text name="viajero.nombreusuario" /></label>
						<label id="errorusuario" class="error"></label>
						<s:textfield cssClass="form-control" name="usuario" />
					</div>
					<div class="form-group">
						<label><s:text name="viajero.clave" /></label>
						<label id="errorclave" class="error"></label>
						<s:password cssClass="form-control" name="clave" />
					</div>
					<div class="form-group">						
						<label><s:text name="viajero.reingresarclave" /></label>
						<label id="errorrClave" class="error"></label>
						<s:password cssClass="form-control" name="rClave" />
					</div>	
					<div class="form-group">						
						<label><s:text name="viajero.imagenperfil" /></label>
						<label id="errorfperfilUsuario" class="error"></label>
						<s:file name="fperfilUsuario" />
					</div>				
					<div class="form-group">
						<s:textfield cssClass="btn btn-back" role="button"
							readonly="true" onclick="javascript: mostrarDatosPersonales()"
							value="Atras">
						</s:textfield>						
						<s:submit cssClass="btn btn-primary" value="%{getText('global.registrar')}" />
					</div>
				</fieldset>
			</s:form>
			</div >
		</div>
	</div>	
	
	<%@ include file="../views/footer.jsp"%>
		
</body>
</html>