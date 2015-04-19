<%
  request.getSession(true);
%>
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
<div class="main_bg"><!-- start main -->    
    <div class="container">
        <div class="main row">            
            <h3 class="tituloSeccion">Mis Mensajes</h3>
            <br>
            <br>
            <table class="table" id="datatable">
              <thead>
                <tr>                  
                  <th>Emisor</th>                  
                  <th>Asunto</th>
                  <th>Fecha</th>
                  <th>Estado Mensaje</th>
                  <th>Accion</th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="mensajeLista" >
							<tr>
								<td><s:property value="emisor.nombre" /></td>								
								<td><s:property value="asunto" /></td>
								<td><s:date name="fecha"  format="dd/MM/YYYY"/></td>
								<td><s:property value="estado" /></td>								
								<td><s:url id="verDetalle" action="detalle">
									<s:param name="id" value="%{id}"></s:param>
								</s:url>
								<s:a href="%{verDetalle}" cssClass="btn btn-default btn-xs">ver detalle</s:a> </td>
							</tr>
						</s:iterator>                          
              </tbody>      
            </table>
        </div>
    </div>
</div><!-- end main -->
 <%@ include file="views/footer.jsp" %>
</body>
</html>