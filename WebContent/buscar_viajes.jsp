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
		<div class="container">			
			<div class="main">
				<div class="row">				
					<div class="tab-buscadores">
						<div role="tabpanel">
							<ul id="myTab" class="nav nav-tabs" data-tabs="tabs">
								<li class="active"><a href="#bsqdireccion" data-toggle="tab">Buscar por Dirección</a></li>
								<li><a href="#bsqevento" data-toggle="tab">Buscar por Evento</a></li>
								<li><a href="#bsqfecha" data-toggle="tab">Buscar por Fecha</a></li>
							</ul>
						</div>
						<br>					
						<div id="my-tab-content" class="tab-content">
							<div class="tab-pane active" id="bsqdireccion">
								<s:form cssClass="form-signin" theme="simple" role="form" action="listarPorDireccion">
									<div class="col-md-12 tab-contentbsq">
										<div class="form-group col-md-5">
											<label for="exampleInputEmail1">Direcci&oacute;n Origen</label>
											<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" placeholder="Ingrese la direccion de origen" />
										</div>
										<div class="form-group col-md-5">
											<label for="exampleInputPassword1">Direcci&oacute;n Destino</label>
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
							</div>
							
							<div class="tab-pane" id="bsqevento">
								<s:form cssClass="form-signin" theme="simple" role="form" action="buscarViajePorEvento">
									<div class="col-md-12 tab-contentbsq">								
										<div class="form-group col-md-5">
											<label for="exampleInputEmail1">Evento</label>
											<s:select list="eventoLista" listKey="id" listValue="nombre" name="evento_id" value="eventoLista.{id}" cssClass="form-control" />
										</div>
										<div class="form-group col-md-1">
											<br>
											<s:submit cssClass="btn btn-primary" value="Buscar" />
										</div>
									</div>
			
								</s:form>
							</div>
							
							<div class="tab-pane" id="bsqfecha">
								<s:form cssClass="form-signin" theme="simple" role="form" action="listarPorFecha">
									<div class="col-md-12 tab-contentbsq">
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
									</div>		
								</s:form>
							</div>						
						</div>					
					</div>
					<s:fielderror />
				</div>
								
				<div class="row">	
					<s:if test="%{viajeLista!=null}">
						<s:if test="%{viajeLista.size()>0}">						
							<h3 class="tituloSeccion">Viajes Encontrados</h3>
							<table class="table" id="datatable">
								<thead>
									<tr>
										<th>Origen</th>
										<th>Destino</th>
										<th>Fecha</th>
										<th>Evento</th>
										<th>Acciones</th>
									</tr>
								</thead>
								<tbody>						
									<s:iterator value="viajeLista">
										<tr>
											<td><s:property value="direccionOrigen" /></td>
											<td><s:property value="direccionDestino" /></td>
											<td><s:date name="fechaInicio" format="dd/MM/YYYY" /> <s:date name="horaPartida" format="HH:mm" /></td>
											<td>
												<s:if test="%{evento==null}">
													-
												</s:if>
												<s:else>
													<s:property value="evento.nombre" />
												</s:else>
											</td>
											<td><s:url id="detalleURL" action="detalleViaje">
													<s:param name="id" value="%{id}"></s:param>
												</s:url> <s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs">ver detalle</s:a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<p>No se encontraron resultados</p>
						</s:else>	
					</s:if>					
				</div>			
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>