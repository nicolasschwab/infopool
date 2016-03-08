<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">				
	<div class="col-sm-12 padding-both-zero">
		<p class="header-title">Viajes</p>
		<div class="menu-seccion">
			<div class="row">
				<div class="col-sm-12">
					<s:form action="BusquedaParametrizadaViaje">									
						<div class="form-group col-sm-3">
						  	<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" placeholder="Direccion de Origen"/>
						</div>
						<div class="form-group col-sm-3">
						  	<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" placeholder="Direccion de Destino"/>
						</div>
						<div class="form-group col-sm-2">
						  	<s:textfield cssClass="form-control" id="fechaViaje" name="fechaViaje" placeholder="Fecha de Viaje" data-date-format="YYYY-MM-DD"/>
						</div>
						<div class="form-group col-sm-3">						  	
						  	<s:textfield cssClass="form-control" name="evento" placeholder="Evento"/>
						</div>
						<div class="form-group col-sm-1">							
							<s:submit cssClass="btn btn-default" value="%{getText('global.buscar')}" />
						</div>
					</s:form>								
				</div>
			</div>					      					    						
		</div>
	</div>
</div>	