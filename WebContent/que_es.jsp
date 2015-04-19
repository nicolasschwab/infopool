<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>
    <%@ include file="views/heads.jsp" %>
</head>
<body>

    <%@ include file="views/header.jsp" %>
    <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="2"/>
    </jsp:include>

    <div class="main_bg">    
        <div class="container">        
        	<div class="col-md-10 centrar margentb2 text-justify">                
                <h3>&iquest;Qué es <strong>Info Pool</strong>?</h3>
                <br>
                <p><strong>Info Pool</strong> es una plataforma que incentiva el uso de viaje compartido para alumnos, profesores y personal de la Facultad de Informática de la Ciudad de La Plata.</p>
                <p>Esta práctica consiste en compartir un automóvil con otras personas tanto para viajes periódicos como para eventos puntuales, reduciendo la congestión de tránsito en la ciudad y facilitando el desplazamiento a personas que no dispongan de coche propio.</p>                
                
                <h3>Beneficios de <strong>Info Pool</strong>?</h3>
                <br>
                <p>El beneficio más obvio de compartir de compartir coche es el de la reducción de gastos para los viajeros. Un conductor que viaja solo en su auto puede ahorrar hasta un 75% de su gasto llevando tres pasajeros.</p>
                <p>El segundo beneficio está relacionado a la calidad de viaje: viajar en automóvil es más rápido y cómodo que viajar en transporte público. Para el pasajero que, de no compartir auto hubiese conducido su automóvil, viajar como pasajero implica desligarse de la obligación de conducir y evitar la molestia de encontrar lugar para estacionamiento. A su vez implica que en lugar de circular dos coches circula solo uno, con todo el ahorro energético que eso implica.</p>
                <p>Finalmente, la reducción del tránsito tiene beneficios múltiples para el conjunto de la sociedad: se reducen las emisiones de CO2; se reduce la contaminación atmosférica, visual y auditiva; y se reducen las demoras o embotellamientos para todos los automovilistas.</p>
                
                FOTOSSSSSSSS                
          </div>        
        </div>
    </div>
    
    <%@ include file="views/footer.jsp" %>
</body>
</html>