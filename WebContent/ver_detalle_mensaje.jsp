<% request.getSession(true);%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
   <%@ include file="views/heads.jsp" %>
   <%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

     
 	<jsp:include page="views/header.jsp">
		<jsp:param name="itemActivo" value="4" />
	</jsp:include>

  
<div class="main_bg">    
    <div class="container">
        <div class="main row">
		  	<div class="col-md-12">
		  		<div class="col-md-4">		   						  	
				  	<div class="col-md-12">
				  		<h3 class="tituloSeccion"><s:text name="mensaje.datos"></s:text> <s:property value="%{queNoSos}" /> </h3>
				  		<img src="<s:url action="ImageAction">
								<s:param name="id" value="%{emisor.id}"></s:param>
							</s:url>" alt="foto conductor" class="img-thumbnail">
				  		<p><strong><s:text name="viajero.nombre"></s:text>:</strong><s:property  value="%{emisor.nombre}"/></p>
			  			<p><strong><s:text name="viajero.fechaingreso"></s:text>:</strong><s:date name="%{emisor.fechaIngresoSistema}" format="dd/MM/yyyy" /></p>
			  			<p><strong><s:text name="viajero.calificacion"></s:text>:</strong> <s:property value="%{emisor.calificacionActual()}" /> / 5</p>
			  			<p><strong><s:text name="mensaje.estado"></s:text>:</strong> <s:property value="%{estado}" /></p>
				  	</div>				  	
			  	</div>
			  	<div class="col-md-8">			  		
			  		<h3 class="tituloSeccion"><s:text name="mensaje.asunto"></s:text></h3>	   				
	   				<p><s:property   value="%{asunto}"   />  </p>
	   				<h3 class="tituloSeccion"><s:text name="mensaje.mensaje"></s:text></h3>		   			
	   				<p><s:property   value="%{detalle}"   /></p>
	   				<s:if test="%{queNoSos=='Emisor'}">		   		
	   					<h3 class="tituloSeccion"><s:text name="mensaje.respuesta"></s:text></h3>	   				
		   				<s:form role="form" theme="simple" cssClass="form-signin" action="responder">
		   					<div class="form-group">
					  			<s:textarea cssClass="form-control" name="respDetalle"  cols="80" rows="7"></s:textarea>
					  		</div>
					  		<s:hidden name="asunto" value="%{asunto}"></s:hidden>
					  		<s:hidden name="receptorID" value="%{receptorID}"></s:hidden>
					  		<s:hidden name="id" value="%{id}"></s:hidden>
					  		<div class="form-group">
				            	<s:submit cssClass="btn btn-primary" value="%{getText('mensaje.responder')}"/>
				            </div>
					  	</s:form>
				  	</s:if>
		   		</div>
		  	</div>		  			   				
		</div>
    </div>
</div><!-- end main -->
 <%@ include file="views/footer.jsp" %>
</body>
</html>