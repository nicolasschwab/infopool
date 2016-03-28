$(document).ready(function(){	
		
	var descripcion;
	$("#descripcion").focusin(function(){
		descripcion=$("#descripcion").val();
	});
	$("#descripcion").focusout(function(){
		if(descripcion!=$("#descripcion").val()){
			$("#spinerDesc").css("display","inline-flex");
			descripcion=$("#descripcion").val();
			viaje=$("#id").val();
			parameters='descripcion='+descripcion+"&id="+viaje;
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "EditarViaje",
				data: parameters,
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerDesc").css("display","none");
						  $("#checkDesc").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkDesc").css("display","none");
							  }, 2000);
					  }, 2000);				
				}			
			});
		}		
		return false;
	});
	
	var fechaInicio;
	$("#fechaInicio").focusin(function(){
		fechaInicio=$("#fechaInicio").val();
	});
	$("#fechaInicio").focusout(function(){
		if(fechaInicio!=$("#fechaInicio").val()){
			$("#spinerFini").css("display","inline-flex");
			fechaInicio=$("#fechaInicio").val();
			viaje=$("#id").val();
			parameters='fechaInicio='+fechaInicio+"&id="+viaje;
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "EditarViaje",
				data: parameters,
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerFini").css("display","none");
						  $("#checkFini").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkFini").css("display","none");
							  }, 2000);
					  }, 2000);				
				}			
			});
		}		
		return false;
	});
	
	var fechaFin;
	$("#fechaFin").focusin(function(){
		fechaFin=$("#fechaFin").val();
	});
	$("#fechaFin").focusout(function(){
		if(fechaFin!=$("#fechaFin").val()){
			$("#spinerFfin").css("display","inline-flex");
			fechaFin=$("#fechaFin").val();
			viaje=$("#id").val();
			parameters='fechaFin='+fechaFin+"&id="+viaje;
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "EditarViaje",
				data: parameters,
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerFfin").css("display","none");
						  $("#checkFfin").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkFfin").css("display","none");
							  }, 2000);
					  }, 2000);				
				}			
			});
		}		
		return false;
	});
		
	$('.botonRecorrido').click(function(){		
		var idFrecuencia = (this.id).split('_')[1];
		var kilometros = $("#kilometros_"+idFrecuencia).val();
		if(kilometros>0){
			$("#spinerTray"+idFrecuencia).css("display","inline-flex");			
			trayecto=$("#puntosTrayecto_"+idFrecuencia).val();
			kmTrayecto=$("#kilometros_"+idFrecuencia).val();			
			parameters='puntosTrayecto='+trayecto+'&kilometros='+kmTrayecto+"&idFrecuencia="+idFrecuencia;
			alert(parameters);
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "EditarFrecuencia",
				data: parameters,
				success:function(response){
					setTimeout(
					  function() 
					  {
						  $("#spinerTray"+idFrecuencia).css("display","none");
						  $("#checkTray"+idFrecuencia).css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkTray"+idFrecuencia).css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}
		
		return false;
	});	
	
	$(".botonFrecuencia").click(function(){
		var idFrecuencia = (this.id).split('_')[1];		
		$("#botonFrecuencia_"+idFrecuencia).css("display","none");
		$("#spinerFrecuencia"+idFrecuencia).css("display","inline-flex");		
		horaPartida=$("#horaPartida"+idFrecuencia).val();
		horaRegreso=$("#horaRegreso"+idFrecuencia).val();
		asientosDisponibles=$("#asientosDisponibles"+idFrecuencia).val();
		frecuenciaViaje=idFrecuencia;
		parameters='asientosDisponibles='+asientosDisponibles+'&horaPartida='+horaPartida+'&horaRegreso='+horaRegreso+"&idFrecuencia="+frecuenciaViaje;
		$.ajax({
			type : "GET",
			headers:{
				Accept:"application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url : "EditarFrecuencia",
			data: parameters,
			success:function(response){				
					setTimeout(
							function()
							{								 
								 $("#spinerFrecuencia"+idFrecuencia).css("display","none");
								 $("#checkFrecuencia"+idFrecuencia).css("display","inline-flex");
								 setTimeout(
								 function() 
								 {
									 $("#checkFrecuencia"+idFrecuencia).css("display","none");
									 $("#botonFrecuencia_"+idFrecuencia).css("display","inline-flex");
								 }, 2000);								 									
							}, 2000);
			}			
		});
	});
});