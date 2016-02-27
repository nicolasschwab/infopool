function cambiarTipoViaje() {
	var tipoViajeElegido = document.getElementById("tipoViaje").value;
	if (tipoViajeElegido == "PERIODICO") {
		document.getElementById("vperPanel").style.display = "inline";
		document.getElementById("vperFecha").style.display = "inline";
	} else {
		document.getElementById("vperFecha").style.display = "none";
		document.getElementById("vperPanel").style.display = "none";
	
	}
};

function cambiarTramoViaje(){
	var tramoViajeElegido = document.getElementById("tramoViaje").value;
	if (tramoViajeElegido == "IDAVUELTA") {
		document.getElementById("tcompHora").style.display = "inline";		
	}
	else{
		document.getElementById("tcompHora").style.display = "none";
	}
}

function mostrarNotificaciones() {
	if ($('#notificaciones').is(':visible')) {
		$('#notificaciones').hide();

	} else {
		$('#notificaciones').show();
		actualizarNotificacionesModificando();
	}

};