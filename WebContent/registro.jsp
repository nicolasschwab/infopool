<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>InfoPool</title>
<%@ include file="views/heads.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body>

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
		<jsp:param name="itemActivo" value="99" />
	</jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="col-md-6 margentb2">
				<h3>Formulario de registro</h3>
				<br>	
				<s:if test="getFieldErrors() != null">
                    <s:if test="getFieldErrors().get('loginError') != null">
                    	<div class="errorMessage">
                       		<s:property value="getFieldErrors().get('loginError')[0]" />
                       	</div>
                    </s:if>
                </s:if>			
				<s:form cssClass="form-signin" theme="simple" role="form" action="registrarUsuario" method="POST" enctype="multipart/form-data" validate="true">
					<div class="form-group">
						<label>Nombres</label>
						<s:textfield cssClass="form-control" name="nombreUsuario" label="Ingrese su nombre" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('nombreUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('nombreUsuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Apellido</label>
						<s:textfield cssClass="form-control" name="apellidoUsuario" label="Ingrese su apellido" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('apellidoUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('apellidoUsuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Ingrese su fecha de nacimiento</label>
						<div class='input-group date datepicker'>
							<s:textfield cssClass="form-control" label="Fecha de nacimiento" name="fechaUsuario" data-date-format="YYYY-MM-DD"/>
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('fechaUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('fechaUsuario')[0]" />	                           		
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>					
					<div class="form-group">
						<label>Tel&eacute;fono</label>
						<s:textfield cssClass="form-control" name="telefonoUsuario" label="Ingrese su tel&eacute;fono" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('telefonoUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('telefonoUsuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>E-mail</label>
						<s:textfield cssClass="form-control" name="mailUsuario" label="Ingrese su e-mail" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('mailUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('mailUsuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Nombre Usuario</label>
						<s:textfield cssClass="form-control" name="usuario" label="Ingrese su nombre" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('usuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('usuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Contrase&ntilde;a</label>
						<s:password cssClass="form-control" name="clave" label="Ingrese la contrase&ntilde;a" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('clave') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('clave')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Repetir contrase&ntilde;a</label>
						<s:password cssClass="form-control" name="rClave" label="Re ingrese la contrase&ntilde;a" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('rClave') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('rClave')[0]" />	                           		
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>					
					<div class="form-group">
						<label>Ingrese sus preferencias</label>
						<s:textarea cssClass="form-control" label="prefUsuario" name="prefUsuario" rows="10" />
						<s:if test="getFieldErrors() != null">
	                        <s:if test="getFieldErrors().get('prefUsuario') != null">
	                        	<div class="errorMessage">
	                           		<s:property value="getFieldErrors().get('prefUsuario')[0]" />
	                           	</div>
	                        </s:if>
	                    </s:if>
					</div>
					<div class="form-group">
						<label>Seleccione la imagen de perfil</label>
						<s:file name="fperfilUsuario" label="Suba su foto de perfil"/>
					</div>
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Registrar" />
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<!-- end main -->

	<%@ include file="views/footer.jsp"%>

</body>
</html>