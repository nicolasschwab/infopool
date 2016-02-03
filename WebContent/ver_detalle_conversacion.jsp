<% request.getSession(true); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
    <%@ include file="views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<body> 
    
    <%@ include file="views/header.jsp" %>
    <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="4"/>
    </jsp:include>
    
    <div class="main_bg">
    	<div class="container">
    		<div class="row subMenu">
    			<div class="col-md-12">
    				<div class="col-md-2 informacion">
    					<s:url id="viaje" action="detalleViaje" encode="true">
							<s:param name="id" value="%{conversacion.viaje.id}"></s:param>
						</s:url>
    					<s:a href="%{viaje}">    					
	    					<div class="texto">
		    					<s:text name="viaje.detallelbl"></s:text>
		    				</div>
	    				</s:a>
	    			</div>
	    			<div class="col-md-3 informacion">
	    				<div class="texto">
		    				<div class="mediano">
		    					<i class="fa fa-circle-o"></i>
		    					<s:text name="viaje.dirorigen"></s:text>
		    				</div>
		    				<div>
		    					<s:property value="conversacion.viaje.direccionOrigen"/>
		    				</div>
	    				</div>
	    			</div>
	    			<div class="col-md-3 informacion">
	    				<div class="texto">
		    				<div class="mediano">
		    					<i class="fa fa-map-marker"></i>
		    					<s:text name="viaje.dirdestino"></s:text>
		    				</div>
		    				<div>
		    					<s:property value="conversacion.viaje.direccionDestino"/>
		    				</div>
	    				</div>
	    			</div>
	    			<div class="col-md-2 informacion">
	    				<div class="texto">
		    				<div class="mediano">
		    					<i class="fa fa-calendar"></i>
		    					<s:text name="viaje.fechavp"></s:text>
		    				</div>
		    				<div>		    					
		    					<s:date name="conversacion.viaje.fechaInicio" format="dd/MM/YYYY" />
		    				</div>
	    				</div>
	    			</div>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-12">
    				<div class="col-md-4">
    					<div class="row">
	    					<div class="col-md-12 paddingTop paddingLeft">
	    						<div class="border">
			    					<s:iterator value="conversacionVista">
			    						<s:a href="http://localhost:8080/infopool/detalle?id=%{conversacion.id}">
			    						<s:iterator value="conversacion.participantes">
			    								<s:if test="%{#parameters.id[0]==conversacion.id}">
			    									<div class="mensajeLista seleccionado">
			    								</s:if>	    							
					    						<s:else>
					    							<div class="mensajeLista">
			    								</s:else>
			    								
					    							<div class="imagenLista">
					    								<img src="<s:url action="ImageAction">
															<s:param name="id" value="%{id}"></s:param>
														</s:url>" alt="foto conductor" class="img-responsive ">
					    							</div>
					    							<div class="datosMensaje">
						    							<div class="nombreLista">			    						
								    							<s:property value="nombre"/>
								    							<s:property value="apellido"/>			    						
							    						</div>
							    						<div class="ultimoMensaje">
							    							<s:property value="ultimoMensaje.detalle"/>
							    						</div>
						    						</div>
						    						<div class="ultimaFecha">
						    							<i class="fa fa-mobile"></i>
						    							<time datetime=""><s:date name="ultimoMensaje.fecha" format="dd/MM/YYYY" /> </time>
						    						</div>
					    						</div>				    						
			    							</s:iterator>
			    						</s:a>
			    						<hr>
			    					</s:iterator>
		    					</div>
	    					</div>
    					</div>
    				</div>
    				<div class="col-md-8">
    					<div class="row">
						<div class="col-md-12 paddingTop">
							<div class="row chat-window" id="chat_window_1">
								<div class="col-md-12">
									<div class="panel panel-default" id="panel">																				
										<s:iterator value="conversacion.mensajes" >
											<div class="panel-body msg_container_base sinPaddingBot">
												<div class="row msg_container base_sent">
													<s:if test="%{user.id==emisor.id}">
														<div class="col-md-1 avatar soyYoDetalle">
													</s:if>
													<s:if test="%{user.id!=emisor.id}">
														<div class="col-md-1 avatar ">
													</s:if>
														<s:url id="detalleViajero" action="detalleViajero" encode="true">
															<s:param name="id" value="%{emisor.id}"></s:param>
														</s:url>
														<s:a href="%{detalleViajero}">
															<img src="<s:url action="ImageAction">
																<s:param name="id" value="%{emisor.id}"></s:param>
															</s:url>" alt="foto conductor" class="img-responsive ">
														</s:a>
													</div>													
													<div class="col-md-11 sinPadding">
														<div class="messages msg_sent">
															<s:if test="%{user.id==emisor.id}">
																<div class="nombre-mensaje soyYoDetalle">
															</s:if>
															<s:if test="%{user.id!=emisor.id}">
																<div class="nombre-mensaje">
															</s:if>
																<s:a href="%{detalleViajero}">
																	<p class="nombreMensaje">
																		<s:property value="emisor.nombre" />
																		<s:property value="emisor.apellido"/>
																	</p>
																</s:a>
																<div class="detalleMensaje">
																	<p><s:property value="detalle" /></p>
																</div>																
															</div>
															<s:if test="%{user.id==emisor.id}">
																<div class="fechaMensaje soyYoDatos">
															</s:if>
															<s:if test="%{user.id!=emisor.id}">
																<div class="fechaMensaje">
															</s:if>															
																<i class="fa fa-mobile"></i>
																<time datetime=""><s:date name="fecha" format="dd/MM/YYYY H:m" /> </time>
															</div>
														</div>
													</div>													
												</div>	
											</div>
											<div class="clearfix"></div>
										</s:iterator>
									</div>
									<s:if test="%{conversacion!=null}">
										<div class="panel-footer">
											<div>
												<s:form role="form" action="responderMensaje">
													<s:hidden  name="id" value="%{conversacion.id}"> </s:hidden>
													<input id="btn-input" name="detalle" type="text" class="form-control input-sm chat_input" placeholder="<s:text name='viaje.chatmsg' />" /> 
													<span class="input-group-btn"><s:submit cssClass="btn btn-primary" value="%{getText('global.enviar')}"/></span>
												</s:form>
											</div>
										</div>
									</s:if>
								</div>
							</div>
						</div>
					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
</body>
</html>