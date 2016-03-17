$(document).ready(function(){
	
	var trayecto;
	$("#puntosTrayecto").focusin(function(){
		trayecto=$("#puntosTrayecto").val();
	});
	$("#puntosTrayecto").focusout(function(){
		if(trayecto!=$("#puntosTrayecto").val()){
			$("#spinerTel").css("display","inline-flex");
			trayecto=$("#puntosTrayecto").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarViaje",
				data: { puntosTrayecto : trayecto},
				success:function(response){
					setTimeout(
					  function() 
					  {
						  $("#spinerTray").css("display","none");
						  $("#checkTray").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkTray").css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}
		
		return false;
	});
	
	$("#botonContrasena").click(function(){
		$("#spinerContrasena").css("display","inline-flex");
		$.ajax({
			type : "GET",
			headers:{
				Accept:"application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url : "actualizarContrasena",
			data: $("#formContrasena").serialize(),
			success:function(response){
				$("#claveActualEdicion").val("");
				$("#claveNuevaEdicion").val("");
				$("#repetirClaveEdicion").val("");
				
				if(response!="modificado"){
					setTimeout(function(){
						$("#divRespuesta").html("");
						 $("#spinerContrasena").css("display","none");
						 $("#divRespuesta").html(response);
					}, 2000
							);
					
				}else{				
					setTimeout(
					  function() 
					  {	
						  $("#spinerContrasena").css("display","none");
						  $("#divRespuesta").html("");
						  $("#checkContrasena").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkContrasena").css("display","none");
							  }, 2000);
					  }, 2000);	
				}
			}			
		});
	});
});