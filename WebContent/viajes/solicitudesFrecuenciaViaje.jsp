<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="model.FrecuenciaViaje"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<link href="css/foro.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/foro.js"></script>
</head>
<body>

	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">		
		<div class="container">
			<div class="row">
				<ol class="breadcrumb">
  					<li><a href="#">< Regresar</a></li>
  				</ol>
				<div class="col-sm-12">
					<h2>Solicitudes de la Frecuencia</h2>
					<s:iterator value="listaSolicitudes" >
						<p><s:property value="viajero.obtenerNombre()"/></p>
						<p><s:date name="fechaInicioSolicitud" format="dd/MM/YYYY"/></p>
					</s:iterator>	
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../views/footer.jsp"%>
</body>
</html>