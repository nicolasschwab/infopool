<%
  request.getSession(true);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
      <%@ include file="views/heads.jsp" %>
</head>
<body>

    <%@ include file="views/header.jsp" %>

    <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="4"/>
    </jsp:include>
<div class="main_bg"><!-- start main -->    
    <div class="container">
        <div class="main row">
            <h3 class="tituloSeccion">Denuncias registradas</h3>            
            <br>
            <table class="table" id="datatable">
              <thead>
                <tr>                  
                  <th>Nombre Denunciante</th>                  
                  <th>Nombre Denunciado</th>
                  <th>Motivo</th>
                  <th>Accion</th>
                </tr>
              </thead>
              <tbody>
                <tr>                  
                  <td>Jose Rodriguez</td>                  
                  <td>Fabiana Gomez</td>
                  <td>Exceso de velocidad</td>
                  <td><a href="ver_detalle_denuncia.php" class="btn btn-default btn-xs">ver detalle</a></td>
                </tr>                                
                <tr>                  
                  <td>Fabiana Gomez</td>                  
                  <td>Rodrigo Perez</td>
                  <td>No cumple con lo ofrecido</td>
                  <td><a href="ver_detalle_denuncia.php" class="btn btn-default btn-xs">ver detalle</a></td>
                </tr>                                
              </tbody>      
            </table>
        </div>
    </div>
</div><!-- end main -->
<%@ include file="views/footer.jsp" %>
</body>
</html>