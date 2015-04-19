<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<!--<script type="text/javascript" src="js/Validaciones.js"></script>-->
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarEdicionViaje();">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>
	
	<div class="main_bg">
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Edición del Viaje</h3>
				<s:fielderror />				
				<s:form role="form" theme="simple" cssClass="form-signin" action="editarViaje">
					<s:if test="%{viaje.fechaFin==null}">
						<s:set var="dispVP" value="" />
						<s:set var="dispVD" value="%{'display:none'}" />
						<s:set var="listV" value="#{'vp':'Viaje Puntual','vd':'Viaje Diario'}" />
					</s:if>
					<s:else>
						<s:set var="dispVP" value="%{'display:none'}" />
						<s:set var="dispVD" value="" />
						<s:set var="listV" value="#{'vd':'Viaje Diario','vp':'Viaje Puntual'}" />
					</s:else>
					
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione el tipo de viaje que va a realizar</label>
								<br>
								<s:select cssClass="form-control" id="tipoViaje" name="tipoViaje" list="listV" onchange="javascript:cambiarTipoViaje()" />								
							</div>							
						</div>												
					</div>					
					<div id="vpPanel" class="form-group" style="<s:property value='dispVP'/>">
						<div class="row">							
							<div class="col-md-6">
								<label>Fecha del viaje</label>
								<div class='input-group date col-md-10 datepicker'>
									<s:date name="viaje.fechaInicio" id="fechaIni" format="yyyy-MM-dd"/>
									<s:textfield cssClass="form-control" name="fechaIniciop" data-date-format="YYYY-MM-DD" value="%{fechaIni}"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>							
						</div>
					</div>					
					<div id="vdPanel" class="form-group" style="<s:property value='dispVD'/>">						
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione los días en los cuales va a viajar</label><br>
								<label class="checkbox-inline">
									<s:checkbox name="viajediario" fieldValue="LUNES" value="%{viaje.diaSeleccionado('LUNES')}"/> Lunes
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="MARTES" value="%{viaje.diaSeleccionado('MARTES')}"/> Martes
								</label> 
								<label class="checkbox-inline">
									<s:checkbox name="viajediario" fieldValue="MIERCOLES" value="%{viaje.diaSeleccionado('MIERCOLES')}"/> Miercoles
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="JUEVES" value="%{viaje.diaSeleccionado('JUEVES')}"/> Jueves
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="VIERNES" value="%{viaje.diaSeleccionado('VIERNES')}"/> Viernes
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="SABADO" value="%{viaje.diaSeleccionado('SABADO')}"/> Sabado
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="DOMINGO" value="%{viaje.diaSeleccionado('DOMINGO')}"/> Domingo
								</label>
								<br>								
							</div>
						</div>
						<br>						
						<div class="row">
							<div class="col-md-6">
								<label>Fecha inicio del viaje</label>
								<div class='input-group date col-md-10 datepicker'>									
									<s:textfield cssClass="form-control"  name="fechaIniciod" data-date-format="YYYY-MM-DD" value="%{fechaIni}"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>
							<div class="col-md-6">
								<label>Fecha fin del viaje</label>
								<div class='input-group date col-md-10 datepicker'>									
									<s:date name="viaje.fechaFin" id="fechaFin" format="yyyy-MM-dd"/>
									<s:textfield cssClass="form-control"  name="fechaFin" data-date-format="YYYY-MM-DD" value="%{fechaFin}"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>
						</div>
						<br>
					</div>
					
					<div class="form-group">
						<div class="row">							
							<div class="col-md-6">
								<label>Hora de Partida</label>
								<div class='input-group date horaviaje col-md-10'>
									<s:date name="viaje.horaPartida" id="horaPar" format="HH:mm"/>
									<s:textfield cssClass="form-control" name="horaPartida" label="Hora de partida" data-date-format="HH:mm" value="%{horaPar}"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>							
							<div class="col-md-6">
								<label>Hora de Regreso</label>
								<div class='input-group date horaviaje col-md-10'>
									<s:date name="viaje.horaRegreso" id="horaReg" format="HH:mm"/>
									<s:textfield cssClass="form-control" name="horaRegreso" label="Hora de regreso" data-date-format="HH:mm" value="%{horaReg}"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<label>Asientos disponibles</label>								
								<s:textfield cssClass="form-control" name="asientos" label="ingrese la cantidad de asientos disponibles" value="%{viaje.asientos}"/>													
							</div>							
						</div>
					</div>

					<div class="form-group">
						<label>Direcci&oacute;n de origen</label>
						<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" label="Ingrese la direcci&oacute;n de origen" value="%{viaje.direccionOrigen}"/>						
					</div>

					<div class="form-group">
						<label>Direcci&oacute;n destino</label>
						<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" label="Ingrese la direcci&oacute;n de origen" value="%{viaje.direccionDestino}"/>						
					</div>

					<div class="form-group">
						<label>Recorrido GoogleMap</label>
						<button type="button" class="btn btn-default" onclick="javascript:calcRoute();">Trazar Recorrido</button>
						<br><br>
						<div id="map-canvas" style="height: 500px"></div>
					</div>
										
					<br>
					<s:hidden name="id" value="%{viaje.id}"></s:hidden>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Editar"/>
					</div>

				</s:form>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>