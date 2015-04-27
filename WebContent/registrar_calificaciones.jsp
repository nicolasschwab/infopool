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
					<s:a href="javascript:history.back();" cssClass="btn btn-primary">Regresar</s:a>
				</div>
				<h3 class="tituloSeccion">Calificaciones para el viaje</h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th style="width:20%">Imagen</th>
							<th style="width:15%">Nombre</th>
							<th style="width:20%">Mail</th>
							<th style="width:25%">Calificacion Actual</th>							
							<th >Acciones</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="pasajeros">
							<tr>
								<td>
									<img src="<s:url action="ImageAction">
											  <s:param name="id" value="%{id}"></s:param>
											  </s:url>"  height="100">
								</td>
								<td>
									<s:property value="nombre" /> <s:property value="apellido" />									
								</td>
								<td>
									<s:property value="mail" />									
								</td>
								<td>
									<s:property value="calificacionActual" />
								</td>
								<td>
									<s:select list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5' }" name="valores" />
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