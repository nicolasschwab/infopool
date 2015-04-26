<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.Viaje"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="css/foro.css" rel='stylesheet' type='text/css' />
<script type="text/javascript" src="js/foro.js"></script>
</head>
<body
	onload="inicializarRecorridoViaje('<s:property value="viaje.direccionOrigen" />','<s:property value="viaje.direccionDestino" />')">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="margentb2 row">
				<div class="col-md-8">
					<s:fielderror />
					<h3 class="tituloSeccion">Detalle del Viaje</h3>
					<div class="row">
						<div class="col-md-12">
							<p>
								<strong>Dirección de origen:</strong>
								<s:property value="viaje.direccionOrigen" />
							</p>
							<p>
								<strong>Dirección de destino:</strong>
								<s:property value="viaje.direccionDestino" />
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong>Hora de partida:</strong>
								<s:date name="viaje.horaPartida" format="HH:mm" />
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong>Hora de regreso:</strong>
								<s:if test="%{viaje.horaRegreso==null}">
									Sin regreso
								</s:if>
								<s:else>
									<s:date name="viaje.horaRegreso" format="HH:mm" />
								</s:else>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong>Fecha de Inicio:</strong>
								<s:date name="viaje.fechaInicio" format="dd/MM/YYYY" />
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong>Fecha de Fin:</strong>
								<s:if test="%{viaje.fechaFin==null}">
									Viaje único
								</s:if>
								<s:else>
									<s:date name="viaje.fechaFin" format="dd/MM/YYYY" />
								</s:else>								
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<p>
								<strong>D&iacute;as:</strong>								
								<s:property value="%{viaje.misDias()}" />								
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong>Asientos:</strong>
								<s:property value="viaje.asientos" />
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong>Asientos ocupados:</strong>
								<s:property value="viaje.pasajeros.size()" />
							</p>
						</div>						
					</div>
					<h3 class="tituloSeccion">Recorrido</h3>
					<div class="row">
						<div class="col-md-12">
							<div id="map-canvas" style="height: 500px"></div>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<h3 class="tituloSeccion">Conductor</h3>
					<div class="row">
						<div class="col-md-10">					
							<p>
								<s:property value="viaje.conductor.nombre" />
								<s:property value="viaje.conductor.apellido" />
								(<a href="ver_perfil.php">ver perfil</a>)
							</p>
							<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{viaje.conductor.id}"></s:param>
							</s:url>" alt="foto conductor" class="img-thumbnail">							
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">	
						<br>				
							<s:if test="%{viaje.conductor.id==usrlogueado.id}">
								<s:url id="edicionViajeURL" action="edicionViaje">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>
								<s:a href="%{edicionViajeURL}" cssClass="btn btn-primary btn-block" role="button">Modificar Viaje</s:a>
								
								<s:if test="%{viaje.evento==null}">
									<s:url id="asociacionEventoURL" action="asociacionEvento">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{asociacionEventoURL}" cssClass="btn btn-primary btn-block" role="button">Asociar evento</s:a>
								</s:if>
								
								<s:url id="cancelacionViajeURL" action="cancelarViaje" encode="true">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>																
								<s:a href="%{cancelacionViajeURL}" cssClass="btn btn-primary btn-block" role="button">Cancelar Viaje</s:a>								
								
								<s:url id="solicitudesURL" action="solicitudesViaje" encode="true">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>
								<s:a href="%{solicitudesURL}" cssClass="btn btn-primary btn-block" role="button">Solicitudes</s:a>								
							</s:if>
							<s:else>								
								<s:if test="%{esPasajero!=true}">
									<s:url id="registroSolicitudURL" action="registroSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{registroSolicitudURL}" cssClass="btn btn-primary btn-block" role="button">Registrar Solicitud</s:a>
								</s:if>					
								<s:else>						
									<a href="" class="btn btn-primary btn-block">Eliminar Solicitud</a>									
								</s:else>						
							</s:else>							
							<a href="" class="btn btn-primary btn-block disabled" role="button">Calificar</a>
							<s:url id="registroDenuncia" action="nuevaDenuncia">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>
							<s:a href="%{registroDenuncia}" cssClass="btn btn-primary btn-block" role="button">Registrar Denuncia</s:a>	
											
							<s:url id="mensaje" action="nuevoMensaje">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>					
							<s:a href="%{mensaje}" cssClass="btn btn-primary btn-block" role="button">Registrar Mensaje a Usuario</s:a>
							<br><br>							
							<div class="container">
								<div class="row chat-window col-xs-5 col-md-3"
									id="chat_window_1">
									<div class="col-xs-12 col-md-12">
										<div class="panel panel-default">
											<div class="panel-heading top-bar">
												<div class="col-md-8 col-xs-8">
													<h3 class="panel-title">
														 Chat -	Foro
													</h3>
												</div>
											</div>											
											  <s:iterator value="viaje.mensajes" >
												<div class="panel-body msg_container_base">
													<div class="row msg_container base_sent">
														<div class="col-md-10 col-xs-10">
															<div class="messages msg_sent">
																<p><s:property value="mensaje" /></p>
																<time datetime="2009-11-13T20:00"> <s:property value="viajero.nombre" />  <s:date name="fechaPublicacion" format="dd/MM/YYYY" /> </time>
															</div>
														</div>
														<div class="col-md-2 col-xs-2 avatar">
															<img
																src="images/foro.png"
																class=" img-responsive ">
														</div>
													</div>	
												</div>
											</s:iterator>
										</div>
										<div class="panel-footer">
											<div class="input-group">
												<s:form role="form" action="enviarMensajeForo">
													<s:hidden  name="viajeId" value="%{viaje.id}"> </s:hidden>
													<input id="btn-input" name="detalle" type="text"
														class="form-control input-sm chat_input"
														placeholder="Escriba su mensaje aquí.." /> 
													<span class="input-group-btn">
														<s:submit cssClass="btn btn-primary" value="Enviar"/>
													</span>
												</s:form>
											</div>
										</div>
									</div>
								</div>
							</div>														
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<%@ include file="views/footer.jsp"%>
</body>
</html>