<% request.getSession(true);%>
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
				<h3 class="tituloSeccion">Viajeros registrados</h3>				
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th>Nombre Viajero</th>
							<th>Teléfono</th>
							<th>Mail</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="viajeroLista">
							<tr>
								<td><s:property value="nombre"/> <s:property value="apellido"/></td>
								<td><s:property value="telefono" /></td>
								<td><s:property value="mail"/></td>
								<td>
									<s:if test="%{activo}">
										Activo
									</s:if>
									<s:else>
										Inactivo
									</s:else>
								</td>																
								<td>
									<s:url id="detalleURL" action="detalleViajero">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs">ver detalle</s:a>
								</td>
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