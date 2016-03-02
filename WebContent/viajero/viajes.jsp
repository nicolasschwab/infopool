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
    	<jsp:param name="itemActivo" value="3"/>
    </jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="row">				
				<div class="col-sm-12 padding-both-zero">
					<p class="header-title">Viajes</p>
					<div class="menu-seccion">
						<div class="row">
							<div class="col-sm-12">																	
								<form class="">									
									<div class="form-group col-sm-3">											
									  	<input type="text" class="form-control" placeholder="Dirección de Origen">
									</div>
									<div class="form-group col-sm-3">										  	
									  	<input type="text" class="form-control" placeholder="Dirección de Destino">
									</div>
									<div class="form-group col-sm-2">										  	
									  	<input type="text" class="form-control" placeholder="Fecha de Partida">
									</div>
									<div class="form-group col-sm-3">										  	
									  	<input type="text" class="form-control" placeholder="Evento">
									</div>
									<div class="form-group col-sm-1">
										<button type="submit" class="btn btn-default">Buscar</button>
									</div>
								</form>								
							</div>
						</div>					      					    						
					</div>
				</div>
			</div>			
			<div class="main row">
				<div class="col-sm-3 padding-both-zero">
					<div class="panel-header-seccion">
						<h3>Filtros</h3>
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
				<div class="col-sm-9 padding-right-zero">
					<div class="contenido-seccion shadow-box">
						<h3 class="titulo-contenido">Últimos viajes registrados</h3>				
						<!-- <table class="table" id="datatable">
							<thead>
								<tr>
									<th>Viaje</th>
									<th>Fecha Inicio</th>			
									<th>Frecuencias</th>
									<th>Conductor</th>		
									<th><s:text name="global.acciones" /></th>
								</tr>
							</thead>
							<tbody>			
							 	<s:iterator value="listaViajes">
				                <tr>                  
				                  <td><strong>DE</strong> <s:property value="direccionOrigen" /><br><strong>HASTA</strong> <s:property value="direccionDestino" /></td>
				                  <td><s:date name="fechaInicio" format="dd/MM/YYYY"/></td>
				                  <td>
				                  	<s:iterator value="frecuencias">
				                  		<s:if test="%{diaFrecuencia!=null}">
				                  			<s:property value="diaFrecuencia"/> a las 
				                  		</s:if>
				                  		<s:date name="horaPartida" format="HH:mm"/> (<s:property value="estadoFrecuencia"/>)<br>			                  	
				                  	</s:iterator>                  
				                  </td>
				                  <td><s:property value="conductor.nombre" /> <s:property value="conductor.apellido" /></td>			                  
				                  <td><s:url id="detalleURL" action="DetalleViaje">
				                  		<s:param name="id" value="%{id}"></s:param>
				                  	  </s:url>
				                  	  <s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs"><s:text name="global.verdetalle" /></s:a>
				                  </td>
				                </tr> 
				              </s:iterator>			              					
							</tbody>
						</table> -->
						<div>
							<s:iterator value="listaViajes">
								<div class="media">	 
									<div class="media-left">
										<a href="#">									    	
									    	<img class="media-object" src="<s:url action="ImageAction">
												<s:param name="id" value="%{conductor.id}"></s:param>
											</s:url>" alt="conductor" width="90" height="90">
									    </a>
									</div>
									<div class="media-body">
										<p><s:date name="fechaInicio" format="dd/MM/YYYY"></s:date></p>
										<p><s:property value="direccionOrigen" /></p>
										<p><s:property value="direccionDestino" /></p>
									</div>
									<div class="media-right">
										<span class="fa fa-user" aria-hidden="true"></span> asientos<br>										
										<span class="fa fa-retweet" aria-hidden="true"></span> tramo<br>						
										<span class="fa fa-map-marker" aria-hidden="true"></span> km<br>
										<span class="fa fa-calendar" aria-hidden="true"></span> tipo viaje								
									</div>
								</div>
							</s:iterator>
						</div>
						<nav>
						  <ul class="pagination">
						    <li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li><a href="#">1</a></li>
						    <li><a href="#">2</a></li>
						    <li><a href="#">3</a></li>
						    <li><a href="#">4</a></li>
						    <li><a href="#">5</a></li>
						    <li>
						      <a href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>