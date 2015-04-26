function cambiarTipoViaje(){
	var tipoViajeElegido = document.getElementById("tipoViaje").value;
	if (tipoViajeElegido == "vd"){		
		document.getElementById("vdPanel").style.display = "inline";
		document.getElementById("vpPanel").style.display = "none";
	}
	else{	
		
		document.getElementById("vpPanel").style.display = "inline";
		document.getElementById("vdPanel").style.display = "none";
		
	}
}