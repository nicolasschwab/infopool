<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp" %>
	<%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>
	
	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="row">				
				<div class="col-sm-12 padding-both-zero">
					<p class="header-title">Viajes</p>
					<div class="menu-seccion">
						<div class="row">
							<div class="col-sm-8">
								<div class="input-group"> 
									<input type="text" class="form-control" aria-label="Text input with segmented button dropdown" placeholder="Ciudad, Dirección, Evento, Facultad, Fecha, Conductor"> 
									<div class="input-group-btn"> 
										<button type="button" class="btn btn-default">Buscar</button> 
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
										 	Filtrar <span class="caret"></span>
										</button> 
										<ul class="dropdown-menu dropdown-menu-right"> 
											<li><a href="#">mis viajes conductor</a></li> 
											<li><a href="#">mis viajes pasajero</a></li> 
											<li><a href="#">historial de viaje</a></li> 
										</ul> 
									</div> 
								</div> 
							</div>
							<div class="col-sm-4">
								<p class="resultado-filtro">10 viajes</p>
							</div>
						</div>					      					    						
					</div>
				</div>
			</div>			
			<div class="main row">
				<div class="col-sm-3 padding-both-zero">
					<div class="panel-header-seccion">
						<h3>Mis Solicitudes</h3>		      		      
			      		<p class="descripcion-seccion">En esta sección usted podrá administrar sus solicitudes de viajes.</p>
					</div>
					<div class="panel-menu-seccion shadow-box">			    		
			      		<ul class="nav">
			      			<li><a href="RegistrarViaje" class="btn">Registrar un Viaje <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="BusquedaViaje" class="btn">Busqueda de Viajes <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="Solicitudes" class="btn">Solicitudes de Viajes <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="Calificaciones" class="btn">Calificaciones <span class="fa fa-chevron-right"></span></a></li>
			      		</ul>
			      	</div>			      	
			      	<div class="panel-menu-seccion shadow-box">	
			      		<p>Ponte al 100%</p>		    		
			      		<ul class="nav">
			      			<li><a href="RegistrarViaje" class="btn">Registrar un Viaje <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="BusquedaViaje" class="btn">Busqueda de Viajes <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="Solicitudes" class="btn">Solicitudes de Viajes <span class="fa fa-chevron-right"></span></a></li>
			      			<li><a href="Calificaciones" class="btn">Calificaciones <span class="fa fa-chevron-right"></span></a></li>
			      		</ul>
			      	</div>			      				      	
			    </div>
				<s:fielderror />
				<div class="col-sm-9 padding-right-zero">
					<div class="contenido-seccion shadow-box">
						<h3 class="titulo-contenido"><s:text name="solicitud.titulo" /></h3>
						<table class="table" id="datatable">
							<thead>
								<tr>
									<th><s:text name="solicitud.nombreViajero" /></th>
									<th><s:text name="solicitud.fecha" /></th>
									<th><s:text name="solicitud.estado" /> Actual</th>
									<th><s:text name="global.acciones" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="listaSolicitudesViajes">
								<tr>
									<td><s:property value="viajero.nombre" /> <s:property value="viajero.apellido" /></td>
									<td><s:date name="fechaInicioSolicitud" format="dd/MM/YYYY" /> a las <s:date name="fechaInicioSolicitud" format="HH:mm" /> hs.</td>
									<td><s:property value="estadoSolicitud" /></td>
									<td>
										<s:if test="%{user.id==viaje.conductor.id}">
											<s:url id="rechazarSolicitud" action="RechazarSolicitudViaje">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:url id="aceptarSolicitud" action="AceptarSolicitud">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:if test="%{estadoSolicitud.name()=='PENDIENTE'}">
												<s:a href="%{aceptarSolicitud}" cssClass="btn btn-default btn-xs btn-success"><s:text name="solicitud.aceptar" /></s:a>
												<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger"><s:text name="solicitud.rechazar" /></s:a>
											</s:if>
											<s:elseif test="%{estadoSolicitud.name()=='ACEPTADO'}">
												<s:a href="%{rechazarSolicitud}" cssClass="btn btn-default btn-xs btn-danger"><s:text name="solicitud.rechazar" /></s:a>
											</s:elseif>
											<s:elseif test="%{estadoSolicitud.name()=='RECHAZADO'}">
												<s:text name="solicitud.rechazada" />
											</s:elseif>
										</s:if>
										<s:else>
											<s:url id="cancelarSolicitudViaje" action="cancelarSolicitudViaje">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{cancelarSolicitudViaje}" cssClass="btn btn-default btn-xs btn-success">Cancelar solicitud</s:a>
										</s:else>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>