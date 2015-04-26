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
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">		
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2">Asociar Evento al Viaje</h3>
				<s:fielderror />
				<s:form role="form" theme="simple" cssClass="form-signin" action="asociarEvento">
					<div class="form-group">
						<div class="row">
							<div class="col-md-12">
								<label>Seleccione el evento</label>
								<br>
								<s:select list="eventoLista"  listKey="id" listValue="nombre" name="evento_id" value="eventoLista.{id}" cssClass="form-control" />			
							</div>
						</div>												
					</div>					
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Asociar"/>
					</div>
					<s:hidden name="id" value="%{viaje.id}"></s:hidden>					
				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="views/footer.jsp"%>
</body>
</html>