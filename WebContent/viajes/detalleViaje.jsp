<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.Viaje"%>
<%@ page language="java" import="model.Usuario"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarRecorridoViaje('<s:property value="viaje.puntosTrayecto"/>')">

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
					  			<s:form role="form" theme="simple" action="nuevoMensaje" method="post">
					  				<s:hidden name="id" value="%{viaje.id}"/> 
					  				<s:submit  value="Enviar Mensaje" cssClass="btn btn-primary"/>
					  			</s:form>
					  			<s:if test="%{soyConductor}">
					  				<s:form role="form" theme="simple" action="EdicionViaje" method="post">
					  					<s:hidden name="id" value="%{viaje.id}"/>
					  					<s:submit value="Editar Viaje" cssClass="btn btn-primary"/>
					  				</s:form>
					  			</s:if>
					  			<s:if test="%{soyPasajero | soyConductor}">
						  			<s:form role="form" theme="simple" action="calificaciones" method="post">
						  				<s:hidden name="idViaje" value="%{viaje.id}"/> 
						  				<s:submit  value="Calificar" cssClass="btn btn-primary"/>
						  			</s:form>
					  			</s:if>
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
															<s:if test="%{asientosDisponibles>0}">																									
																<s:url id="registroSolicitudURL" action="RegistroSolicitudViaje">
																	<s:param name="idFrecuenciaViaje" value="%{id}"></s:param>
																</s:url>
																<s:if test="%{solicito=='noSolicito'}">
																	<s:a href="%{registroSolicitudURL}" cssClass="btn btn-primary btn-sm" role="button">Unirme</s:a>
																</s:if>
																<s:if test="%{solicito=='PENDIENTE'}">
																	<p><strong><s:text name="frecuencia.solicitud.pendiente" /></strong></p>
																</s:if>
																<s:if test="%{solicito=='RECHAZADA'}">
																	<p><strong><s:text name="frecuencia.solicitud.rechazada" /></strong></p>
																</s:if>
																<s:if test="%{solicito=='CANCELADA'}">
																	<p><strong><s:text name="frecuencia.solicitud.cancelada" /></strong></p>
																</s:if>
																<s:if test="%{solicito=='ACEPTADA'}">
																	<p><strong><s:text name="frecuencia.solicitud.aceptada" /></strong></p>
																</s:if>										
															</s:if>
															<s:else>
																asientos ocupados
															</s:else>
														</s:if>
														<s:else>
															operaciones pasajero
														</s:else>
													</s:if>
													<s:else>
														<s:url id="solicitudesFrecuenciaURL" action="SolicitudesFrecuenciaViaje">
															<s:param name="idFrecuenciaViaje" value="%{id}"></s:param>
														</s:url>
														<s:a href="%{solicitudesFrecuenciaURL}" cssClass="btn btn-primary btn-sm" role="button">Solicitudes</s:a>
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
							<s:if test="%{soyPasajero | soyConductor}">
								<h2>Foro de pasajeros</h2>
								<div class="row">
									<div class="col-md-10">
										<div class="row chat-window" id="chat_window_1">
											<div class="col-md-12">
												<div class=" panel-default">
													<div class="panel-heading top-bar">
														<div class="col-md-8">
															<h3 class="panel-title">
															<s:iterator value="viaje.foroViaje.participantesConversacion">
																<s:property value="nombre" />,
															</s:iterator>
															</h3>
														</div>
													</div>
													<div class="panel">										
														<s:iterator value="viaje.foroViaje.mensajes" >
															<div class="panel-body msg_container_base">
																<div class="row msg_container base_sent">
																	<div class="textoForo">
																			<div class="messages msg_sent">												
																		<p><s:property value="detalle" /></p>																																			
																	</div>
																</div>
																	<div class="col-md-2 avatar " id="fotoForo">									
																	<img title="${emisor.nombre}, <s:date name="fechaPublicacion" format="dd/MM/YYYY H:m" />" src="<s:url action="ImageAction">
																		<s:param name="id" value="%{emisor.id}"></s:param>
																	</s:url>" alt="foto conductor" class=" img-responsive ">
																</div>																
															</div>	
															</div>
														</s:iterator>
													</div>
												</div>
												<div class="panel-footer">
													<div>
														<s:form role="form" action="enviarMensajeForo">
															<s:hidden  name="viajeId" value="%{viaje.id}"> </s:hidden>
															<input id="btn-input" name="detalle" type="text" class="form-control input-sm chat_input" placeholder="<s:text name='viaje.chatmsg' />" /> 
															<span class="input-group-btn"><s:submit cssClass="btn btn-primary" value="%{getText('global.enviar')}"/></span>
														</s:form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</s:if>
						</div>																	
					</div>
				</div>
			</div>
		</div>
	</div>	
	<%@ include file="../views/footer.jsp"%>
</body>
</html>