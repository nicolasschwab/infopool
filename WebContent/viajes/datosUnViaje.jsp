	<%@ taglib prefix="s" uri="/struts-tags" %>

		<div class="media">	 
			<div class="media-left">
				<a href="#">									    	
			    	<img class="media-object" src="<s:url action="ImageAction">
						<s:param name="id" value="%{conductor.id}"></s:param>
					</s:url>" alt="conductor" width="90" height="90">
			    </a>
			</div>
			<div class="media-body">
				<s:a cssClass="aViaje" href="DetalleViaje?id=%{id}">
					<p><s:date name="fechaInicio" format="dd/MM/YYYY"></s:date></p>
					<p><s:property value="direccionOrigen" /></p>
					<p><s:property value="direccionDestino" /></p>
				</s:a>
			</div>
			<div class="media-right">
				<s:a cssClass="aViaje" href="DetalleViaje?id=%{id}">										
					<span class="fa fa-user" aria-hidden="true"></span> asientos<br>										
					<span class="fa fa-retweet" aria-hidden="true"></span> tramo<br>						
					<span class="fa fa-map-marker" aria-hidden="true"></span> km<br>
					<span class="fa fa-calendar" aria-hidden="true"></span> tipo viaje
				</s:a>								
			</div>
		</div>
