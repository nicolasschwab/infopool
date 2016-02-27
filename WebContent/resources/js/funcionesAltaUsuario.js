function mostrarDatosPersonales() {
	document.getElementById("datosPersonales").style.display = "inherit";
	document.getElementById("datosUsuario").style.display = "none";
	//document.getElementById("datosAuto").style.display = "none";

};

function mostrarDatosUsuario() {
	// Se validan los datos personales
	if (validarDatosPersonales()) {
		document.getElementById("datosPersonales").style.display = "none";
		document.getElementById("datosUsuario").style.display = "inherit";
		//document.getElementById("datosAuto").style.display = "none";
		return true;
	}
	return false
	
};

function mostrarDatosAuto() {
	//Se validan los datos del usuario
	
	if (validarDatosUsuario()) {
		if(document.getElementById("poseeAuto").value==='SI'){		
			document.getElementById("datosPersonales").style.display = "none";
			document.getElementById("datosUsuario").style.display = "none";
			document.getElementById("datosAuto").style.display = "inherit";
		}else{
			document.getElementById("registroForm").submit();
		}
		return true;
	}
	return false;
};

function validarVacio(elem, id) {
	document.getElementById(id).innerHTML = "";
	if (elem == null || elem.length == 0 || /^\s+$/.test(elem)) {
		document.getElementById(id).innerHTML = "Debe completar este campo";
		return true;
	}
	return false;
}

function validarSolo10Numeros(elem, id) {
	if (!/^[0-9]{0,10}$/.test(elem)) {
		document.getElementById(id).innerHTML = "Este campo solo puede contener numeros de no mas de 10 digitos";
		return true;
	}
	return false;
}

function validarMail(elem, id) {
	if (!/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$/
			.test(elem)) {
		document.getElementById(id).innerHTML = "Este campo debe ser un email";
		return true;
	}
	return false;
}

function validarClavesIguales(clave1,clave2,id){
	if (clave1!==clave2) {
		document.getElementById(id).innerHTML = "Las contraseñas no coinciden";
		return true;
	}
	return false;
}

function validarDatosPersonales() {
	avanzar = true;
	nombreUsuario = document.getElementsByName("nombreUsuario")[0];
	idNombreUsuario = "error" + nombreUsuario.getAttribute("name");
	apellidoUsuario = document.getElementsByName("apellidoUsuario")[0];
	idApellidoUsuario = "error" + apellidoUsuario.getAttribute("name");
	fNacimientoUsuario = document.getElementsByName("fNacimientoUsuario")[0];
	idFNacimientoUsuario = "error" + fNacimientoUsuario.getAttribute("name");
	telefonoUsuario = document.getElementsByName("telefonoUsuario")[0];
	idTelefonoUsuario = "error" + telefonoUsuario.getAttribute("name");
	mailUsuario = document.getElementsByName("mailUsuario")[0];
	idMailUsuario = "error" + mailUsuario.getAttribute("name");
	if (validarVacio(nombreUsuario.value, idNombreUsuario)) {		
		avanzar = false;
	}
	if (validarVacio(apellidoUsuario.value, idApellidoUsuario)) {		
		avanzar = false;
	}
	if (validarVacio(fNacimientoUsuario.value, idFNacimientoUsuario)) {
		avanzar = false;
	}
	if (validarVacio(telefonoUsuario.value, idTelefonoUsuario)
			|| validarSolo10Numeros(telefonoUsuario.value, idTelefonoUsuario)) {		
		avanzar = false;
	}
	if (validarVacio(mailUsuario.value, idMailUsuario)
			|| validarMail(mailUsuario.value, idMailUsuario)) {		
		avanzar = false;
	}
	return avanzar;
}

function validarDatosUsuario() {
	avanzar = true;
	usuario = document.getElementsByName("usuario")[0];
	idUsuario = "error" + usuario.getAttribute("name");
	clave = document.getElementsByName("clave")[0];
	idClave = "error" + clave.getAttribute("name");
	rClave = document.getElementsByName("rClave")[0];
	idrClave = "error" + rClave.getAttribute("name");
	fperfilUsuario = document.getElementsByName("fperfilUsuario")[0];
	idfperfilUsuario = "error" + fperfilUsuario.getAttribute("name");
	if (validarVacio(usuario.value, idUsuario)) {
		avanzar = false;
	}
	if (validarVacio(clave.value, idClave) || validarClavesIguales(clave.value,rClave.value,idClave)) {
		avanzar = false;
	}
	if (validarVacio(rClave.value, idrClave)) {
		avanzar = false;
	}
	if (validarVacio(fperfilUsuario.value, idfperfilUsuario)) {
		avanzar = false;
	}
	if (avanzar){
		document.getElementById("registroForm").submit();
	}
	return avanzar;
}

function validarDatosAuto() {
	avanzar = true;
	vistaMarca = document.getElementsByName("vistaMarca")[0];
	idMarca = "error" + vistaMarca.getAttribute("name");
	modelo = document.getElementsByName("modelo")[0];
	idModelo = "error" + modelo.getAttribute("name");
	vistaCombustible = document.getElementsByName("vistaCombustible")[0];
	idCombustible = "error" + vistaCombustible.getAttribute("name");
	vistaTipo = document.getElementsByName("vistaTipo")[0];
	idTipo = "error" + vistaTipo.getAttribute("name");
	if (validarVacio(vistaMarca.value, idMarca)) {
		avanzar = false;
	}
	if (validarVacio(modelo.value, idModelo)) {
		avanzar = false;
	}
	if (validarVacio(vistaCombustible.value, idCombustible)) {
		avanzar = false;
	}
	if (validarVacio(vistaTipo.value, idTipo)) {
		avanzar = false;
	}
	return avanzar;
}

function validarForm(){
	enviar=false;
	if($('#datosPersonales').is(':visible')){		
		mostrarDatosUsuario(); // valida datos Personales
	}
	else{
		if($('#datosUsuario').is(':visible')){					
			enviar=validarDatosUsuario();
		}		
	};
	return enviar;
}