<%
  request.getSession(true);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="views/heads.jsp" %>
	 <%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="3"/>
    </jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="main row">
				<div>
					<a href="registroViaje" class="btn btn-primary"><span class="fa fa-road" aria-hidden="true"></span> <s:text name="viaje.registrarbtn" /></a>
					<a href="buscarViaje" class="btn btn-default"><span class="fa fa-search" aria-hidden="true"></span> <s:text name="viaje.buscarbtn" /></a>					
				</div>
				<h3 class="tituloSeccion margentb2"><s:text name="viaje.misviajeslbl" /></h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th><s:text name="viaje.dirorigen" /></th>
							<th><s:text name="viaje.dirdestino" /></th>
							<th><s:text name="viaje.fechapartida" /></th>
							<th><s:text name="viaje.rol" /></th>
							<th><s:text name="global.acciones" /></th>
						</tr>
					</thead>
					<tbody>					
					 <s:iterator value="viajeListaConductor">
		                <tr>                  
		                  <td><s:property value="direccionOrigen" /></td>
		                  <td><s:property value="direccionDestino" /></td>
		                  <td><s:date name="fechaInicio" format="dd/MM/YYYY"/> <s:date name="horaPartida" format="HH:mm"/></td>                  
		                  <td><s:text name="viaje.conductor" /></td>
		                  <td><s:url id="detalleURL" action="detalleViaje">
		                  		<s:param name="id" value="%{id}"></s:param>
		                  	  </s:url>
		                  	  <s:a href="%{detalleURL}"   cssClass="btn btn-default btn-xs"><s:text name="global.verdetalle" /></s:a>
		                  </td>
		                </tr> 
		              </s:iterator>
		              <s:iterator value="viajeListaPasajero">
		                <tr>                  
		                  <td><s:property value="direccionOrigen" /></td>
		                  <td><s:property value="direccionDestino" /></td>
		                  <td><s:date name="fechaInicio" format="dd/MM/YYYY"/> <s:date name="horaPartida" format="HH:mm"/></td>                  
		                  <td><s:text name="viaje.pasajero" /></td>
		                  <td><s:url id="detalleURL" action="detalleViaje">
		                  		<s:param name="id" value="%{id}"></s:param>
		                  	  </s:url>
		                  	  <s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs"><s:text name="viaje.verdetalle" /></s:a>
		                  </td>
		                </tr> 
		              </s:iterator>					
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- end main -->
	<%@ include file="views/footer.jsp"%>
</body>
</html>