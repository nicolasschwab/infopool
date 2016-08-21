<div class="col-sm-3 padding-both-zero">
	<div class="panel-header-seccion">
		<h3 class="h3BusquedaViaje" >Buscá en el mapa!</h3>
		<div id="recorridoFlotante" class="recorridoFlotante">
			<div class="flotante">
				En lugar de escribir las direcciones, seleccionalas en el mapa!
			</div>
			<div>
				<i onclick="cerrarFlotanteBusqueda()" class="fa fa-times cerrar"  aria-hidden="true"></i>
			</div>
		</div>
		<div id="moverMarcadores" class="recorridoFlotante" style="display:none">
			<div class="flotante">
				Bien! Recuerda que puedes mover los marcadors y modificar el radio de busqueda de las direcciones
			</div>
			<div>
				<i onclick="cerrarMoverMarcadores()" class="fa fa-times cerrar"  aria-hidden="true"></i>
			</div>
		</div>
		<div class="range">
			<div class="mensaje">
				<span>Modifica el radio de busqueda!</span>
			</div>
			<div>
				<input class="rangeBusquedaViaje" type="range" id="distancia" min="0" max="1000" step="10"  value="$('#rango').attr('value')" >
			</div>
		</div>
	</div>
	<div class="panel-menu-seccion shadow-box " id="map-canvas" style="height: 500px">			    		
<!--    		<ul class="nav"> -->
<!--    			<li><a href="RegistrarViaje" class="btn">Registrar un Viaje <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="BusquedaViaje" class="btn">Busqueda de Viajes <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="Solicitudes" class="btn">Solicitudes de Viajes <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="Calificaciones" class="btn">Calificaciones <span class="fa fa-chevron-right"></span></a></li> -->
<!--    		</ul> -->
   	</div>			      	
<!--    	<div class="panel-menu-seccion shadow-box">	 -->
<!--    		<p>Ponte al 100%</p>		    		 -->
<!--    		<ul class="nav"> -->
<!--    			<li><a href="RegistrarViaje" class="btn">Registrar un Viaje <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="BusquedaViaje" class="btn">Busqueda de Viajes <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="Solicitudes" class="btn">Solicitudes de Viajes <span class="fa fa-chevron-right"></span></a></li> -->
<!--    			<li><a href="Calificaciones" class="btn">Calificaciones <span class="fa fa-chevron-right"></span></a></li> -->
<!--    		</ul> -->
<!--    	</div>			      				      	 -->
</div>