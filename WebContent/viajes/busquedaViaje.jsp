<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp" %>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

	<jsp:include page="../views/header.jsp">
    	<jsp:param name="itemActivo" value="3"/>
    </jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="col-sm-12 menu-seccion">					
				<jsp:include page="../viajero/menuSeccionViaje.jsp">
					<jsp:param name="subitemActivo" value="2"/>				
				</jsp:include>
			</div>			
			<div class="main row">
				<div class="col-sm-12 encabezado-seccion">
			    	<h1 class="titulo-seccion">Viajes</h1>		      		      
			      	<p class="descripcion-seccion">En esta sección usted podrá administrar sus viajes.</p>
			    </div>
				<div class="col-sm-12 contenido-seccion">
					<h3 class="tituloSeccion"><s:text name="viaje.misviajeslbl" /></h3>
					<table class="table" id="datatable">
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
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../views/footer.jsp"%>
	
</body>
</html>