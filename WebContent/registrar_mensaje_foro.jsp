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
<body >

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="4" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">		
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Registro de Mensaje</h3>
				<s:fielderror />
				<s:form role="form" theme="simple" cssClass="form-signin" action="enviarMensajeForo">
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Receptor</label>
								<br>
								<h4><label class="form-control" >Mensaje al foro del viaje  </label></h4> 			
							</div>
						</div>												
					</div>				
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Ingrese el mensaje</label>
								<br>
								<s:textarea cssClass="form-control"  name="detalle" rows="10" />		
							</div>
						</div>												
					</div>
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Registrar"/>
					</div>
					<s:hidden name="viajeId" value="%{viajeId}"></s:hidden>					
				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="views/footer.jsp"%>
</body>
</html>