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
		<div class="container">
			<div class="main row">
				<div>
					<s:a href="javascript:history.back();" cssClass="btn btn-primary"><s:text name="global.regresar" /></s:a>
				</div>
				<h3 class="tituloSeccion"><s:text name="solicitud.titulo" /></h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th><s:text name="solicitud.nombreViajero" /></th>
							<th><s:text name="solicitud.fecha" /></th>
							<th><s:text name="solicitud.estado" /></th>
							<th><s:text name="global.acciones" /></th>
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
										<s:a href="%{aceptarSolicitud}" cssClass="btn btn-default btn-xs btn-success"><s:text name="solicitud.aceptar" /></s:a>
										<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger"><s:text name="solicitud.rechazar" /></s:a>
									</s:if>
									<s:elseif test="%{estado.name()=='ACEPTADO'}">
										<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger"><s:text name="solicitud.rechazar" /></s:a>
									</s:elseif>
									<s:elseif test="%{estado.name()=='RECHAZADO'}">
										<s:a href="%{aceptarSolicitud}"	cssClass="btn btn-default btn-xs btn-success"><s:text name="solicitud.aceptar" /></s:a>
									</s:elseif></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>