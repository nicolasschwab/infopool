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
			<div class="margentb2 row">
				<div class="col-sm-12">
					<s:fielderror />
					<h3 class="tituloSeccion">						
						<s:property value="viaje.direccionOrigen" /> <s:property value="viaje.tramoViaje"/> <s:property value="viaje.direccionDestino" />
					</h3>
					<div class="row box-detail">
						<div class="col-sm-7 box-detail-map">
							<div id="map-canvas" style="height: 300px"></div>
						</div>
						<div class="col-sm-5">
							<div class="box-detail-conductor">								
								<img src="<s:url action="ImageAction">
									<s:param name="id" value="%{viaje.conductor.id}"></s:param>
								</s:url>" alt="foto conductor">															
								CONDUCTOR: <span><s:property value="viaje.conductor.nombre"/> <s:property value="viaje.conductor.apellido"/></span><br>
								<button type="button" class="btn btn-xs btn-info" data-toggle="modal" data-target=".myModal"><s:text name="viaje.perfilconductor" /></button>
								
								<div class="modal fade myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-lg">
								    <div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
								          <h4 class="modal-title" id="myLargeModalLabel"><s:text name="viaje.datosconductorlbl" /></h4>
								        </div>
								        <div class="modal-body">
								       		<p><strong><s:text name="viajero.nombre" />:</strong> <s:property value="viaje.conductor.nombre" /> <s:property value="viaje.conductor.apellido" /></p>
								  			<p><strong><s:text name="viajero.mail" />:</strong> <s:property value="viaje.conductor.mail" /></p>
								  			<p><strong><s:text name="viajero.telefono" />:</strong> <s:property value="viaje.conductor.telefono" /></p>
								  			<p><strong><s:text name="viajero.fechanacimiento" />:</strong> <s:date name="viaje.conductor.fechaNacimiento" format="dd/MM/YYYY" /></p>
								  			<p><strong><s:text name="viajero.fechaingreso" />:</strong> <s:date name="viaje.conductor.fechaIngresoSistema" format="dd/MM/YYYY" /></p>
								  			<p><strong><s:text name="viajero.calificacion" />:</strong> <s:property value="viaje.conductor.calificacion" />/5</p>
								  			<p><strong><s:text name="viajero.estado" />:</strong> <s:if test="viaje.conductor.activo"><s:text name="global.activo" /></s:if><s:else><s:text name="global.inactivo" /></s:else></p>
								  			<p><strong><s:text name="viajero.preferencias" />:</strong> <!--<s:property value="viaje.conductor.preferenciasViaje" />--></p>														          
								        </div>      
								    </div>
								  </div>
								</div>								
							</div>
							<div class="col-sm-12 box-detail-descripcion">
								<s:property value="viaje.descripcion"/>
							</div>
							<div class="col-sm-5 box-detail-finicio">
								<p>
								<strong><s:text name="viaje.fechainicio" />:</strong>
								<s:date name="viaje.fechaInicio" format="dd/MM/YYYY" />
								</p>
							</div>
							<div class="col-sm-5 box-detail-distancia">
								<p>
									<strong><s:text name="viaje.distancia" /> del recorrido:</strong>
									<s:property value="viaje.kilometros"/> km								
								</p>
							</div>		
						</div>												
						<div class="col-sm-12">
							<div class="box-detail-divider"></div>
							<div class="row box-detail-frecuencias">
								<h2>Frecuencias</h2>
								<s:iterator value="viaje.frecuencias" >
									<div class="box-detail-frecuencia">
										<strong>Día de Semana:</strong> <s:property value="diaFrecuencia"/> 
										<strong>Hora Partida:</strong> <s:date name="horaPartida" format="HH:mm" /> <strong>Hora Regreso:</strong> <s:date name="horaRegreso" format="HH:mm" />
										<strong>Asientos Disponibles:</strong> <s:property value="asientosDisponibles"/>
										<strong>Estado:</strong> <s:property value="estadoFrecuencia"/>
										<s:if test="%{soyConductor}">
											<s:if test="%{soyPasajero}">											
												<s:url id="registroSolicitudURL" action="RegistroSolicitudViaje">
													<s:param name="idFrecuenciaViaje" value="%{id}"></s:param>
												</s:url>
												<s:a href="%{registroSolicitudURL}" cssClass="btn btn-primary btn-sm" role="button">Unirme</s:a>
											</s:if>
											<s:else>
												operaciones del conductor de viaje
											</s:else>
										</s:if>		
										<s:else>
												
										</s:else>																		
									</div>
								</s:iterator>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="box-detail-divider"></div>
							<div class="row box-detail-solicitudes">
								<h2>Solicitudes</h2>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="box-detail-divider"></div>
							<div class="row box-detail-foro">
								<h2>Foro de Mensajes</h2>
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