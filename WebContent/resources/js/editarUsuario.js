
$(document).ready(function(){
	
	$("#editarAuto").submit(function(event){
		marca=$("#vistaMarca").val();
		modelo=$("#modelos").val();
		$.ajax({
			type : "GET",
			headers:{
				Accept:"application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url : "editarAuto",
			data: { marcaSeleccionada :marca, modeloSeleccionado: modelo},
			success:function(response){
				
			}
				
		});
		event.preventDefault();
	});
	
	
	$("#vistaMarca").change(function(){
		valor=$("#vistaMarca").val();
		$("#modelos option").each(function(){
			$(this).remove();
		});			
		
		$.ajax({
			type : "GET",
			headers:{
				Accept:"application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url : "traerModelosDeMarca",
			data: { marcaSeleccionada :valor},
			success:function(response){
				$.each($.makeArray(response),function(k,objeto){
					$("#modelos").append(
							"<option value='"+objeto.id+"'>"+objeto.nombre +"</option>"
							);
				});
			}
				
		});	
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
	})
		
		
	
	
	var nombreUsuario;
	$("#usuarioEdicion").focusin(function(){
		nombreUsuario=$("#usuarioEdicion").val();
	});
	$("#usuarioEdicion").focusout(function(){
		if(nombreUsuario!=$("#usuarioEdicion").val()){
			$("#spinerUs").css("display","inline-flex");
			nombreUsuario=$("#usuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { usuarioEdicion : nombreUsuario},
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerUs").css("display","none");
						  $("#checkUs").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkUs").css("display","none");
							  }, 2000);
					  }, 2000);				
				}			
			});
		}		
		return false;
	});
	
	var nombre;
	$("#nombreUsuarioEdicion").focusin(function(){
		nombre=$("#nombreUsuarioEdicion").val();
	});
	$("#nombreUsuarioEdicion").focusout(function(){
		if(nombre!=$("#nombreUsuarioEdicion").val()){
			$("#spinerNom").css("display","inline-flex");
			nombre=$("#nombreUsuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { nombreUsuarioEdicion : nombre},
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerNom").css("display","none");
						  $("#checkNom").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkNom").css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}
		
		return false;
	});
	
	var apellido;
	$("#apellidoUsuarioEdicion").focusin(function(){
		apellido=$("#apellidoUsuarioEdicion").val();
	});
	$("#apellidoUsuarioEdicion").focusout(function(){
		if(apellido!=$("#apellidoUsuarioEdicion").val()){
			$("#spinerApe").css("display","inline-flex");
			apellido=$("#apellidoUsuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { apellidoUsuarioEdicion : apellido},
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerApe").css("display","none");
						  $("#checkApe").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkApe").css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}
		
		return false;
	});
	
	var mail;
	$("#mailUsuarioEdicion").focusin(function(){
		mail=$("#mailUsuarioEdicion").val();
	});
	$("#mailUsuarioEdicion").focusout(function(){
		if(mail!=$("#mailUsuarioEdicion").val()){
			$("#spinerMail").css("display","inline-flex");
			mail=$("#mailUsuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { mailUsuarioEdicion : mail},
				success:function(response){	
					setTimeout(
					  function() 
					  {
						  $("#spinerMail").css("display","none");
						  $("#checkMail").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkMail").css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}		
		return false;
	});
	
	var telefono;
	$("#telefonoUsuarioEdicion").focusin(function(){
		telefono=$("#telefonoUsuarioEdicion").val();
	});
	$("#telefonoUsuarioEdicion").focusout(function(){
		if(telefono!=$("#telefonoUsuarioEdicion").val()){
			$("#spinerTel").css("display","inline-flex");
			telefono=$("#telefonoUsuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { telefonoUsuarioEdicion : telefono},
				success:function(response){
					setTimeout(
					  function() 
					  {
						  $("#spinerTel").css("display","none");
						  $("#checkTel").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkTel").css("display","none");
							  }, 2000);
					  }, 2000);					
				}			
			});
		}
		
		return false;
	});
	
	var fechaNac;
	$("#fNacimientoUsuarioEdicion").focusin(function(){
		fechaNac=$("#fNacimientoUsuarioEdicion").val();
	});
	$("#fNacimientoUsuarioEdicion").focusout(function(){
		if(fechaNac!=$("#fNacimientoUsuarioEdicion").val()){
			$("#spinerFNac").css("display","inline-flex");
			fechaNac=$("#fNacimientoUsuarioEdicion").val();
			$.ajax({
				type : "GET",
				headers:{
					Accept:"application/json; charset=utf-8",
					"Content-Type": "application/json; charset=utf-8"
				},
				url : "actualizarUsuario",
				data: { fNacimientoUsuarioEdicion : fechaNac},
				success:function(response){
					setTimeout(
					  function() 
					  {
						  $("#spinerFNac").css("display","none");
						  $("#checkFNac").css("display","inline-flex");
							setTimeout(
							  function() 
							  {
								  $("#checkFNac").css("display","none");
							  }, 2000);
					  }, 2000);
					
				}			
			});
		}		
		return false;
	});

	
});
	
function anclaInfoTi(){
	document.location.href = "#editarInfoTi";
	$("#infoTi").css("background","#f1f1f1");
	$("#contrasena").css("background","none");
	$("#infoPer").css("background","none");
	$("#infoAuto").css("background","none");
}
function anclaContrasena(){
	document.location.href = "#editarContrasenia";
	$("#infoTi").css("background","none");
	$("#contrasena").css("background","#f1f1f1");
	$("#infoPer").css("background","none");
	$("#infoAuto").css("background","none");
}
function anclaInfoPersonal(){
	document.location.href = "#editarInfoPersonal";
	$("#infoTi").css("background","none");
	$("#contrasena").css("background","none");
	$("#infoPer").css("background","#f1f1f1");
	$("#infoAuto").css("background","none");
}
function anclaInfoAuto(){
	document.location.href = "#editarInfoAuto";
	$("#infoTi").css("background","none");
	$("#contrasena").css("background","none");
	$("#infoPer").css("background","none");
	$("#infoAuto").css("background","#f1f1f1");
}

$(function() {
	$("#btn_enviar").click(function() {
		parameters='nombreUsuario='+document.getElementById('nombre').value+'&apellidoUsuario='+document.getElementById('apellido').value+'&mailUsuario='+document.getElementById('mail').value+'&telefonoUsuario='+document.getElementById('telefono').value+'&fechaUsuario='+document.getElementById('fechaNac').value
		var url = "editarUsuario"; // El script a dónde se realizará la petición.
		$.ajax({
			type : "POST",
			url : url,
			data : parameters, // Adjuntar los campos del formulario enviado.
		});
		return false; // Evitar ejecutar el submit del formulario.
	});
});
