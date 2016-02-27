<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.Viajero"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
    <%@ include file="../views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

  <jsp:include page="../views/header.jsp">
    	<jsp:param name="itemActivo" value="2"/>
    </jsp:include>

<div class="main_bg">    
    <div class="container">
        <div class="main row">
		  <div class="col-md-12">
		  	<div class="row">
		  		<div class="col-md-2">		  			
		  			<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{user.id}"></s:param>
							</s:url>" class="img-thumbnail">		  			
		  		</div>
		  		<div class="col-md-5">
		  			<p><strong><s:text name="viajero.nombre" />:</strong> <s:property value="user.nombre" /> <s:property value="user.apellido" /></p>
		  			<p><strong><s:text name="viajero.mail" />:</strong> <s:property value="user.mail" /></p>
		  			<p><strong><s:text name="viajero.telefono" />:</strong> <s:property value="user.telefono" /></p>
		  			<p><strong><s:text name="viajero.fechanacimiento" />:</strong> <s:date name="user.fechaNacimiento" format="dd/MM/YYYY" /></p>
		  		</div>
		  		<div class="col-md-5">
		  			<p><strong><s:text name="viajero.fechaingreso" />:</strong> <s:date name="user.fechaIngresoSistema" format="dd/MM/YYYY" /></p>
		  			<p><strong><s:text name="viajero.calificacion" />:</strong> <s:property value="user.calificacion" />/5</p>
		  			<p><strong><s:text name="viajero.estado" />:</strong> <s:if test="user.activo"><s:text name="global.activo" /></s:if><s:else><s:text name="global.inactivo" /></s:else></p>
		  			<p><strong><s:text name="viajero.preferencias" />:</strong> <s:property value="user.preferenciasViaje" /></p>
		  		</div>
		  	</div>		  	
		  	<div class="row">
		  		<div class="col-md-2 centrar">
		  			<s:url var="edicionUsuario" action="edicionUsuario">
						<s:param name="id" value="%{user.id}"></s:param>
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
 <%@ include file="../views/footer.jsp" %>
</body>
</html>