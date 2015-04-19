<?php
    session_start();    
?>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
    <%@ include file="views/heads.jsp" %>
</head>
<body>

   <%@ include file="views/header.jsp" %>

 <%@ include file="views/menu.jsp" %>
<div class="main_bg"><!-- start main -->    
    <div class="container">
        <div class="main row">
        	<div class="col-md-12">
			  <div class="col-md-6">
			  	<h3 class="tituloSeccion">Denunciante</h3>
			  	<div class="col-md-4">
			  		<img src="images/c4.jpg" alt="foto conductor" class="img-thumbnail">
			  	</div>
			  	<div class="col-md-8">
			  		<p><strong>Nombre:</strong> Rodrigo Perez</p>
		  			<p><strong>Viajero desde:</strong> 06/09/2013</p>
		  			<p><strong>Calificacion:</strong> 1.2 de 5</p>
		  			<p><strong>Estado:</strong> Activo</p>
			  	</div>
			  </div>
			  <div class="col-md-6">
			  	<h3 class="tituloSeccion">Denunciado</h3>
			  	<div class="col-md-4">
			  		<img src="images/c3.jpg" alt="foto conductor" class="img-thumbnail">
			  	</div>
			  	<div class="col-md-8">
			  		<p><strong>Nombre:</strong> Juana Rodriguez</p>
		  			<p><strong>Viajero desde:</strong> 06/09/2013</p>
		  			<p><strong>Calificacion:</strong> 4.2 de 5</p>
		  			<p><strong>Estado:</strong> Activo</p>
			  	</div>
			  </div>

			  <div class="col-md-12">
			   		<div class="col-md-12">
			   			<h3 class="tituloSeccion">Motivo Denuncia</h3>
			   			<p>Execeso de velocidad</p>
			   		</div>
			   		<div class="col-md-12">
			   			<h3 class="tituloSeccion">Mensaje Denuncia</h3>
			   			<p>A pesar de mis insistentes pedidos no bajo la velocidad</p>
			   		</div>
			  </div>

			  <div class="col-md-12">			  	
			  	<div class="col-md-8">
				  	<h3 class="tituloSeccion">Resoluci&oacute;n de la denuncia</h3>
				  	<form>
				  		<div class="form-group">				  			
						  	<select class="form-control">
						  		<option>A favor del denunciante</option>
						  		<option>A favor del denunciado</option>
						  		<option>Caso omiso</option>
						  	</select>
						</div>
						<div class="form-group">
				  			<button class="btn btn-primary">Enviar</button>
				  		</div>
				  	</form>
			  	</div>			  	
			  </div>
			</div>
		</div>
    </div>
</div><!-- end main -->
 <%@ include file="views/footer.jsp" %>
</body>
</html>