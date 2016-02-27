<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>    
    <%@  include file="views/heads.jsp" %>
</head>
<body>

    <jsp:include page="views/header.jsp">
    	<jsp:param name="itemActivo" value="1"/>
    </jsp:include>    

    <div class="main_bg">
        <div class="container">
            <div class="row">            
                <img src="resources/images/logoh.png" width="100%">
            </div>
        </div>
        <div class="container">
            <div class="main row">
                <div class="col-md-4 images_1_of_4 text-center">
                    <span class="bg"><i class="fa fa-check"></i></span>
                    <h3>F&aacute;cil</h3>
                    <p class="para">Encontrá el viaje que buscas en pocos pasos.</p>                    
                </div>
                <div class="col-md-4 images_1_of_4 bg1 text-center">
                    <span class="bg"><i class="fa fa-dollar"></i></span>
                    <h3>Barato</h3>
                    <p class="para">Compartí gastos de traslado con otras personas y ahorr&aacute;.</p>                    
                </div>
                <div class="col-md-4 images_1_of_4 bg1 text-center">
                    <span class="bg"><i class="fa fa-lock"></i></span>
                    <h3>Seguro</h3>
                    <p class="para">Conocé con qui&eacute;n viajas antes de subirte al coche.</p>                    
                </div>   
            </div>
        </div>
    </div><!-- end main -->

    <%@ include file= "views/footer.jsp" %>

</body>
</html>