<% request.getSession(true);%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body onload="inicializarEdicionEvento();">

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="2" />
	</jsp:include>

	<div class="main_bg">
		<div class="container">
			<div class="col-md-12">
				<h3 class="tituloSeccion margentb2"><s:text name="evento.edicionlbl" /></h3>
				<s:fielderror />
				<s:form cssClass="form-signin" role="form" theme="simple" action="editarEvento">					
					<div class="form-group">
						<label><s:text name="evento.nombre" /> (<s:text name="global.camporequerido" />)</label>
							<s:if test="%{evnt.nombre.length()>0}">
								<s:textfield cssClass="form-control" name="nombre" label="Nombre del evento" value="%{evnt.nombre}"/>
							</s:if>
							<s:else>
								<s:textfield cssClass="form-control" name="nombre" label="Nombre del evento"/>
							</s:else>
					</div>
					<div class="form-group">
						<label><s:text name="evento.fecha" /> (<s:text name="global.camporequerido" />)</label>						
						<div class='input-group date col-md-3' id='datetimepicker2'>
							<s:if test="%{evnt.fechaHora!=null}">
								<s:date name="evnt.fechaHora" id="fechaHora" format="yyyy-MM-dd HH:mm"/>
							</s:if>
							<s:textfield cssClass="form-control" name="fechaHora" data-date-format="YYYY-MM-DD HH:mm"/>
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<div class="form-group">
						<label><s:text name="evento.web" /></label>
						<s:if test="%{evnt.web.length()>0}">
							<s:textfield cssClass="form-control" name="web" label="Ingrese la Web" value="%{evnt.web}"/>
						</s:if>
						<s:else>
							<s:textfield cssClass="form-control" name="web" label="Ingrese la Web"/>
						</s:else>							
					</div>
					<div class="form-group">
						<label><s:text name="evento.ubicacion" /> (<s:text name="global.camporequerido" />)</label>
						<s:if test="%{evnt.ubicacion.length()>0}">
							<s:textfield cssClass="form-control" id="dirOrigen" name="ubicacion" label="Ingrese la direcci&oacute;n" value="%{evnt.ubicacion}" />
						</s:if>
						<s:else>
							<s:textfield cssClass="form-control" id="dirOrigen" name="ubicacion" label="Ingrese la direcci&oacute;n" />
						</s:else>
					</div>
					<div class="form-group">
						<label><s:text name="evento.ubicaciongm" /></label>
						<button type="button" class="btn btn-default" onclick="javascript:calcPoint();"><s:text name="evento.localizargm" /></button>
						<br>
						<br>
						<div id="map-canvas" style="width: 100%; height: 500px"></div>
					</div>					
					<div class="form-group">				
						<s:if test="%{evnt.id!=null}">		
							<s:hidden name="id" value="%{evnt.id}"></s:hidden>
						</s:if>
						<s:else>
							<s:hidden name="id"></s:hidden>
						</s:else>
						<s:submit cssClass="btn btn-primary" value="%{getText('global.editar')}" />
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<%@ include file="views/footer.jsp"%>
</body>
</html>