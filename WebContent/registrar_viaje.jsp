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
<body onload="inicializarRegistroViaje();cambiarTipoViaje()">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>
	
	<div class="main_bg">
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Registro de Viaje</h3>
				<s:fielderror />				
				<s:form role="form" theme="simple" cssClass="form-signin" action="registrarViaje">
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione el tipo de viaje que va a realizar</label>
								<br>
								<s:select cssClass="form-control" id="tipoViaje" name="tipoViaje" list="#{'vp':'Viaje Puntual','vd':'Viaje Diario'}" onchange="javascript: cambiarTipoViaje()" />								
							</div>							
						</div>												
					</div>					
					<div id="vpPanel" class="form-group">
						<div class="row">							
							<div class="col-md-6">
								<label>Fecha del viaje</label>
								<div class='input-group date col-md-10 datepicker'>
									<s:textfield cssClass="form-control" name="fechaIniciop" data-date-format="YYYY-MM-DD"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>							
						</div>
					</div>					
					<div id="vdPanel" class="form-group" style="display:none">						
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione los días en los cuales va a viajar</label><br>
								<label class="checkbox-inline">
									<s:checkbox name="viajediario" fieldValue="LUNES" /> Lunes
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="MARTES" /> Martes
								</label> 
								<label class="checkbox-inline">
									<s:checkbox name="viajediario" fieldValue="MIERCOLES" /> Miercoles
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="JUEVES" /> Jueves
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="VIERNES" /> Viernes
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="SABADO" /> Sabado
								</label> 
								<label class="checkbox-inline"> 
									<s:checkbox name="viajediario" fieldValue="DOMINGO" /> Domingo
								</label>
								<br>								
							</div>
						</div>
						<br>						
						<div class="row">
							<div class="col-md-6">
								<label>Fecha inicio del viaje</label>
								<div class='input-group date col-md-10 datepicker'>
									<s:textfield cssClass="form-control"  name="fechaIniciod" data-date-format="YYYY-MM-DD"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>
							<div class="col-md-6">
								<label>Fecha fin del viaje</label>
								<div class='input-group date col-md-10 datepicker'>
									<s:textfield cssClass="form-control"  name="fechaFin" data-date-format="YYYY-MM-DD"/>
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
									<s:textfield cssClass="form-control" name="horaPartida" label="Hora de partida" data-date-format="HH:mm"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>							
							<div class="col-md-6">
								<label>Hora de Regreso</label>
								<div class='input-group date horaviaje col-md-10'>
									<s:textfield cssClass="form-control" name="horaRegreso" label="Hora de regreso" data-date-format="HH:mm"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<label>Asientos disponibles</label>								
								<s:textfield cssClass="form-control" name="asientos" label="ingrese la cantidad de asientos disponibles" />													
							</div>							
						</div>
					</div>

					<div class="form-group">
						<label>Direcci&oacute;n de origen</label>
						<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" label="Ingrese la direcci&oacute;n de origen" />						
					</div>

					<div class="form-group">
						<label>Direcci&oacute;n destino</label>
						<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" label="Ingrese la direcci&oacute;n de origen" />						
					</div>

					<div class="form-group">
						<label>Recorrido GoogleMap</label>
						<button type="button" class="btn btn-default" onclick="javascript:calcRoute();">Trazar Recorrido</button>
						<br><br>
						<div id="map-canvas" style="height: 500px"></div>
					</div>

					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Registrar"/>
					</div>

				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="views/footer.jsp"%>
</body>
</html>