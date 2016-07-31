<% request.getSession(true);%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="../views/heads.jsp"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarBusquedaViaje();">

	<jsp:include page="../views/header.jsp">
		<jsp:param name="itemActivo" value="0" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<jsp:include page="./menuBusqueda.jsp"></jsp:include>
			<div class="main row">
<%-- 				<jsp:include page="./filtros.jsp"></jsp:include> --%>
				<div class="col-sm-12 padding-right-zero">
					<div class="contenido-seccion shadow-box">
						<div class="boxViajes">
							<div class="tiposListadoViajes" id="pasajero"><h3 class="titulo-contenido">Resultados de busqueda</h3></div>
						</div>
						<div class="listaViajesConductor listaviajes_wrap">
							<s:if test="%{listaBusquedaViajes.isEmpty()}">							
								<div>No hay Viajes para mostrar</div>
							</s:if>
							<s:else>
								<s:iterator value="listaBusquedaViajes">
									<jsp:include page="datosUnViaje.jsp" />
								</s:iterator>
							</s:else>
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