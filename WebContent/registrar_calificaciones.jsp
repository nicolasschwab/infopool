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

	
	<jsp:include page="views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="main row">				
				<h3 class="tituloSeccion"><s:text name="calificaciones.titulo"></s:text></h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th style="width:10%"><s:text name="calificaciones.imagen"></s:text></th>
							<th style="width:25%"><s:text name="viajero.nombre"></s:text></th>
							<th style="width:25%"><s:text name="viajero.mail"></s:text></th>
							<th style="width:20%"><s:text name="calificaciones.actual"></s:text></th>														
							<th style="width:20%"><s:text name="global.acciones"></s:text></th>
						</tr>
					</thead>
					<tbody>						
						<s:iterator value="pasajeros">
							<tr>								
								<td>
									<img src="<s:url action="ImageAction">
											  <s:param name="id" value="%{id}"></s:param>
											  </s:url>"  height="80" />
								</td>
								<td>
									<s:property value="nombre" /> <s:property value="apellido" />									
								</td>
								<td>
									<s:property value="mail" />									
								</td>
								<td>
									<s:property value="calificacion" />
								</td>
								<td>
									<form action="calificarViaje" method="post">
										<div class="form-group">
											<div class="col-md-6 calificacion">											
												<input class="radioCalificacion" id="radio1" type="radio" name="calificacionnro" value="5">
											    <label class="labelCalificacion" for="radio1">★</label>
											    <input class="radioCalificacion" id="radio2" type="radio" name="calificacionnro" value="4">
											    <label class="labelCalificacion" for="radio2">★</label>
											    <input class="radioCalificacion" id="radio3" type="radio" name="calificacionnro" value="3">
											    <label class="labelCalificacion" for="radio3">★</label>
											    <input class="radioCalificacion" id="radio4" type="radio" name="calificacionnro" value="2">
											    <label class="labelCalificacion" for="radio4">★</label>
											    <input class="radioCalificacion" id="radio5" type="radio" name="calificacionnro" value="1">
											    <label class="labelCalificacion" for="radio5">★</label>
												<input type="hidden" name="idPasajero" value="<s:property value='id' />" />
												<input type="hidden" name="idViaje" value="<s:property value='viaje.id' />" />
											</div>
											<div class="col-md-3">
											<input class="btn btn-primary" type="submit" value="calificar">
											</div>											
										</div>
									</form>
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
