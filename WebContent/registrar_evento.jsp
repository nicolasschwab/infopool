<% request.getSession(true);%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarRegistroEvento();">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="2" />
	</jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Registro del Evento</h3>
				<s:fielderror />
				<s:form cssClass="form-signin" role="form" theme="simple" action="registrarEvento">
					<div class="form-group">
						<label for="exampleInputEmail1">Fecha</label>
						<div class='input-group date col-md-3' id='datetimepicker2'>
							<s:textfield cssClass="form-control" name="fechaHora" data-date-format="YYYY-MM-DD HH:mm"/>
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<div class="form-group">
						<label for="nombre">Nombre</label>
						<s:textfield cssClass="form-control" name="nombre" label="Nombre del evento" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Web del Evento</label>
						<s:textfield cssClass="form-control" name="web" label="Ingrese la Web" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Ubicaci&oacute;n</label>
						<s:textfield cssClass="form-control" id="dirOrigen" name="ubicacion" label="Ingrese la direcci&oacute;n" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Ubicaci&oacute;n GoogleMap</label>
						<button type="button" cssClass="btn btn-default" onclick="javascript:calcPoint();">Localizar ubicaci&oacute;n</button>
						<br>
						<br>
						<div id="map-canvas" style="width: 100%; height: 500px"></div>
					</div>
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Registrar" />
					</div>

				</s:form>
			</div>
		</div>
	</div>
	<!-- end main -->

	<%@ include file="views/footer.jsp"%>
</body>
</html>