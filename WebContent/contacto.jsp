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
		<jsp:param name="itemActivo" value="5" />
	</jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="col-md-10">
				<h3>Contacto</h3>
				<s:fielderror />
				<s:form cssClass="form-horizontal" theme="simple" role="form" action="registrarUsuario" method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-md-2 control-label">Nombre</label>
						<div class="col-md-10">
							<s:textfield cssClass="form-control" name="nombreUsuario" label="Ingrese su nombre" required="true" />
						</div>
					</div>									
					<div class="form-group">
						<label class="col-md-2 control-label">Tel&eacute;fono</label>
						<div class="col-md-10">
							<s:textfield cssClass="form-control" name="telefonoUsuario" label="Ingrese su tel&eacute;fono" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">E-mail</label>
						<div class="col-md-10">
							<s:textfield cssClass="form-control" name="mailUsuario" label="Ingrese su e-mail" required="true" />
						</div>
					</div>					
					<div class="form-group">
						<label class="col-md-2 control-label">Comentario</label><br>
						<div class="col-md-10">
							<s:textarea cssClass="form-control" label="prefUsuario" name="prefUsuario" rows="10" />
						</div>
					</div>					
					<br>
					<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Enviar" />
					</div>
				</s:form>				  
			</div>
		</div>
	</div>
	<!-- end main -->

	<%@ include file="views/footer.jsp"%>

</body>
</html>