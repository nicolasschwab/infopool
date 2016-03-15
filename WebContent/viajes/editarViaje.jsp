<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<!--<script type="text/javascript" src="js/Validaciones.js"></script>-->
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarEdicionViaje()">
	
	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>
	
	<div class="main_bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 encabezado-seccion">
			    	<h1 class="titulo-seccion">Edición de Viaje</h1>		      		      
			      	<p class="descripcion-seccion">En 3 pasos usted podrá registrar su viaje.</p>
			    </div>
				<div class="col-sm-12 contenido-seccion">					
					<s:fielderror />
					<s:form role="form" theme="simple" cssClass="form-datos" action="EditarViaje">
						<fieldset id="datosUbicacion">
							<legend>Ubicación del Viaje</legend>
							<p>Solo se almacenaran los puntos intermedios del recorrido.</p>
							<div class="form-group">								
								<s:hidden cssClass="form-control" id="dirOrigen" name="direccionOrigen" value="%{viaje.direccionOrigen}"/>					
								<s:hidden cssClass="form-control" id="dirDestino" name="direccionDestino" value="%{viaje.direccionDestino}"/>						
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
						
						<fieldset id="datosDetalle">
							<legend>Datos Varios</legend>
							<div class="form-group">
								<label>Descripción</label>
								<s:textarea cssClass="form-control" name="descripcion" value="%{viaje.descripcion}"/>								
							</div>
						</fieldset>					
										
						<fieldset id="datosTiempo">
							<legend>Frecuencias del Viaje</legend>																	
							<div class="form-group">
								<div class="row">							
									<div class="col-sm-6">
										<label><s:text name="viaje.fechavp" /></label>
										<div class='input-group date col-sm-10 datepicker'>
											<s:date name="viaje.fechaInicio" id="fechaIni" format="yyyy-MM-dd"/>
											<s:textfield cssClass="form-control" name="fechaInicio" data-date-format="YYYY-MM-DD" value="%{fechaIni}"/>
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										</div>								
									</div>									
									<s:if test="{viaje.tipoViaje.name()=='PERIODICO'}">										
										<div id="vperFecha" class="col-sm-6">
											<label><s:text name="viaje.fechavp" /></label>
											<div class='input-group date col-sm-10 datepicker'>
												<s:date name="viaje.fechaFin" id="fechaFin" format="yyyy-MM-dd"/>
												<s:textfield cssClass="form-control" name="fechaFin" data-date-format="YYYY-MM-DD" value="%{fechaFin}"/>
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											</div>								
										</div>
									</s:if>							
								</div>								
							</div>
							<s:set var="trmViaje" value="%{viaje.tramoViaje.name()}" />							
							<s:iterator value="viaje.frecuencias">
								<p><strong><s:property value="%{diaFrecuencia.name()}"/></strong></p>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-2">
											<label><s:text name="viaje.asientos" /></label>								
											<s:textfield cssClass="form-control" name="asientosDisponibles" value="%{asientosDisponibles}"/>													
										</div>															
										<div class="col-sm-5">
											<label><s:text name="viaje.horapartida" /></label>
											<div class='input-group date horaviaje col-sm-10'>
												<s:date name="horaPartida" id="horaPar" format="HH:mm"/>
												<s:textfield cssClass="form-control" name="horaPartida" data-date-format="HH:mm" value="%{horaPar}"/>
												<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
											</div>								
										</div>										
										<s:if test="%{viaje.tramoViaje.name()=='IDAVUELTA'}">							
											<div id="tcompHora" class="col-sm-5">
												<label><s:text name="viaje.horaregreso" /></label>
												<div class='input-group date horaviaje col-sm-10'>
													<s:date name="horaRegreso" id="horaReg" format="HH:mm"/>
													<s:textfield cssClass="form-control" name="horaRegreso" data-date-format="HH:mm" value="%{horaReg}"/>
													<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
												</div>								
											</div>
										</s:if>
									</div>
								</div>
							</s:iterator>
						</fieldset>
						
						<div class="form-group">
							<s:submit cssClass="btn btn-primary" value="%{getText('global.editar')}"/>
						</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>