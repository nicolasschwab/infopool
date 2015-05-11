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
								<li class="active"><a href="#bsqdireccion" data-toggle="tab"><s:text name="busqueda.bsqdireccion" /></a></li>
								<li><a href="#bsqevento" data-toggle="tab"><s:text name="busqueda.bsqevento" /></a></li>
								<li><a href="#bsqfecha" data-toggle="tab"><s:text name="busqueda.bsqfecha" /></a></li>
							</ul>
						</div>
						<br>					
						<div id="my-tab-content" class="tab-content">
							<div class="tab-pane active" id="bsqdireccion">
								<s:form cssClass="form-signin" theme="simple" role="form" action="listarPorDireccion">
									<div class="col-md-12 tab-contentbsq">
										<div class="form-group col-md-5">
											<label><s:text name="viaje.dirorigen"/></label>
											<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" />
										</div>
										<div class="form-group col-md-5">
											<label><s:text name="viaje.dirdestino"/></label>
											<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" />
										</div>
										<div class="col-md-2 form-group">
											<br>
											<s:submit cssClass="btn btn-primary" value="%{getText('global.buscar')}" />
										</div>
									</div>
								</s:form>
							</div>
							
							<div class="tab-pane" id="bsqevento">
								<s:form cssClass="form-signin" theme="simple" role="form" action="buscarViajePorEvento">
									<div class="col-md-12 tab-contentbsq">								
										<div class="form-group col-md-5">
											<label><s:text name="viaje.evento" /></label>
											<s:select list="eventoLista" listKey="id" listValue="nombre" name="evento_id" value="eventoLista.{id}" cssClass="form-control" />
										</div>
										<div class="form-group col-md-1">
											<br>
											<s:submit cssClass="btn btn-primary" value="%{getText('global.buscar')}" />
										</div>
									</div>
			
								</s:form>
							</div>
							
							<div class="tab-pane" id="bsqfecha">
								<s:form cssClass="form-signin" theme="simple" role="form" action="listarPorFecha">
									<div class="col-md-12 tab-contentbsq">
										<div class="form-group col-md-5">
											<label><s:text name="busqueda.fecha"/></label>
											<div class='input-group date datepicker'>
												<s:textfield type='text' onfocus="this.blur()" name="fecha" id="fecha" data-date-format="YYYY-MM-DD" cssClass="form-control" />
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
										<div class="form-group col-md-2">
											<br>
											<s:submit cssClass="btn btn-primary" value="%{getText('global.buscar')}" />
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
							<h3 class="tituloSeccion"><s:text name="busqueda.viajelistado" /></h3>
							<table class="table" id="datatable">
								<thead>
									<tr>									
										<th><s:text name="viaje.dirorigen" /></th>
										<th><s:text name="viaje.dirdestino" /></th>
										<th><s:text name="viaje.fechapartida" /></th>
										<th><s:text name="viaje.eventolbl" /></th>
										<th><s:text name="global.acciones" /></th>
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
												</s:url> <s:a href="%{detalleURL}" cssClass="btn btn-default btn-xs"><s:text name="global.verdetalle" /></s:a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<p><s:text name="busqueda.viajelistadonulo" /></p>
						</s:else>	
					</s:if>					
				</div>			
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>