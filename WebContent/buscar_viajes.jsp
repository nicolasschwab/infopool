<% request.getSession(true);%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarBusquedaViaje();">

	<%@ include file="views/header.jsp"%>

	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="3" />
	</jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="main row">
				<s:fielderror />
				<s:form cssClass="form-signin" theme="simple" role="form"
					action="listarPorDireccion">
					<div class="col-md-12">
						<div class="form-group col-md-5">
							<label for="exampleInputEmail1">Direcci&oacute;n desde</label>
							<s:textfield cssClass="form-control" id="dirOrigen"
								name="direccionOrigen"
								placeholder="Ingrese la direccion de origen" />
						</div>
						<div class="form-group col-md-5">
							<label for="exampleInputPassword1">Direcci&oacute;n hasta</label>
							<s:textfield cssClass="form-control" id="dirDestino"
								name="direccionDestino"
								placeholder="Ingrese la direccion de destino" />
						</div>
						<div class="col-md-2 form-group">
							<br>
							<button type="submit" class="btn btn-primary">Buscar</button>
						</div>
					</div>
				</s:form>

				<div class="col-md-12">
					<s:form cssClass="form-signin" theme="simple" role="form"
						action="listarTodosLosViajes">
						<div class="form-group col-md-4">
							<label for="exampleInputEmail1">Evento</label>
							<s:select list="eventoLista" listKey="id" listValue="nombre"
								headerKey="-1" headerValue="" name="valor"
								value="eventoLista.{id}" cssClass="form-control" />
						</div>
						<div class="form-group col-md-1">
							<br>
							<s:submit cssClass="btn btn-primary" value="Buscar" />
						</div>

					</s:form>
					<s:form cssClass="form-signin" theme="simple" role="form"
						action="listarPorFecha">
						<div class="form-group col-md-5">
							<label for="exampleInputEmail1">Fecha</label>
							<div class='input-group date datepicker'>
								<s:textfield type='text' onfocus="this.blur()" name="fecha"
									id="fecha" data-date-format="YYYY-MM-DD"
									cssClass="form-control" />
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<div class="form-group col-md-2">
							<br>
							<s:submit cssClass="btn btn-primary" value="Buscar" />
						</div>

					</s:form>
				</div>

				<h3 class="tituloSeccion margentb2">Viajes Encontrados</h3>
				<table class="table" id="datatable">
					<thead>
						<tr>
							<th>Desde</th>
							<th>Hasta</th>
							<th>Fecha Partida</th>
							<th>Evento</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="viajeLista">
							<tr>
								<td><s:property value="direccionOrigen" /></td>
								<td><s:property value="direccionDestino" /></td>
								<td><s:date name="fechaInicio" format="dd/MM/YYYY" /> <s:date
										name="horaPartida" format="HH:mm" /></td>
								<td><s:property value="" /></td>
								<td><s:url id="detalleURL" action="detalleViaje">
										<s:param name="id" value="%{id}"></s:param>
									</s:url> <s:a href="%{detalleURL}" class="btn btn-default btn-xs">ver detalle</s:a>
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