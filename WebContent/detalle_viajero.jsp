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
		<!-- start main -->
		<div class="container">
			<div class="main row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-2">
							<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{viajero.id}"></s:param>
							</s:url>" />
						</div>
						<div class="col-md-5">
							<p>
								<strong>Nombre:</strong>
								<s:property value="viajero.nombre" />
								<s:property value="viajero.apellido" />
							</p>
							<p>
								<strong>Mail:</strong>
								<s:property value="viajero.mail" />
							</p>
							<p>
								<strong>Telefono:</strong>
								<s:property value="viajero.telefono" />
							</p>
							<p>
								<strong>Fecha Nacimiento:</strong>
								<s:date name="viajero.fechaNacimiento" format="dd/MM/YYYY" />
							</p>
						</div>
						<div class="col-md-5">
							<p>
								<strong>Fecha de ingreso al sistema:</strong>
								<s:date name="viajero.fechaIngresoSistema" format="dd/MM/YYYY" />
							</p>
							<p>
								<strong>Calificacion:</strong> 4.2 de 5 (FALTAAAA)
							</p>
							<p>
								<strong>Estado:</strong>
								<s:if test="viajero.activo">Activo</s:if>
								<s:else>Inactivo</s:else>
							</p>
							<p>
								<strong>Preferencias del viajero:</strong>
								<s:property value="viajero.preferenciasViaje" />
							</p>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4 centrar">
							<s:if test="%{viajero.activo}">
								<s:url id="cancelacionViajeroURL" action="desactivarViajero">
									<s:param name="id" value="%{viajero.id}"></s:param>
								</s:url>							
								<s:a href="%{cancelacionViajeroURL}" cssClass="btn btn-primary" role="button">Cancelar suscripción</s:a>
							</s:if> 
							<s:else>
								<s:url id="activacionViajeroURL" action="activarViajero">
									<s:param name="id" value="%{viajero.id}"></s:param>
								</s:url>							
								<s:a href="%{activacionViajeroURL}" cssClass="btn btn-primary" role="button">Activar suscripción</s:a>
							</s:else>
							<a href="" class="btn btn-primary disabled" role="button">Ver Denuncias</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end main -->
	<%@ include file="views/footer.jsp"%>
</body>
</html>