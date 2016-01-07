
<%
	request.getSession(true);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h3 class="tituloSeccion margentb2">
				<s:text name="viajero.registrolbl" />
			</h3>
			<s:fielderror />
			<s:form cssClass="form-signin" theme="simple" role="form" id="registroForm"
				action="registrarUsuario" method="POST"
				enctype="multipart/form-data" onsubmit="return validarForm();">
				<fieldset id="datosPersonales">
					<legend>
						<s:text name="global.datospersonales"></s:text>
					</legend>
					<div class="form-group">
						<label id="errornombreUsuario" class="error"></label><br>
						 <label><s:text name="viajero.nombre" /></label>
						<s:textfield cssClass="form-control" name="nombreUsuario" />
					</div>
					<div class="form-group">
						<label id="errorapellidoUsuario" class="error"></label><br> 
						<label><s:text name="viajero.apellido" /></label>
						<s:textfield cssClass="form-control" name="apellidoUsuario" />
					</div>
					<div class="form-group">
						<label id="errorfechaUsuario" class="error"></label><br> 
						<label><s:text name="viajero.fechanacimiento" /></label>
						<div class='input-group date datepicker'>
							<s:textfield cssClass="form-control" readonly="true" label="Fecha de nacimiento"
								name="fechaUsuario" data-date-format="YYYY-MM-DD" />
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<div class="form-group">
						<label id="errortelefonoUsuario" class="error"></label><br>
						 <label><s:text name="viajero.telefono" /></label>
						<s:textfield cssClass="form-control" name="telefonoUsuario" />
					</div>
					<div class="form-group">
						<label id="errormailUsuario" class="error"></label><br>
						 <label><s:text name="viajero.mail" /></label>
						<s:textfield cssClass="form-control" name="mailUsuario" />
					</div>
					<div class="form-group">
						<s:textfield cssClass="btn btn-primary" role="button"
							readonly="true" onclick="javascript: mostrarDatosUsuario()"
							value="Siguiente"></s:textfield>
					</div>
				</fieldset>
				<fieldset id="datosUsuario">
					<legend>
						<s:text name="global.datosusuario"></s:text>
					</legend>
					<div class="form-group">
						<label id="errorusuario" class="error"></label><br>
						<label><s:text name="viajero.nombreusuario" /></label>
						<s:textfield cssClass="form-control" name="usuario" />
					</div>
					<div class="form-group">
						<label id="errorclave" class="error"></label><br>
						<label><s:text name="viajero.clave" /></label>
						<s:password cssClass="form-control" name="clave" />
					</div>
					<div class="form-group">
						<label id="errorrClave" class="error"></label><br>
						<label><s:text name="viajero.reingresarclave" /></label>
						<s:password cssClass="form-control" name="rClave" />
					</div>
					<div class="form-group">
						<label id="errorrClave" class="error"></label><br>
						<label><s:text name="viajero.poseeAuto" /></label>
						<s:select list="#{'SI':'Si','NO':'No'}" id="poseeAuto"></s:select>
					</div>
					<div class="form-group">
						<label id="errorprefUsuario" class="error"></label><br>
						<label><s:text name="viajero.preferencias" /></label><br>
						<img src="images/hablar.jpg" title="Hablar durante el viaje" alt="Hablar">
							<s:checkbox name="prefUsuario" value="HABLAR" fieldValue="HABLAR" /> 
						<img src="images/comer.jpg" title="Comer o tomar algo" alt="Comer"> 
							<s:checkbox name="prefUsuario" value="COMER" fieldValue="COMER" />						 
						<img src="images/fumar.jpg" title="Fumar" alt="Fumar">
							<s:checkbox name="prefUsuario" value="FUMAR" fieldValue="FUMAR" /> 
						
					</div>
					<div class="form-group">
						<label id="errorfperfilUsuario" class="error"></label><br/>
						<label><s:text name="viajero.imagenperfil" /></label>
						<s:file name="fperfilUsuario" />
					</div>
					<br>
					<div class="form-group">
						<s:textfield cssClass="btn btn-primary" role="button"
							readonly="true" onclick="javascript: mostrarDatosAuto()"
							value="Siguiente"></s:textfield>
					</div>
					<div class="form-group">
						<s:textfield cssClass="btn btn-primary" role="button"
							readonly="true" onclick="javascript: mostrarDatosPersonales()"
							value="Atras"></s:textfield>
					</div>
				</fieldset>

				<fieldset id="datosAuto">
					<s:text name="viajero.autoopcional"></s:text>
					<br>
					<legend>
						<s:text name="global.datosvehiculo"></s:text>
					</legend>
					<div class="form-group">
						<label id="errorvistaMarca" class="error"></label><br/>
						<label><s:text name="vehiculo.marca" /></label>
						<s:select list="#{'':'','FORD':'Ford','CITROEN':'Citroen','CHEVROLET':'Chevrolet','DODGE':'Dodge','FIAT':'Fiat','HONDA':'Honda','HYUNDAI':'Hyundai','PEUGEOT':'Peugeot','RENAULT':'Renault','SEAT':'Seat','SUZUKI':'Suzuki','TOYOTA':'Toyota','VOLKSWAGEN':'Volkswagen'}" cssClass="form-control" name="vistaMarca" ></s:select>
					</div>
					<div class="form-group">
						<label id="errormodelo" class="error"></label><br/>
						<label><s:text name="vehiculo.modelo" /></label>
						<div class='input-group date datepicker'>
							<s:textfield cssClass="form-control" name="modelo"
								data-date-format="YYYY" />
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<div class="form-group">
						<label id="errorvistaCombustible" class="error"></label><br/>
						<label><s:text name="vehiculo.combustible" /></label>
						<s:select list="#{'':'','NAFTA':'Nafta','GASOIL':'Gasoil','GAS':'Gas','ELECTRICO':'Electrico'}" cssClass="form-control" name="vistaCombustible"></s:select>
					</div>
					<div class="form-group">
						<label id="errorvistaTipo" class="error"></label><br/>
						<label><s:text name="vehiculo.tipo" /></label>
						<s:select list="#{'':'','AUTO_3_PUERTAS':'Auto_3_Puertas','AUTO_5_PUERTAS':'Auto_5_Puertas','CAMIONETA':'Camioneta'}" cssClass="form-control" name="vistaTipo"></s:select>
					</div>
					<br>
					<div class="form-group">
						<s:textfield cssClass="btn btn-primary" role="button"
							readonly="true" onclick="javascript: mostrarDatosUsuario()"
							value="Atras"></s:textfield>
					</div>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary"
							value="%{getText('global.registrar')}" />
					</div>
				</fieldset>
			</s:form>
			</div >
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>