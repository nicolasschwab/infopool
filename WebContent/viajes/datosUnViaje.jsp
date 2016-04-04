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
				<div class="viaje_item_main_content col-sm-8">
					<span><s:date name="fechaInicio" format="dd/MM/YYYY"></s:date></span>
					<span><s:property value="direccionOrigen" /></span>
					<span><s:property value="direccionDestino" /></span>
				</div>		
				<div class="viaje_item_review_content col-sm-4">
					<span class="fa fa-user" aria-hidden="true"> asientos</span>										
					<span class="fa fa-retweet" aria-hidden="true"> tramo</span>				
					<span class="fa fa-map-marker" aria-hidden="true"> km</span>
					<span class="fa fa-calendar" aria-hidden="true"> tipo viaje</span>		
				</div>
			</div>
		</div>
	</div>	
</div>