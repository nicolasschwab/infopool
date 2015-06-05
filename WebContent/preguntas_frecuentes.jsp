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
				<p>Sí, el servicio es gratuito y de libre acceso.</p>
				
				<h4>2.- Cómo me registro?</h4>
				<p>Hay dos opciones para registrase en InfoPool: la primera es utilizando el formulario de registración y crear una cuenta. La segunda opción es utilizar tu cuenta de Facebook.</p>
				
				<h4>3.- Quién puede registrarse?</h4>
				<p>Cualquier persona mayor de 18 años.</p>
				
				<h4>4.- Cómo publico un viaje?</h4>
				<p>A través de 3 sencillos pasos, en donde definís a donde y cuando viajás.</p>
				
				<h4>5.- Cómo encuentro con quien compartir un viaje?</h4>
				<p>A través del buscador, en donde definís a donde y cuando viajás. El sistema te mostrará una lista de posibles viajes a compartir.</p>
				
				<h4>6.- Es necesario tener auto?</h4>
				<p>No, simplemente podés publicar viajes como pasajero y encontrar un conductor que te lleve hacia donde vas.</p>
				
				<h4>7.- Es obligatorio compartir los gastos?</h4>
				<p>No, aunque el espíritu de compartir un viaje es compartir los gastos. InfoPool permite que los usuarios acuerden libremente un monto entre ellos. El monto de la colaboración con los gastos lo fija el conductor cuando publica el viaje. Si sos pasajero, podés definir con que monto estarías dispuesto a colaborar al momento de realizar el viaje.</p>
				
				<h4>8.- Puedo publicar un viaje como pasajero si soy conductor?</h4>
				<p>Sí, no hay ningún inconveniente, ya que podés ser conductor en una oportunidad y pasajero en otra oportunidad.</p>
				
				<h4>9.- Cuándo finalizan las publicaciones?</h4>
				<p>Las publicaciones no finalizan, aunque una vez cumplida la fecha de viaje definida, éstas no aparecerán mas en los resultados de las búsquedas.</p>
				
				<h4>10.- Cómo me subo a un viaje publicado?</h4>
				<p>Es sencillo, una vez que encuentres con quien viajar y hagas todas la preguntas que consideres necesarias, tendrás la opción de hacer click en el botón "Me subo" en la publicación del viaje. Luego de hacer click en este botón, se enviarán e-mails a ambos usuarios con los datos de contacto de cada uno para que puedan ponerse de acuerdo y realizar el viaje juntos.</p>
				
				<h4>11.- Cómo me llega la información del conductor/pasajero?</h4>
				<p>Recibirás un e-mail en la cuenta con la que te registraste. Si llegaras a cambiar tu dirección de e-mail no olvides actualizarla en tu perfil también.</p>
				
				<h4>12.- Puedo rechazar una solicitud de viaje?</h4>
				<p>Por el momento deberás ponerte en contacto con el otro usuario mediante los datos provistos por e-mail al momento de subirte al viaje. Aunque nos encontramos trabajando para simplificar el mecanismo a la brevedad.</p>
          </div>        
        </div>
    </div><!-- end main -->

    <%@ include file="views/footer.jsp" %>

</body>
</html>