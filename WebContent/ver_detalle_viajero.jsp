<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.Viajero"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
    <%@ include file="views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

     <%@ include file="views/header.jsp" %>

  <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="2"/>
    </jsp:include>

<div class="main_bg">    
    <div class="container">
        <div class="main row">
		  <div class="col-md-12">
		  	<div class="row">
		  		<div class="col-md-2">		  			
		  			<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{usrlogueado.id}"></s:param>
							</s:url>" class="img-thumbnail">		  			
		  		</div>
		  		<div class="col-md-5">
		  			<p><strong><s:text name="viajero.nombre" />:</strong> <s:property value="usrlogueado.nombre" /> <s:property value="usrlogueado.apellido" /></p>
		  			<p><strong><s:text name="viajero.mail" />:</strong> <s:property value="usrlogueado.mail" /></p>
		  			<p><strong><s:text name="viajero.telefono" />:</strong> <s:property value="usrlogueado.telefono" /></p>
		  			<p><strong><s:text name="viajero.fechanacimiento" />:</strong> <s:date name="usrlogueado.fechaNacimiento" format="dd/MM/YYYY" /></p>
		  		</div>
		  		<div class="col-md-5">
		  			<p><strong><s:text name="viajero.fechaingreso" />:</strong> <s:date name="usrlogueado.fechaIngresoSistema" format="dd/MM/YYYY" /></p>
		  			<p><strong><s:text name="viajero.calificacion" />:</strong> <s:property value="usrlogueado.calificacionActual()" />/5</p>
		  			<p><strong><s:text name="viajero.estado" />:</strong> <s:if test="usrlogueado.activo"><s:text name="global.activo" /></s:if><s:else><s:text name="global.inactivo" /></s:else></p>
		  			<p><strong><s:text name="viajero.preferencias" />:</strong> <s:property value="usrlogueado.preferenciasViaje" /></p>
		  		</div>
		  	</div>		  	
		  	<div class="row">
		  		<div class="col-md-2 centrar">
		  			<s:url id="edicionUsuario" action="edicionUsuario">
						<s:param name="id" value="%{usrlogueado.id}"></s:param>
					</s:url>
			  		<s:a href="%{edicionUsuario}" cssClass="btn btn-primary" role="button"><s:text name="viajero.edicionbtn" /></s:a>
				  	<!--  <a href="" class="btn btn-primary disabled" role="button"><s:text name="viajero.cancelarbtn" /></a>				  	
				  	<a href="" class="btn btn-primary disabled" role="button"><s:text name="viajero.denunciasbtn" /></a>-->				  	
		  		</div>
		  	</div>
		  </div>
		</div>
    </div>
</div><!-- end main -->
 <%@ include file="views/footer.jsp" %>
</body>
</html>