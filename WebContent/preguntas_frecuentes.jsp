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
        	<div class="col-md-10 text-justify centrar margentb2">                
                <h3>Preguntas Frequentes</h3>
                <br>
                
				<h4>1.- El servicio es gratuito?</h4>
				<p>S�, el servicio es gratuito y de libre acceso.</p>
				
				<h4>2.- C�mo me registro?</h4>
				<p>Hay dos opciones para registrase en InfoPool: la primera es utilizando el formulario de registraci�n y crear una cuenta. La segunda opci�n es utilizar tu cuenta de Facebook.</p>
				
				<h4>3.- Qui�n puede registrarse?</h4>
				<p>Cualquier persona mayor de 18 a�os.</p>
				
				<h4>4.- C�mo publico un viaje?</h4>
				<p>A trav�s de 3 sencillos pasos, en donde defin�s a donde y cuando viaj�s.</p>
				
				<h4>5.- C�mo encuentro con quien compartir un viaje?</h4>
				<p>A trav�s del buscador, en donde defin�s a donde y cuando viaj�s. El sistema te mostrar� una lista de posibles viajes a compartir.</p>
				
				<h4>6.- Es necesario tener auto?</h4>
				<p>No, simplemente pod�s publicar viajes como pasajero y encontrar un conductor que te lleve hacia donde vas.</p>
				
				<h4>7.- Es obligatorio compartir los gastos?</h4>
				<p>No, aunque el esp�ritu de compartir un viaje es compartir los gastos. InfoPool permite que los usuarios acuerden libremente un monto entre ellos. El monto de la colaboraci�n con los gastos lo fija el conductor cuando publica el viaje. Si sos pasajero, pod�s definir con que monto estar�as dispuesto a colaborar al momento de realizar el viaje.</p>
				
				<h4>8.- Puedo publicar un viaje como pasajero si soy conductor?</h4>
				<p>S�, no hay ning�n inconveniente, ya que pod�s ser conductor en una oportunidad y pasajero en otra oportunidad.</p>
				
				<h4>9.- Cu�ndo finalizan las publicaciones?</h4>
				<p>Las publicaciones no finalizan, aunque una vez cumplida la fecha de viaje definida, �stas no aparecer�n mas en los resultados de las b�squedas.</p>
				
				<h4>10.- C�mo me subo a un viaje publicado?</h4>
				<p>Es sencillo, una vez que encuentres con quien viajar y hagas todas la preguntas que consideres necesarias, tendr�s la opci�n de hacer click en el bot�n "Me subo" en la publicaci�n del viaje. Luego de hacer click en este bot�n, se enviar�n e-mails a ambos usuarios con los datos de contacto de cada uno para que puedan ponerse de acuerdo y realizar el viaje juntos.</p>
				
				<h4>11.- C�mo me llega la informaci�n del conductor/pasajero?</h4>
				<p>Recibir�s un e-mail en la cuenta con la que te registraste. Si llegaras a cambiar tu direcci�n de e-mail no olvides actualizarla en tu perfil tambi�n.</p>
				
				<h4>12.- Puedo rechazar una solicitud de viaje?</h4>
				<p>Por el momento deber�s ponerte en contacto con el otro usuario mediante los datos provistos por e-mail al momento de subirte al viaje. Aunque nos encontramos trabajando para simplificar el mecanismo a la brevedad.</p>
          </div>        
        </div>
    </div><!-- end main -->

    <%@ include file="views/footer.jsp" %>

</body>
</html>