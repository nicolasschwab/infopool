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
		<jsp:param name="itemActivo" value="2" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="main row">
				<div>
					<a href="registroEvento" class="btn btn-primary"><span class="fa fa-pencil" aria-hidden="true"></span> Registrar Evento</a>
				</div>
								
				<s:if test="%{eventoLista!=null}">
					<h3 class="tituloSeccion">Eventos Registrados</h3>
					<table class="table" id="datatable">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Ubicaci&oacute;n</th>
								<th>Fecha del evento</th>							
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="eventoLista">
								<tr>
									<td><s:property value="nombre" /></td>
									<td><s:property value="ubicacion" /></td>
									<td><s:date name="fechaHora" format="dd/MM/yyyy HH:mm" /></td>								
									<td>
										<s:url id="detalleURL" action="detalleEvento">
											<s:param name="id" value="%{id}"></s:param>
										</s:url>
										<s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs">ver detalle</s:a>							
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
				<s:else>
					<p>No hay eventos registrados</p>
				</s:else>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>