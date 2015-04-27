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
				<h3 class="tituloSeccion margentb2"><s:text name="viaje.registrolbl" /></h3>
				<s:fielderror />				
				<s:form role="form" theme="simple" cssClass="form-signin" action="registrarViaje">
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label><s:text name="viaje.tipoviaje" /></label>
								<br>
								<s:select cssClass="form-control" id="tipoViaje" name="tipoViaje" list="#{'vp':'Viaje Puntual','vd':'Viaje Diario'}" onchange="javascript: cambiarTipoViaje()" />								
							</div>							
						</div>												
					</div>					
					<div id="vpPanel" class="form-group">
						<div class="row">							
							<div class="col-md-6">
								<label><s:text name="viaje.fechavp" /></label>
								<div class='input-group date col-md-10 datepicker'>
									<s:textfield cssClass="form-control" name="fechaIniciop" data-date-format="YYYY-MM-DD"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>							
						</div>
						<br>
					</div>					
					<div id="vdPanel" class="form-group" style="display:none">						
						<div class="row">
							<div class="col-md-12">
								<label><s:text name="viaje.diaslbl" /></label><br>
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
								<label><s:text name="viaje.fechainicio" /></label>
								<div class='input-group date col-md-10 datepicker'>
									<s:textfield cssClass="form-control"  name="fechaIniciod" data-date-format="YYYY-MM-DD"/>
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								</div>								
							</div>
							<div class="col-md-6">
								<label><s:text name="viaje.fechafin" /></label>
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
								<label><s:text name="viaje.horapartida" /></label>
								<div class='input-group date horaviaje col-md-10'>
									<s:textfield cssClass="form-control" name="horaPartida" data-date-format="HH:mm"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>							
							<div class="col-md-6">
								<label><s:text name="viaje.horaregreso" /></label>
								<div class='input-group date horaviaje col-md-10'>
									<s:textfield cssClass="form-control" name="horaRegreso" data-date-format="HH:mm"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<label><s:text name="viaje.asientos" /></label>								
								<s:textfield cssClass="form-control" name="asientos" />													
							</div>							
						</div>
					</div>

					<div class="form-group">
						<label><s:text name="viaje.dirorigen" /></label>
						<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" />						
					</div>

					<div class="form-group">
						<label><s:text name="viaje.dirdestino" /></label>
						<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" />						
					</div>

					<div class="form-group">
						<label><s:text name="viaje.recorridogm" /></label>
						<button type="button" class="btn btn-default" onclick="javascript:calcRoute();"><s:text name="viaje.trazagm" /></button>
						<br><br>
						<div id="map-canvas" style="height: 500px"></div>
					</div>
					
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="%{getText('global.registrar')}"/>
					</div>

				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="views/footer.jsp"%>
</body>
</html>