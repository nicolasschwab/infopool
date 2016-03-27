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
		  		<div class="col-md-1"></div>
		  		<div class="col-md-2 barraLateral">
		  			<div class="menuBarra">	  			
						<ul class="listaPerfil">						
							<li id="infoTi" onclick="javascript:anclaInfoTi()"><i class="fa fa-user iconoBarra"></i>Informacion sobre ti</li>
							<li id="contrasena" onclick="javascript:anclaContrasena()"><i class="fa fa-lock iconoBarra"></i>Contraseña</li>
							<li id="infoPer" onclick="javascript:anclaInfoPersonal()"><i class="fa fa-male iconoBarra"></i>Informacion Personal</li>
							<li id="infoAuto" onclick="javascript:anclaInfoAuto()"><i class="fa fa-car iconoBarra"></i>Informacion de tu auto</li>
						</ul>
					</div>	  			
		  		</div>
		  		<s:fielderror />
		  		<div class="col-md-8">
		  			<fieldset id="editarInfoTi" class="fieldsetPerfil" >
		  				<legend>Información sobre ti</legend>
		  				<div class="row rowEditarPerfil"><div class="col-md-12"><s:text name="perfil.acercaDeTi" /></div></div>
		  				<div class="row rowEditarPerfil">
		  					<div class="col-md-3"><strong><s:text name="perfil.informacionSobreTi" />:</strong></div>
			  				<div class="col-md-8 botonImagenEdicion">
				  				<div class="col-md-4">
				  					<div class="modal fade myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
									  <div class="modal-dialog modal-lg">
									    <div class="modal-content popUpImagen">
									        <div class="modal-header">
									          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
									          <h4 class="modal-title" id="myLargeModalLabel"><s:text name="viajero.seleccionaFoto" /></h4>
									        </div>
									        <div class="modal-body">
									        	<s:form cssClass="form-datos" theme="simple" role="form" id="registroForm" action="modificarImagen" method="POST" enctype="multipart/form-data">
									       			<s:file name="fperfilUsuarioEdicion" />
									       			<s:submit cssClass="btn btn-primary popUpBoton" value="%{getText('global.editar')}"/>
									       		</s:form>															          
									        </div>      
									    </div>
									  </div>
									</div>
				  					<img src="<s:url action="ImageAction">
											<s:param name="id" value="%{user.id}"></s:param>
										</s:url>" class="img-thumbnail">
								</div>
								<div class="md-col-4">
									<button type="button" class="btn btn-xs btn-info" data-toggle="modal" data-target=".myModal"><s:text name="viajero.cambiarImagen" /></button>									
				  				</div>
			  				</div>
		  				</div>
		  				<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.nombreUsuario" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:textfield name="usuarioEdicion" id="usuarioEdicion" value="%{user.usuario}"/></div><div class="col-md-2"><div id="spinerUs" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkUs" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
			  			<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.preferencias" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><i class="fa fa-paw"></i></div></div></div>		  				
		  				<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.calificacion" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:property value="user.calificacion"/>/5</div></div></div>
		  				<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.fechaingreso" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:date name="user.fechaIngresoSistema" format="YYYY-MM-dd"/></div></div></div>
		  			</fieldset>
		  			<fieldset id="editarContrasenia" class="fieldsetPerfil" >
		  				<legend ><s:text name="perfil.contrasena" /></legend>
		  				<form id="formContrasena">
		  					<div class="row rowEditarPerfil"><div class="col-md-3 errorEdicion" id="divRespuesta"></div></div>	
			  				<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.claveActual" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:password id="claveActualEdicion" name="claveActualEdicion" value="" /></div></div></div>
							<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.clave" />:</strong> </div><div class="col-md-8"><div class="col-md-6"> <s:password id="claveNuevaEdicion" name="claveNuevaEdicion" /></div></div></div>
							<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.reingresarclave" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:password id="repetirClaveEdicion" name="repetirClaveEdicion" /></div></div></div>
							<input type="hidden" name="<%=(session.getAttribute("tokenId"))%>" value="<%=(session.getAttribute("tokenValue"))%>">
							<div class="col-md-2"><div id="spinerContrasena" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkContrasena" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div><input type="button" value="modificar" class="btn btn-primary botonContrasena" id="botonContrasena">
						</form>
				  	</fieldset>
				  		
				  		
			  		<!-- Fieldset con los datos de usuario -->  	
			  		<fieldset id="editarInfoPersonal" class="fieldsetPerfil">
			  			<legend ><s:text name="perfil.informacionPersonal" /></legend>
			  			<div class="row rowEditarPerfil"><div class="col-md-12"><s:text name="perfil.mensajeInfoPersonal" /></div></div>
			  			<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.nombre" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:textfield name="nombreUsuarioEdicion" cssClass="nombreEditar" id="nombreUsuarioEdicion" value="%{user.nombre}"/></div><div class="col-md-2"><div id="spinerNom" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkNom" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
				  		<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.apellido" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:textfield name="apellidoUsuarioEdicion" id="apellidoUsuarioEdicion" value="%{user.apellido}" /></div><div class="col-md-2"><div id="spinerApe" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkApe" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
				  		<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.mail" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:textfield name="mailUsuarioEdicion" id="mailUsuarioEdicion" value="%{user.mail}" /></div><div class="col-md-2"><div id="spinerMail" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkMail" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
				  		<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.telefono" />:</strong> </div><div class="col-md-8"><div class="col-md-6"> <s:textfield name="telefonoUsuarioEdicion" id="telefonoUsuarioEdicion" value="%{user.telefono}"/></div><div class="col-md-2"><div id="spinerTel" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkTel" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
						<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="viajero.fechanacimiento" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:date name="user.fechaNacimiento" format="YYYY-MM-dd" id="fechaNac" /> <s:textfield name="fNacimientoUsuarioEdicion" id="fNacimientoUsuarioEdicion" data-date-format="YYYY-MM-DD" value="%{fechaNac}"/></div><div class="col-md-2"><div id="spinerFNac" style="display:none"><jsp:include page="../spiner.jsp"></jsp:include></div><div id="checkFNac" style="display:none"><i style="color:green" class="fa fa-check"></i> Guardado</div></div></div></div>
				  		
				  	</fieldset>
				  	<!-- Fieldset con los datos de usuario -->  	
			  		<fieldset id="editarInfoAuto" class="fieldsetPerfil">
			  			<legend ><s:text name="perfil.informacionAuto" /></legend>
			  			<form id="editarAuto" >
			  			<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="vehiculo.marca" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><s:select emptyOption="true" list="marcas"  listKey="id" listValue="nombre"  name="vistaMarca" value="4" cssClass="form-control" /></div></div></div>
			  			<div class="row rowEditarPerfil"><div class="col-md-3"><strong><s:text name="vehiculo.modelo" />:</strong> </div><div class="col-md-8"><div class="col-md-6"><select id="modelos" name="modelos" class="form-control"><option></option></select></div></div></div>
			  			<div class="row rowEditarPerfil"><div class="col-md-3 derecha"><s:submit cssClass="btn btn-primary " value="%{getText('global.registrar')}"></s:submit></div></div>
			  			</form>
				  	</fieldset>
				</div>
		  	
		  	
		  </div>
		</div>
    </div>
</div><!-- end main -->
 <%@ include file="../views/footer.jsp" %>
</body>
</html>