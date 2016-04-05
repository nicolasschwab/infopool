<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="viaje_item row">	 
	<div class="viaje_item_imagen col-sm-2">
		<a href="#">									    	
	    	<img src="<s:url action="ImageAction">
				<s:param name="id" value="%{conductor.id}"></s:param>
			</s:url>" alt="conductor" width="100" height="100">
	    </a>
	</div>
	<div class="col-sm-10">
		<div class="row">
			<div class="viaje_item_content">
				<div class="viaje_item_main_content col-sm-9">					
					<s:a href="DetalleViaje?id=%{id}">
						<span class="direccion_viaje"><s:property value="direccionOrigen" /> - <s:property value="direccionDestino" /></span>
					</s:a>
					<s:if test="%{tipoViaje.name()=='PUNTUAL'}">
						<span class="fecha_viaje"><s:date name="fechaInicio" format="dd/MM/YYYY"></s:date></span>
					</s:if>
					<s:else>
						<span class="fecha_viaje">Del <s:date name="fechaInicio" format="dd/MM/YYYY"></s:date> al <s:date name="fechaFin" format="dd/MM/YYYY"></s:date></span>
					</s:else>
					<span class="descripcion_viaje"><s:property value="descripcion"/></span>
				</div>		
				<div class="viaje_item_review_content col-sm-3">
					<span class="icono_kilometros_res"><span><s:property value="kilometros"/>km</span></span>
					<s:if test="%{tipoViaje.name()=='PUNTUAL'}">
						<span class="icono_tramo_res"><span><s:property value="getFrecuencias()[0].tramoViaje"/></span></span>
						<span class="icono_tipoviaje_res"><span>Puntual</span></span>
						<span class="icono_asientos_res"><span><s:property value="getFrecuencias()[0].asientosDisponibles"/></span></span>						
					</s:if>
					<s:else>
						<span class="icono_tramo_res"><span><s:property value="getFrecuencias()[0].tramoViaje"/></span></span>
						<span class="icono_tipoviaje_res"><span>Periodico</span></span>
						<span class="icono_asientos_res"><span>verificar frecuencia</span></span>
					</s:else>					
					<s:a cssClass="btn btn_infoviaje" href="DetalleViaje?id=%{id}">Ver viaje</s:a>
				</div>
			</div>
		</div>
	</div>	
</div>