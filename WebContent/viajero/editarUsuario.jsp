<% request.getSession(true);%>
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
		<jsp:param name="itemActivo" value="2" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2"><s:text name="viajero.edicionlbl" /></h3>
				<s:fielderror />
				<s:form cssClass="form-signin" theme="simple" role="form" action="editarUsuario">
					<div class="form-group">
						<label><s:text name="viajero.nombre" /></label>
						<s:textfield cssClass="form-control" name="nombreUsuario" value="%{user.nombre}" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.apellido" /></label>
						<s:textfield cssClass="form-control" name="apellidoUsuario" value="%{user.apellido}" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.fechanacimiento" /></label>
						<div class='input-group date datepicker'>
							<s:date name="user.fechaNacimiento" id="fechaNac" format="yyyy-MM-dd"/>
							<s:textfield cssClass="form-control" name="fechaUsuario" data-date-format="YYYY-MM-DD" value="%{fechaNac}"/>
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>						
					</div>					
					<div class="form-group">
						<label><s:text name="viajero.telefono" /></label>
						<s:textfield cssClass="form-control" name="telefonoUsuario" value="%{user.telefono}" />						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.mail" /></label>
						<s:textfield cssClass="form-control" name="mailUsuario" value="%{user.mail}" />						
					</div>					
					<div class="form-group">
						<label><s:text name="viajero.clave" /></label>
						<s:password cssClass="form-control" name="clave"/>						
					</div>
					<div class="form-group">
						<label><s:text name="viajero.reingresarclave" /></label>
						<s:password cssClass="form-control" name="rClave"/>						
					</div>					
					<div class="form-group">
						<label><s:text name="viajero.preferencias" /></label>
						<s:textarea cssClass="form-control" label="prefUsuario" name="prefUsuario" rows="10" value="%{user.preferenciasViaje}"/>						
					</div>					
					<br>
					<div class="form-group">
						<s:hidden name="id" value="%{user.id}"/>	
						<s:submit cssClass="btn btn-primary" value="%{getText('global.editar')}" />
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<%@ include file="../views/footer.jsp"%>
</body>
</html>