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
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="main row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-2">
							<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{viajero.id}"></s:param>
							</s:url>" class="img-thumbnail"/>
						</div>
						<div class="col-md-5">
							<p>
								<strong><s:text name="viajero.nombre" />:</strong>
								<s:property value="viajero.nombre" />
								<s:property value="viajero.apellido" />
							</p>
							<p>
								<strong><s:text name="viajero.mail" />:</strong>
								<s:property value="viajero.mail" />
							</p>
							<p>
								<strong><s:text name="viajero.telefono" />:</strong>
								<s:property value="viajero.telefono" />
							</p>
							<p>
								<strong><s:text name="viajero.fechanacimiento" />:</strong>
								<s:date name="viajero.fechaNacimiento" format="dd/MM/YYYY" />
							</p>							
						</div>
						<div class="col-md-5">
							<p>
								<strong><s:text name="viajero.fechaingreso" />:</strong>
								<s:date name="viajero.fechaIngresoSistema" format="dd/MM/YYYY" />
							</p>
							<p>
								<strong><s:text name="viajero.calificacion" />:</strong> <s:property value="viajero.calificacionActual()"/>/5 (<s:property value="viajero.misCalificacionesRecibidas.size()" /> <s:text name="viajero.calificaciones" />)
							</p>
							<p>
								<strong><s:text name="viajero.estado" />:</strong>
								<s:if test="viajero.activo"><s:text name="global.activo" /></s:if>
								<s:else><s:text name="global.inactivo" /></s:else>
							</p>
							<p>
								<strong><s:text name="viajero.preferencias" />:</strong>
								<s:property value="viajero.preferenciasViaje" />
							</p>
							<p>
								<strong><s:text name="viajero.viajesrealizados"/>: </strong>
								<s:property value="viajero.misViajesPasajero.size()" />  <s:text name="viajero.viajespasajero" /> -  
								<s:property value="viajero.misViajesConductor.size()" /> <s:text name="viajero.viajesconductor" />
							</p>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-3 centrar">
							<s:if test="%{viajero.activo}">
								<s:url id="cancelacionViajeroURL" action="desactivarViajero">
									<s:param name="id" value="%{viajero.id}"></s:param>
								</s:url>							
								<s:a href="%{cancelacionViajeroURL}" cssClass="btn btn-primary" role="button"><s:text name="viajero.cancelarbtn" /></s:a>
							</s:if> 
							<s:else>
								<s:url id="activacionViajeroURL" action="activarViajero">
									<s:param name="id" value="%{viajero.id}"></s:param>
								</s:url>							
								<s:a href="%{activacionViajeroURL}" cssClass="btn btn-primary" role="button"><s:text name="viajero.activarbtn" /></s:a>
							</s:else>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>