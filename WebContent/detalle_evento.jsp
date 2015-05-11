<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="calcularUbicacion('<s:property value="evnt.ubicacion" />')">
	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="2" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="main row">
				<div class="col-md-12">
					<h3 class="tituloSeccion"><s:text name="evento.detallelbl" /></h3>
					<div class="col-md-9">						
						<div class="row">
							<div class="col-md-12">
								<p>
									<strong><s:text name="evento.nombre" />:</strong>
									<s:property value="evnt.nombre" />
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<p>
									<strong><s:text name="evento.fecha" /></strong>
									<s:date name="evnt.fechaHora" format="dd/MM/yyyy" />
									a las
									<s:date name="evnt.fechaHora" format="HH:mm" />
									Hs.
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<p>
									<strong><s:text name="evento.ubicacion" />:</strong>
									<s:property value="evnt.ubicacion" />
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<p>
									<strong><s:text name="evento.web" />:</strong>
									<a href="http://<s:url value='%{evnt.web}' />" target="_blank"><s:property value="evnt.web" /></a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<br>
						<s:url id="edicionEventoURL" action="edicionEvento">
							<s:param name="id" value="%{id}"></s:param>
						</s:url>
						<s:a href="%{edicionEventoURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="evento.edicionbtn" /></s:a>
						<s:if test="%{activo}">						
							<s:url id="cancelacionEventoURL" action="cancelarEvento" encode="true">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>																						
							<s:a href="%{cancelacionEventoURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="evento.cancelarbtn" /></s:a>
						</s:if>
					</div>
				</div>
				
				<div class="col-md-12">
					<h3 class="tituloSeccion"><s:text name="evento.ubicaciongm" /></h3>
					<div class="row">
						<div class="col-md-12">
							<div id="map-canvas" style="height: 500px"></div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>