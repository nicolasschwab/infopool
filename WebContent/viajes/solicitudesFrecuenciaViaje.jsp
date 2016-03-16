<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.FrecuenciaViaje"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<link href="css/foro.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/foro.js"></script>
</head>
<body onload="inicializarRegistroViaje()">

	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="row">
				<ol class="breadcrumb">
  					<li><a href="#" onclick="window.history.back();return false;">&lt; Regresar</a></li>
  				</ol>
				<div class="col-sm-12">
					<s:fielderror />
					<div class="row box-detail">
						<h2>Solicitudes de la Frecuencia</h2>
						<div class="media">
							<div class="col-md-5">	
								<s:iterator value="listaSolicitudes" >
															
									<s:url id="aceptarSolicitud" action="AceptarSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:url id="rechazarSolicitud" action="RechazarSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:url id="cancelarSolicitud" action="CancelarSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									
									<div class="row divisionSolicitudes">
										<div class="col-md-6">
											<a class="linkSolicitudes" href="#">									    	
										    	<img class="media-object imagenSolicitudes" src="<s:url action="ImageAction">
													<s:param name="id" value="%{viajero.id}"></s:param>
												</s:url>" alt="conductor" width="90" height="90">
										    </a>
										    <div class="datosViajeroSolicitud">																			
												<div><span><i class="fa fa-user"></i></span><s:property value="%{viajero.usuario}"/></div>
												<div><span><i class="fa fa-user"></i></span><s:property value="%{viajero.calificacion}"/>/5</div>
												<div><span></span> Icono de pref</div>
											</div>
										</div>
										<div class="col-md-6 datosSolicitud">
											<div><input class="btn btn-primary" value="ver propuesta de recorrido" type="button"/></div>
											<div><span><i class="fa fa-calendar"></i></span><s:date name="fechaInicioSolicitud" format="dd/MM/YYYY"/></div> 
											<div><s:property value="estadoSolicitud" /></div>
											<s:if test="%{estadoSolicitud.name()=='PENDIENTE'}">
												<s:a href="%{aceptarSolicitud}" cssClass="btn btn-default btn-xs btn-success"><s:text name="solicitud.aceptar" /></s:a>
												<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger"><s:text name="solicitud.rechazar" /></s:a>
											</s:if>
											<s:elseif test="%{estadoSolicitud.name()=='ACEPTADA'}">
												<s:a href="%{cancelarSolicitud}" cssClass="btn btn-default btn-xs btn-danger">Cancelar</s:a>
											</s:elseif>
											<s:elseif test="%{estadoSolicitud.name()=='RECHAZADA'}">
												<s:text name="solicitud.rechazada" />
											</s:elseif>
										</div>
									</div>
								</s:iterator>	
							</div>
							<div class="col-md-7 box-detail-map">									
								<div id="map-canvas" style="min-height: 500px"></div>									
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../views/footer.jsp"%>
</body>
</html>