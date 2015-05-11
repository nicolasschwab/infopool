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
	        <div class="main row">            
	            <h3 class="tituloSeccion"><s:text name="mensaje.misMensajes"/></h3>	            
	            <table class="table" id="datatable">
	              <thead>
	                <tr>                  
	                  <th><s:text name="mensaje.emisor" /></th>                  
	                  <th><s:text name="mensaje.asunto" /></th>
	                  <th><s:text name="mensaje.fecha" /></th>
	                  <th><s:text name="mensaje.estado" /></th>
	                  <th><s:text name="global.acciones" /></th>
	                </tr>
	              </thead>
	              <tbody>
	                <s:iterator value="mensajeLista" >
						<tr>
							<td><s:property value="emisor.nombre" /></td>								
							<td><s:property value="asunto" /></td>
							<td><s:date name="fecha"  format="dd/MM/YYYY"/></td>
							<td><s:property value="estado" /></td>								
							<td>
								<s:url id="verDetalle" action="detalle">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>
								<s:a href="%{verDetalle}" cssClass="btn btn-default btn-xs"><s:text name="%{getText('global.verdetalle')}" /></s:a>
							</td>
						</tr>
					</s:iterator>                          
	              </tbody>      
	            </table>
	        </div>
	    </div>
	</div>
	 <%@ include file="views/footer.jsp" %>
</body>
</html>