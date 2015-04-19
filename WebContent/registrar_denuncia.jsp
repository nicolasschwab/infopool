<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<!--<script type="text/javascript" src="js/Validaciones.js"></script>-->
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body >

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Registro de Denuncia</h3>
				<s:form role="form" theme="simple" cssClass="form-signin" action="registrarDenuncia">
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione el motivo de la denuncia</label>
								<br>
								<s:select cssClass="form-control"  name="motivo" list="#{'imprudencia al manejar':'imprudencia al manejar','impuntualidad':'impuntualidad','acoso verbal':'acoso verbal'}"/>								
							</div>
						</div>												
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione el denunciado</label>
								<br>
								<s:select list="listaDenunciados" listKey="id" listValue="nombre + ' ' + apellido +' ('+ usuario + ')'"
								headerKey="-1" headerValue="" name="denunciado"
								value="listaDenunciados.{id}" cssClass="form-control" />			
							</div>
						</div>												
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Ingrese el mensaje de la denuncia</label>
								<br>
								<s:textarea cssClass="form-control" label="prefUsuario" name="mensaje" rows="10" />		
							</div>
						</div>												
					</div>
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Registrar"/>
					</div>

				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="views/footer.jsp"%>
</body>
</html>