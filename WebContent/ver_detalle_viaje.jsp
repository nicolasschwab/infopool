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
<body onload="inicializarRecorridoViaje('<s:property value="viaje.direccionOrigen" />','<s:property value="viaje.direccionDestino" />')">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="margentb2 row">
				<div class="col-md-8">
					<s:fielderror />
					<h3 class="tituloSeccion"><s:text name="viaje.detallelbl" /></h3>
					<div class="row">
						<div class="col-md-12">
							<p>
								<strong><s:text name="viaje.dirorigen" />:</strong>
								<s:property value="viaje.direccionOrigen" />
							</p>
							<p>
								<strong><s:text name="viaje.dirdestino" />:</strong>								
								<s:property value="viaje.direccionDestino" />																	
							</p>
							<s:if test="%{viaje.evento!=null}">
							<p>
								<strong><s:text name="viaje.eventolbl" />:</strong>
								<s:property value="viaje.evento.nombre"/>
							</p>
							</s:if>							
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong><s:text name="viaje.fechainicio" />:</strong>
								<s:date name="viaje.fechaInicio" format="dd/MM/YYYY" />
							</p>
						</div>
						<s:if test="%{viaje.evento==null}">
						<div class="col-md-6">
							<p>
								<strong><s:text name="viaje.fechafin" />:</strong>
								<s:if test="%{viaje.fechaFin==null}">
									<s:text name="viaje.viajeunico" />
								</s:if>
								<s:else>
									<s:date name="viaje.fechaFin" format="dd/MM/YYYY" />
								</s:else>								
							</p>
						</div>
						</s:if>
					</div>		
					<s:if test="%{viaje.evento==null}">								
					<div class="row">
						<div class="col-md-12">
							<p>
								<strong><s:text name="viaje.dias" />:</strong>								
								<s:property value="%{viaje.misDias()}" />								
							</p>
						</div>
					</div>
					</s:if>
					<div class="row">
						<div class="col-md-6">
							<p>
								<strong><s:text name="viaje.horapartida" />:</strong>
								<s:date name="viaje.horaPartida" format="HH:mm" />
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong><s:text name="viaje.horaregreso" />:</strong>
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
								<strong><s:text name="viaje.asientos" />:</strong>
								<s:property value="viaje.asientos" />
							</p>
						</div>
						<div class="col-md-6">
							<p>
								<strong><s:text name="viaje.asientosocupados" />:</strong>
								<s:property value="viaje.pasajeros.size()" />
							</p>
						</div>						
					</div>
					<h3 class="tituloSeccion"><s:text name="viaje.recorridogm" /></h3>
					<div class="row">
						<div class="col-md-12">
							<div id="map-canvas" style="height: 500px"></div>
						</div>
					</div>
				</div>

				<div class="col-md-4">					
					<div class="row">
						<h3 class="tituloSeccion"><s:text name="viaje.conductor" /></h3>
						<div class="col-md-10">					
							<p>
								<s:property value="viaje.conductor.nombre" />
								<s:property value="viaje.conductor.apellido" />
								<button type="button" class="btn btn-xs btn-info" data-toggle="modal" data-target=".myModal"><s:text name="viaje.perfilconductor" /></button>
							</p>
							<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{viaje.conductor.id}"></s:param>
							</s:url>" alt="foto conductor" class="img-thumbnail">
							
							<div class="modal fade myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-lg">
							    <div class="modal-content">
							        <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
							          <h4 class="modal-title" id="myLargeModalLabel"><s:text name="viaje.datosconductorlbl" /></h4>
							        </div>
							        <div class="modal-body">
							       		<p><strong><s:text name="viajero.nombre" />:</strong> <s:property value="usrlogueado.nombre" /> <s:property value="usrlogueado.apellido" /></p>
							  			<p><strong><s:text name="viajero.mail" />:</strong> <s:property value="usrlogueado.mail" /></p>
							  			<p><strong><s:text name="viajero.telefono" />:</strong> <s:property value="usrlogueado.telefono" /></p>
							  			<p><strong><s:text name="viajero.fechanacimiento" />:</strong> <s:date name="usrlogueado.fechaNacimiento" format="dd/MM/YYYY" /></p>
							  			<p><strong><s:text name="viajero.fechaingreso" />:</strong> <s:date name="usrlogueado.fechaIngresoSistema" format="dd/MM/YYYY" /></p>
							  			<p><strong><s:text name="viajero.calificacion" />:</strong> 4.2 de 5 (FALTAAAA)</p>
							  			<p><strong><s:text name="viajero.estado" />:</strong> <s:if test="usrlogueado.activo"><s:text name="global.activo" /></s:if><s:else><s:text name="global.inactivo" /></s:else></p>
							  			<p><strong><s:text name="viajero.preferencias" />:</strong> <s:property value="usrlogueado.preferenciasViaje" /></p>														          
							        </div>      
							    </div>
							  </div>
							</div>							
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">	
						<br>				
							<s:if test="%{viaje.conductor.id==usrlogueado.id}">
								<s:url id="edicionViajeURL" action="edicionViaje">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>
								<s:a href="%{edicionViajeURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.modificarbtn" /></s:a>
								
								<s:if test="%{viaje.evento==null}">
									<s:url id="asociacionEventoURL" action="asociacionEvento">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{asociacionEventoURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.asociareventobtn" /></s:a>
								</s:if>
								
								<s:if test="%{viaje.activo==true}">								
									<s:url id="cancelacionViajeURL" action="cancelarViaje" encode="true">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>																
									<s:a href="%{cancelacionViajeURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.cancelarbtn" /></s:a>
								</s:if>								
								
								<s:if test="%{viaje.pasajeros.size() < viaje.asientos}">
									<s:url id="solicitudesURL" action="solicitudesViaje" encode="true">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{solicitudesURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.solicitudesbtn" /></s:a>
								</s:if>								
							</s:if>
							<s:else>								
								<s:if test="%{esPasajero!=true}">
									<s:url id="registroSolicitudURL" action="registroSolicitudViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<s:a href="%{registroSolicitudURL}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.registrarsolicitudbtn" /></s:a>
								</s:if>					
								<s:else>						
									<a href="" class="btn btn-primary btn-block"><s:text name="viaje.eliminarsolicitudbtn" /></a>									
								</s:else>						
							</s:else>
							<s:url id="registroCalificar" action="calificaciones">
								<s:param name="idViaje" value="%{id}"></s:param>
							</s:url>						
							<s:a href="%{registroCalificar}" cssClass="btn btn-primary btn-block " role="button"><s:text name="viaje.calificarbtn" /></s:a>

							<s:url id="registroDenuncia" action="nuevaDenuncia">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>
							<s:a href="%{registroDenuncia}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.registrardenunciabtn" /></s:a>	
											
							<s:url id="mensaje" action="nuevoMensaje">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>					
							<s:a href="%{mensaje}" cssClass="btn btn-primary btn-block" role="button"><s:text name="viaje.registrarmensajebtn" /></s:a>
							<br><br>							
							
							<div class="row chat-window" id="chat_window_1">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-heading top-bar">
											<div class="col-md-8">
												<h3 class="panel-title"><s:text name="viaje.chatlbl" /></h3>
											</div>
										</div>											
										<s:iterator value="viaje.mensajes" >
											<div class="panel-body msg_container_base">
												<div class="row msg_container base_sent">
													<div class="col-md-11">
														<div class="messages msg_sent">
															<p><s:property value="mensaje" /></p>
															<time datetime=""><s:property value="viajero.nombre" />, <s:date name="fechaPublicacion" format="dd/MM/YYYY H:m" /> </time>
														</div>
													</div>
													<div class="col-md-2 avatar">
														<img src="images/foro.png" class=" img-responsive ">
													</div>
												</div>	
											</div>
										</s:iterator>
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
				</div>
			</div>
		</div>
	</div>	
	<%@ include file="views/footer.jsp"%>
</body>
</html>
