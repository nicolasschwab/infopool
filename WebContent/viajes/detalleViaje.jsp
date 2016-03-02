<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.Viaje"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<link href="css/foro.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/foro.js"></script>
</head>
<body onload="inicializarRecorridoViaje('<s:property value="viaje.direccionOrigen" />','<s:property value="viaje.direccionDestino" />')">

	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="row">
				<ol class="breadcrumb">
  					<li><a href="#">< Regresar</a></li>
  				</ol>
				<div class="col-sm-12">					
					<s:fielderror />
					<div class="row box-detail">						
						<div class="col-sm-7 box-detail-map">
							<div id="map-canvas" style="height: 300px"></div>
						</div>
						<div class="col-sm-5">
							<div class="box-detail-conductor">
								<div class="col-sm-4 img-conductor">								
									<img src="<s:url action="ImageAction">
										<s:param name="id" value="%{viaje.conductor.id}"></s:param>
									</s:url>" alt="foto conductor">
								</div>								
								<div class="col-sm-8 ">							
									<span>CONDUCTOR</span>					       										  			
						  			<p><s:property value="viaje.conductor.nombre"/> <s:property value="viaje.conductor.apellido"/></p>
						  			<p><strong>Edad:</strong> <s:date name="viaje.conductor.fechaNacimiento" format="dd/MM/YYYY" /></p>
						  			<p><strong><s:text name="viajero.calificacion" />:</strong> <s:property value="viaje.conductor.calificacion" />/5</p>
						  			<p><strong><s:text name="viajero.preferencias" />:</strong> <!--<s:property value="viaje.conductor.preferenciasViaje" />--></p>					  			
					  			</div>
					  			<button type="button" class="btn btn-primary">Enviar Mensaje</button>
							</div>							
						</div>
						<div class="col-sm-7">
							<div class="box-detail-divider"></div>
							<div class="row box-detail-frecuencias">
								<h2>Detalle del Viaje</h2>
								<p><strong>Fecha del viaje: </strong><s:date name="viaje.fechaInicio" format="dd/MM/YYYY" /></p>
								<p><strong>Dirección de Origen: </strong><s:property value="viaje.direccionOrigen" /></p>
								<p><strong>Dirección de Destino: </strong><s:property value="viaje.direccionDestino" /></p>
							</div>
						</div>
						<div class="col-sm-5">
							<div class="box-detail-divider"></div>
							<h2>Auto</h2>
						</div>												
						<div class="col-sm-7">
							<div class="box-detail-divider"></div>
							<div class="row box-detail-frecuencias">								
								<h2>Frecuencias</h2>
								<s:iterator value="viaje.frecuencias" >
									<div class="box-detail-frecuencia">
										<div class="row">
											<div class="col-sm-8 box-frecuencia-datos">
												<p><s:property value="diaFrecuencia"/></p> 
												<p><strong>Hora Partida:</strong> <s:date name="horaPartida" format="HH:mm" /> <strong>Hora Regreso:</strong> <s:date name="horaRegreso" format="HH:mm" /></p>
												<p><strong>Asientos Disponibles:</strong> <s:property value="asientosDisponibles"/></p>
												<p><strong>Estado: </strong><s:property value="estadoFrecuencia"/></p>
											</div>
											<div class="col-sm-4 box-frecuencia-acciones">
												<s:if test="%{soyViajero}">
													<s:if test="%{!soyConductor}">
														<s:if test="%{!soyPasajero}">											
															<s:url id="registroSolicitudURL" action="RegistroSolicitudViaje">
																<s:param name="idFrecuenciaViaje" value="%{id}"></s:param>
															</s:url>
															<s:a href="%{registroSolicitudURL}" cssClass="btn btn-primary btn-sm" role="button">Unirme</s:a>
														</s:if>
														<s:else>
															operaciones pasajero
														</s:else>
													</s:if>
													<s:else>
														<s:url id="solicitudesFrecuenciaURL" action="SolicitudesFrecuenciaViaje">
															<s:param name="idFrecuenciaViaje" value="%{id}"></s:param>
														</s:url>
														<s:a href="%{solicitudesFrecuenciaURL}" cssClass="btn btn-primary btn-sm" role="button">Solicitudes <span class="badge">3</span></s:a>
													</s:else>
												</s:if>		
												<s:else>
													operaciones del admin													
												</s:else>
											</div>																		
										</div>
									</div>
								</s:iterator>
							</div>
						</div>
						<div class="col-sm-5">
							<div class="box-detail-divider"></div>
							<h2>Foro de pasajeros</h2>
						</div>																	
					</div>
				</div>
			</div>
		</div>
	</div>	
	<%@ include file="../views/footer.jsp"%>
</body>
</html>