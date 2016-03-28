<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>	
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>
	
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
					<s:form role="form" theme="simple" cssClass="form-datos">
						<fieldset id="datosDetalle">
							<legend>Datos Varios</legend>
							<div class="form-group col-sm-4">
								<div class="row">							
									<div class="col-sm-10">
										<label><s:text name="viaje.fechavp" /></label>
										<div class='input-group date col-sm-10 datepicker'>
											<s:date name="viaje.fechaInicio" id="fechaIni" format="yyyy-MM-dd"/>
											<s:textfield cssClass="form-control" id="fechaInicio" name="fechaInicio" data-date-format="YYYY-MM-DD" value="%{fechaIni}"/>
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										</div>								
									</div>
									<div class="col-sm-2">
										<div id="spinerFini" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div>
										<div id="checkFini" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div>
									</div>									
									<s:if test="%{viaje.tipoViaje.name()=='PERIODICO'}">
										<br>										
										<div id="vperFecha" class="col-sm-10">
											<label><s:text name="viaje.fechavp" /></label>
											<div class='input-group date col-sm-10 datepicker'>
												<s:date name="viaje.fechaFin" id="fechaFin" format="yyyy-MM-dd"/>
												<s:textfield cssClass="form-control" id="fechaFin" name="fechaFin" data-date-format="YYYY-MM-DD" value="%{fechaFin}"/>
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											</div>								
										</div>
										<div class="col-sm-2">
											<div id="spinerFfin" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div>
											<div id="checkFfin" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div>
										</div>
									</s:if>							
								</div>								
							</div>
							<div class="form-group col-sm-8">
								<label>Descripción</label>
								<s:textarea cssClass="form-control" id="descripcion" name="descripcion" value="%{viaje.descripcion}"/>
								<div class="col-md-2">
									<div id="spinerDesc" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div>
									<div id="checkDesc" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div>
								</div>								
							</div>
						</fieldset>					
						<div class="form-group">								
							<s:hidden cssClass="form-control" id="dirOrigen" name="direccionOrigen" value="%{viaje.direccionOrigen}"/>					
							<s:hidden cssClass="form-control" id="dirDestino" name="direccionDestino" value="%{viaje.direccionDestino}"/>
							<s:hidden id="id" name="id" value="%{viaje.id}"/>						
						</div>			
						<fieldset id="datosTiempo">
							<legend>Frecuencias del Viaje</legend>				
							<ul class="nav nav-tabs">
							<s:iterator value="viaje.frecuencias">
							    <li><a href="#frecuencia_<s:property value='%{id}'/>"><s:property value="%{diaFrecuencia.name()}"/></a></li>
							</s:iterator>
							</ul>
							<div class="tab-content">
							<s:iterator value="viaje.frecuencias">
								<div id="frecuencia_<s:property value='%{id}'/>" class="tab-pane fade">									
									<div id="divRespuesta<s:property value="%{id}"/>"></div>								
									<div class="row">										
										<div class="col-sm-4">
											<div class="col-sm-12">
												<label><s:text name="viaje.asientos" /></label>																			
												<s:textfield cssClass="form-control" id="asientosDisponibles%{id}" name="asientosDisponibles" value="%{asientosDisponibles}"/>													
											</div>															
											<div class="col-sm-12">
												<label><s:text name="viaje.horapartida" /></label>
												<s:date name="horaPartida" id="horaPar" format="HH:mm"/>											
												<div class='input-group date horaviaje col-sm-12' id="">												
													<s:textfield cssClass="form-control" id="horaPartida%{id}" name="horaPartida" data-date-format="HH:mm" value="%{horaPar}"/>
													<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
												</div>								
											</div>										
											<s:if test="%{tramoViaje.name()=='IDAVUELTA'}">							
												<div id="tcompHora" class="col-sm-12">
													<label><s:text name="viaje.horaregreso" /></label>
													<div class='input-group date horaviaje col-sm-12'>
														<s:date name="horaRegreso" id="horaReg" format="HH:mm"/>
														<s:textfield cssClass="form-control" id="horaRegreso%{id}" name="horaRegreso" data-date-format="HH:mm" value="%{horaReg}"/>
														<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
													</div>								
												</div>
											</s:if>
											<div class="col-sm-12">
												<div id="spinerFrecuencia<s:property value="%{id}"/>" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div>
												<div id="checkFrecuencia<s:property value="%{id}"/>" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div>
												<input type="button" value="modificar" class="btn btn-primary botonFrecuencia" id="botonFrecuencia_<s:property value="%{id}"/>"/>
											</div>										
										</div>	
										<div class="col-sm-8">								
											<div class="col-sm-5">
												<label>Distancia del trayecto (km)</label>										
												<s:textfield cssClass="form-control" id="kilometros_%{id}" name="kilometros" readonly="true" value="%{kilometros}"/>										
											</div>																		
											<div class="col-sm-7">
												<div id="spinerTray<s:property value="%{id}"/>" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div>
												<div id="checkTray<s:property value="%{id}"/>" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div>										
												<input type="button" value="actualizar recorrido" class="btn btn-primary botonRecorrido" id="botonRecorrido_<s:property value="%{id}"/>"/>
											</div>	
											<div class="col-sm-12">
												<label><s:text name="viaje.recorridogm" /></label>								
												<s:hidden id="puntosTrayecto_%{id}" name="puntosTrayecto" value="%{puntosTrayecto}"></s:hidden>
												<div id="map-canvas<s:property value="%{id}"/>" style="height: 250px"></div>
											</div>
										</div>
									</div>
								</div>
							</s:iterator>
						</div>
						</fieldset>
					</s:form>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
		    $(".nav-tabs a").click(function(){
		    	$(this).tab('show');
		    	var frec = (this.toString().split('#')[1]).split('_')[1];
		        mostrarRecorridoFrecuencia(frec);		        
		    });
		 	// Select first tab
		    $('.nav-tabs a:first').click()
		});
	</script>
	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>