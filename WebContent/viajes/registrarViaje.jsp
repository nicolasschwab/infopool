<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<!--<script type="text/javascript" src="js/Validaciones.js"></script>-->
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarRegistroViaje();cambiarTipoViaje()">
	
	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>
	
	<div class="main_bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 encabezado-seccion">
			    	<h1 class="titulo-seccion">Registro de Viaje</h1>		      		      
			      	<p class="descripcion-seccion">En 3 pasos usted podrá registrar su viaje.</p>
			    </div>
				<div class="col-sm-12 contenido-seccion">					
					<s:fielderror />
					<s:form role="form" theme="simple" cssClass="form-datos" action="RegistrarViaje">
						<fieldset id="datosUbicacion">
							<legend>Ubicación del Viaje</legend>
							<div class="form-group">
								<label><s:text name="viaje.dirorigen" /></label>
								<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" onchange="javascript:calcularTrayecto();"/>						
							</div>		
							<div class="form-group">
								<label><s:text name="viaje.dirdestino" /></label>
								<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" onchange="javascript:calcularTrayecto();"/>						
							</div>
							<div class="form-group">
								<div class="row">									
									<div class="col-sm-3">
										<label>Distancia del trayecto (km)</label>										
										<s:textfield cssClass="form-control" id="kilometros" name="kilometros" readonly="true"/>
									</div>
								</div>						
							</div>		
							<div class="form-group">
								<label><s:text name="viaje.recorridogm" /></label>								
								<s:hidden id="puntosTrayecto" name="puntosTrayecto"></s:hidden>
								<div id="map-canvas" style="height: 500px"></div>
							</div>
						</fieldset>
										
						<fieldset id="datosTiempo">
							<legend>Frecuencia del Viaje</legend>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-12">
										<label><s:text name="viaje.tipoviaje" /></label>
										<s:select cssClass="form-control" id="tipoViaje" name="tipoViaje" list="#{'PUNTUAL':'Puntual','PERIODICO':'Periodico'}" onchange="javascript: cambiarTipoViaje()" />								
									</div>							
								</div>												
							</div>				
							<div class="form-group">
								<div class="row">							
									<div class="col-sm-6">
										<label><s:text name="viaje.fechavp" /></label>
										<div class='input-group date col-sm-10 datepicker'>
											<s:textfield cssClass="form-control" name="fechaInicio" data-date-format="YYYY-MM-DD"/>
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										</div>								
									</div>
									<div id="vperFecha" class="col-sm-6" style="display:none">
										<label><s:text name="viaje.fechavp" /></label>
										<div class='input-group date col-sm-10 datepicker'>
											<s:textfield cssClass="form-control" name="fechaFin" data-date-format="YYYY-MM-DD"/>
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										</div>								
									</div>							
								</div>								
							</div>					
							<div id="vperPanel" class="form-group" style="display:none">						
								<div class="row">
									<div class="col-sm-12">
										<label><s:text name="viaje.diaslbl" /></label><br>
										<label class="checkbox-inline">
											<s:checkbox name="diaPeriodico" fieldValue="LUNES" /> Lunes
										</label> 
										<label class="checkbox-inline"> 
											<s:checkbox name="diaPeriodico" fieldValue="MARTES" /> Martes
										</label> 
										<label class="checkbox-inline">
											<s:checkbox name="diaPeriodico" fieldValue="MIERCOLES" /> Miercoles
										</label> 
										<label class="checkbox-inline"> 
											<s:checkbox name="diaPeriodico" fieldValue="JUEVES" /> Jueves
										</label> 
										<label class="checkbox-inline"> 
											<s:checkbox name="diaPeriodico" fieldValue="VIERNES" /> Viernes
										</label> 
										<label class="checkbox-inline"> 
											<s:checkbox name="diaPeriodico" fieldValue="SABADO" /> Sabado
										</label> 
										<label class="checkbox-inline"> 
											<s:checkbox name="diaPeriodico" fieldValue="DOMINGO" /> Domingo
										</label>																		
									</div>
								</div>
							</div>							
							<div class="form-group">
								<div class="row">
									<div class="col-sm-12">
										<label>Tramo Viaje</label>
										<s:select cssClass="form-control" id="tramoViaje" name="tramoViaje" list="#{'IDA':'IDA','IDAVUELTA':'IDA/VUELTA'}" onchange="javascript: cambiarTramoViaje()" />								
									</div>							
								</div>												
							</div>							
							<div class="form-group">
								<div class="row">							
									<div class="col-sm-6">
										<label><s:text name="viaje.horapartida" /></label>
										<div class='input-group date horaviaje col-sm-10'>
											<s:textfield cssClass="form-control" name="horaPartida" data-date-format="HH:mm"/>
											<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
										</div>								
									</div>							
									<div id="tcompHora" class="col-sm-6" style="display:none">
										<label><s:text name="viaje.horaregreso" /></label>
										<div class='input-group date horaviaje col-sm-10'>
											<s:textfield cssClass="form-control" name="horaRegreso" data-date-format="HH:mm"/>
											<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
										</div>								
									</div>
								</div>
							</div>
						</fieldset>
						
						<fieldset id="datosDetalle">
							<legend>Datos varios</legend>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-2">
										<label><s:text name="viaje.asientos" /></label>								
										<s:textfield cssClass="form-control" name="asientosDisponibles" />													
									</div>							
								</div>
							</div>
							<div class="form-group">								
								<label>Descripcion</label>
								<s:textarea cssClass="form-control" name="descripcion"/>								
							</div>
						</fieldset>					
						
						<div class="form-group">
							<s:submit cssClass="btn btn-primary" value="%{getText('global.registrar')}"/>
						</div>
	
					</s:form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>