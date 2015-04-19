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
                <h3>&iquest;Qu� es <strong>Info Pool</strong>?</h3>
                <br>
                <p><strong>Info Pool</strong> es una plataforma que incentiva el uso de viaje compartido para alumnos, profesores y personal de la Facultad de Inform�tica de la Ciudad de La Plata.</p>
                <p>Esta pr�ctica consiste en compartir un autom�vil con otras personas tanto para viajes peri�dicos como para eventos puntuales, reduciendo la congesti�n de tr�nsito en la ciudad y facilitando el desplazamiento a personas que no dispongan de coche propio.</p>                
                
                <h3>Beneficios de <strong>Info Pool</strong>?</h3>
                <br>
                <p>El beneficio m�s obvio de compartir de compartir coche es el de la reducci�n de gastos para los viajeros. Un conductor que viaja solo en su auto puede ahorrar hasta un 75% de su gasto llevando tres pasajeros.</p>
                <p>El segundo beneficio est� relacionado a la calidad de viaje: viajar en autom�vil es m�s r�pido y c�modo que viajar en transporte p�blico. Para el pasajero que, de no compartir auto hubiese conducido su autom�vil, viajar como pasajero implica desligarse de la obligaci�n de conducir y evitar la molestia de encontrar lugar para estacionamiento. A su vez implica que en lugar de circular dos coches circula solo uno, con todo el ahorro energ�tico que eso implica.</p>
                <p>Finalmente, la reducci�n del tr�nsito tiene beneficios m�ltiples para el conjunto de la sociedad: se reducen las emisiones de CO2; se reduce la contaminaci�n atmosf�rica, visual y auditiva; y se reducen las demoras o embotellamientos para todos los automovilistas.</p>
                
                FOTOSSSSSSSS                
          </div>        
        </div>
    </div>
    
    <%@ include file="views/footer.jsp" %>
</body>
</html>