<%@ taglib prefix="s" uri="/struts-tags"%>				
	<div class="col-sm-4 padding-both-zero">		
		<div class="menu-seccion">
			<h3 class="h3BusquedaViaje" >Buscá tu viaje!</h3>
			<div class="row">
				<div class="col-sm-12" style="height: 312px">
					<s:form action="BusquedaParametrizadaViaje" role="form" theme="simple" onsubmit="return validateForm()" cssClass="form-datos">
						<section>
							<div class="form-group col-sm-12 menu-div">
								<label>Direccion origen</label>
							  	<s:textfield cssClass="form-control" id="dirOrigen" name="direccionOrigen" placeholder="Direccion de Origen"/>
							</div>
						</section>
						<section >
							<div class="form-group col-sm-12 menu-div">
								<label>Direccion destino</label>
							  	<s:textfield cssClass="form-control" id="dirDestino" name="direccionDestino" placeholder="Direccion de Destino"/>
							</div>
						</section>
						<section>
							<div class="form-group col-sm-12 menu-div">
								<label>Fecha del viaje</label>
							  	<s:textfield cssClass="form-control" id="fechaViaje" name="fechaViaje" placeholder="Fecha de Viaje" data-date-format="YYYY-MM-DD"/>
							</div>
						</section>
						<section>
							<div class="form-group col-sm-12 menu-div" >
								<div class="mensaje">
									<span>Modifica el radio de busqueda!</span>
								</div>
								<div>
									<input class="rangeBusquedaViaje" type="range" id="distancia" min="0" max="1000" step="10"  value="500" >
								</div>
							</div>
						</section>
<!-- 						<div class="form-group col-sm-12">						  	 -->
<%-- 						  	<s:textfield cssClass="form-control" name="evento" placeholder="Evento"/> --%>
<!-- 						</div> -->
						<s:hidden id="rango" name="rango" value=""></s:hidden>
						<div class="form-group col-sm-4 separetor"></div>
						<div class="form-group col-sm-4"  style="margin-top: 10px;">							
							<s:submit cssClass="btn btn-default" value="%{getText('global.buscar')}" />
						</div>
					</s:form>								
				</div>
			</div>					      					    						
		</div>
	</div>
