function actualizarNotificacionesModificando(){
	$.ajax({
		type : "GET",
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type": "application/json; charset=utf-8"
		},
		url : "http://localhost:8080/infopool/notificacion/listar.json?",
		success:function(response){				
			$('#bodyNotificaciones').html("");						
			$.each($.makeArray(response.resultado).reverse(),function(k,v){			
				elHtml(v);			
			});
			//siempre poner el valor en 0
			$('#circulo').html(0);
			mostarCirculo();
		}
		
	});
	return false;
};
function actualizarNotificaciones(){
	$.ajax({
		type : "GET",
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type": "application/json; charset=utf-8"
		},
		url : "http://localhost:8080/infopool/notificacion/cantidad.json?",
		success:function(response){				
			$('#bodyNotificaciones').html("");
			var noVistas=parseInt(0);			
//			$.each($.makeArray(response.resultado).reverse(),function(k,v){
//				if(v.estado=='NOVISTO'){
//					noVistas +=parseInt(1);
//				}				
//				//elHtml(v);			
//			});	
			//poner el valor en lo que quedo el contador
			$('#circulo').html(response.mensaje);
			mostarCirculo();
		}
		
	});
	return false;
};
setInterval(actualizarNotificaciones,30000);
function mostarCirculo(){
	if($('#circulo').text()!=0){
		$('#circulo').show("slow").css("display","inline");
	}else{
		$('#circulo').hide();
	}
}
function elHtml(valor){
	var html2="";
	html2='<a href="'+valor.link+'" class="linkNotificacion ">';
			html2+='<div class="contenedor '+valor.estado+'">';
				html2+='<div class="imgNotificacion">';
					html2+='<img id="foto" class="img-thumbnail foto" src="ImageAction?id='+valor.emisorId+'">';
				html2+='</div>';
				html2+='<div class="infoNotificacion">';
					html2+='<div id="mensaje">'+valor.mensaje+'</div>';
					html2+='<div class="datosMenores">';
						html2+='<div class="iconoInfo"><i class="colorIconoInfo fa fa-'+valor.tipo+'"></i></div>';
						html2+='<div class="colorFechaInfo">'+valor.fecha+'</div>';
					html2+='</div>';
				html2+='</div>';			
			html2+='</div>';
			html2+='<hr/>';
			html2+='<div class="clearfix"></div>';
		html2+='</a>';
	$('#bodyNotificaciones').append(html2);  
}
