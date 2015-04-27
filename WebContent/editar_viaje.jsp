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
				<h3 class="tituloSeccion margentb2"><s:text name="viaje.edicionlbl" />
				<s:if test="%{viaje.evento!=null}">					
				: <s:text name="viaje.eventolbl" /> <s:property value="viaje.evento.nombre" />
				</s:if>
				</h3>
				
				<s:fielderror />				
				<s:form role="form" theme="simple" cssClass="form-signin" action="editarViaje">					
					<s:if test="%{viaje.evento==null}">
						<s:if test="%{viaje.fechaFin!=null}">						
							<s:set var="dispVP" value="%{'display:none'}" />
							<s:set var="dispVD" value="" />
							<s:set var="listV" value="#{'vd':'Viaje Diario','vp':'Viaje Puntual'}" />
						</s:if>
						<s:else>
							<s:set var="dispVP" value="" />
							<s:set var="dispVD" value="%{'display:none'}" />
							<s:set var="listV" value="#{'vp':'Viaje Puntual','vd':'Viaje Diario'}" />
						</s:else>
						
						<div class="form-group">
							<div class="row">
								<div class="col-md-12">
									<label><s:text name="viaje.tipoviaje" /></label>
									<br>
									<s:select cssClass="form-control" id="tipoViaje" name="tipoViaje" list="listV" onchange="javascript:cambiarTipoViaje()"/>								
								</div>							
							</div>												
						</div>	
						<div id="vpPanel" class="form-group" style="<s:property value='dispVP'/>">
							<div class="row">							
								<div class="col-md-6">
									<label><s:text name="viaje.fechavp" /></label>
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
									<label><s:text name="viaje.diaslbl" /></label><br>
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
									<label><s:text name="viaje.fechainicio" /></label>
									<div class='input-group date col-md-10 datepicker'>									
										<s:textfield cssClass="form-control"  name="fechaIniciod" data-date-format="YYYY-MM-DD" value="%{fechaIni}"/>
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									</div>								
								</div>
								<div class="col-md-6">
									<label><s:text name="viaje.fechafin" /></label>
									<div class='input-group date col-md-10 datepicker'>									
										<s:date name="viaje.fechaFin" id="fechaFin" format="yyyy-MM-dd"/>
										<s:textfield cssClass="form-control"  name="fechaFin" data-date-format="YYYY-MM-DD" value="%{fechaFin}"/>
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									</div>								
								</div>
							</div>
							<br>
						</div>
					</s:if>
					
					<div class="form-group">
						<div class="row">							
							<div class="col-md-6">
								<label><s:text name="viaje.horapartida" /></label>
								<div class='input-group date horaviaje col-md-10'>
									<s:date name="viaje.horaPartida" id="horaPar" format="HH:mm"/>
									<s:textfield cssClass="form-control" name="horaPartida" data-date-format="HH:mm" value="%{horaPar}"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>							
							<div class="col-md-6">
								<label><s:text name="viaje.horaregreso" /></label>
								<div class='input-group date horaviaje col-md-10'>
									<s:date name="viaje.horaRegreso" id="horaReg" format="HH:mm"/>
									<s:textfield cssClass="form-control" name="horaRegreso" data-date-format="HH:mm" value="%{horaReg}"/>
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
								</div>								
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<label><s:text name="viaje.asientos" /></label>															
								<s:textfield cssClass="form-control" name="asientos" value="%{viaje.asientos}"/>														
							</div>							
						</div>
					</div>

					<div class="form-group">
						<label><s:text name="viaje.dirorigen" /></label>
						<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" value="%{viaje.direccionOrigen}"/>						
					</div>
					
					<s:if test="%{viaje.evento!=null}">						
						<s:set var="esEvento" value="%{true}" />
					</s:if>
					<s:else>
						<s:set var="esEvento" value="%{false}" />
					</s:else>					
					<div class="form-group">
						<label><s:text name="viaje.dirdestino" /></label>
						<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" value="%{viaje.direccionDestino}" readonly="esEvento"/>						
					</div>								

					<div class="form-group">
						<label><s:text name="viaje.recorridogm" /></label>
						<button type="button" class="btn btn-default" onclick="javascript:calcRoute();"><s:text name="viaje.trazagm" /></button>
						<br><br>
						<div id="map-canvas" style="height: 500px"></div>
					</div>
					
					<s:hidden name="id" value="%{viaje.id}"></s:hidden>
					<s:if test="%{viaje.evento!=null}">
						<s:hidden name="evento_id" value="%{viaje.evento.id}"></s:hidden>
					</s:if>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="%{getText('global.editar')}"/>
					</div>

				</s:form>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>