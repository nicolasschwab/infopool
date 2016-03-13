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
	<jsp:include page="views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>
	
	<div class="main_bg">		
		<div class="container">
			<div class="main row">				
				<h3 class="tituloSeccion"><s:text name="viajero.listado" /></h3>				
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th><s:text name="viajero.nombre" /></th>
							<th><s:text name="viajero.telefono" /></th>
							<th><s:text name="viajero.mail" /></th>
							<th><s:text name="viajero.estado" /></th>
							<th><s:text name="global.acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listaViajeros">
							<tr>
								<td><s:property value="nombre"/> <s:property value="apellido"/></td>
								<td><s:property value="telefono" /></td>
								<td><s:property value="mail"/></td>
								<td>
									<s:if test="%{activo}">
										<s:text name="global.activo" />
									</s:if>
									<s:else>
										<s:text name="global.inactivo" />
									</s:else>
								</td>																
								<td>
									<s:url id="detalleURL" action="detalleViajero">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs"><s:text name="global.verdetalle" /></s:a>
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