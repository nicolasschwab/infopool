<%  request.getSession(true); %>
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
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="main row">

				<div>
					<s:a href="javascript:history.back();" cssClass="btn btn-primary">Regresar</s:a>
				</div>
				<h3 class="tituloSeccion margentb2">Solicitudes para el viaje</h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th>Nombre Viajero</th>
							<th>Fecha Solicitud</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="solicitudesviaje" var="prueba">
							<tr>
								<td><s:property value="viajero.nombre" /> <s:property value="viajero.apellido" /></td>
								<td><s:date name="fechaSolicitud" format="dd/MM/YYYY" /> <s:date name="fechaSolicitud" format="HH:mm" /></td>
								<td><s:property value="estado" /></td>
								<td><s:url id="rechazarSolicitud" action="rechazarSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:url id="aceptarSolicitud" action="aceptarSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:if test="%{estado.name()=='PENDIENTE'}">
										<s:a href="%{aceptarSolicitud}" cssClass="btn btn-default btn-xs btn-success">Aceptar</s:a>
										<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger">Rechazar</s:a>
									</s:if>
									<s:elseif test="%{estado.name()=='ACEPTADO'}">
										<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger">Rechazar</s:a>
									</s:elseif>
									<s:elseif test="%{estado.name()=='RECHAZADO'}">
										<s:a href="%{aceptarSolicitud}"	cssClass="btn btn-default btn-xs btn-success">Aceptar</s:a>
									</s:elseif></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- end main -->
	<%@ include file="views/footer.jsp"%>
</body>
</html>