var rendererOptions = {draggable: true};  
var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
var directionsService = new google.maps.DirectionsService();
var map;
var laplata = new google.maps.LatLng(-34.929448, -57.950127); 
var mapOptions = {
  mapTypeId: google.maps.MapTypeId.ROADMAP,
  zoom: 12,
  center: laplata
};
var ne = new google.maps.LatLng(-32.212801,-67.609863);
var sw = new google.maps.LatLng(-37.195331,-69.433594);
var argentina = new google.maps.LatLngBounds(sw, ne);

var data = {};

function inicializarRegistroViaje() {
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
  directionsDisplay.setMap(map);
  directionsDisplay.addListener('directions_changed', function() {
	  //ACA SE DEBERIAN ACTUALIZAR LOS WAYPOINTS Y EL KM
	  actualizarTrayecto(directionsDisplay.getDirections());
  });
  
  var inputO = (document.getElementById('dirOrigen')); 
  var searchBoxO = new google.maps.places.SearchBox(inputO);  
  google.maps.event.addListener(searchBoxO, 'places_changed', function() {
    var places = searchBoxO.getPlaces();
    if (places.length == 0) {
      return;
    }
  });
  var inputD = (document.getElementById('dirDestino')); 
  var searchBoxD = new google.maps.places.SearchBox(inputD);  
  google.maps.event.addListener(searchBoxD, 'places_changed', function() {
    var places = searchBoxD.getPlaces();
    if (places.length == 0) {
      return;
    }
  });
}

function inicializarEdicionViaje(wp){
	  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);	  
	  directionsDisplay.setMap(map);
	  directionsDisplay.addListener('directions_changed', function() {
		  //ACA SE DEBERIAN ACTUALIZAR LOS WAYPOINTS Y EL KM
		  actualizarTrayecto(directionsDisplay.getDirections());		  
	  });
	  directionsDisplay.setPanel(document.getElementById('directionsPanel'));
	  var wpV = JSON.parse(wp)
	  
	  var waypoints = [];
	  for(i in wpV.waypoints){
		var latlng = wpV.waypoints[i];
		waypoints.push({
			location: new google.maps.LatLng(parseFloat(latlng[0]), parseFloat(latlng[1])),
			stopover: false
		});
	  }
	  var start = wpV.start;
	  var end = wpV.end;
	  
	  if ((wpV.start != "") && (wpV.end != "")){	
	    var request = {
	      origin: start,
	      destination: end,
	      waypoints: waypoints,
	      travelMode: google.maps.TravelMode.DRIVING
	    };

	    directionsService.route(request, function(response, status) {
	      if (status == google.maps.DirectionsStatus.OK) {
	        directionsDisplay.setDirections(response);
	      }
	    });
	  }
	  else{
	    alert("El recorrido no se muestra porque los datos son incorrectos");
	  }	
}



/* FUNCIONES DE CALCULO */
function calcularTrayecto() {
  var dirOrigen = document.getElementById('dirOrigen').value;
  var dirDestino = document.getElementById('dirDestino').value;

  if ((dirOrigen != "") && (dirDestino == "")){    
	  dirDestino = dirOrigen;
  }
  if ((dirOrigen == "") && (dirDestino != "")){    
	  dirOrigen = dirDestino;
  }
  
  if ((dirOrigen != "") && (dirDestino != "")){
	  var request = {
		  origin: dirOrigen,
		  destination: dirDestino,    
		  travelMode: google.maps.TravelMode.DRIVING
	  };
	  directionsService.route(request, function(response, status) {
	      if (status == google.maps.DirectionsStatus.OK) {
	        directionsDisplay.setDirections(response);
	      }
	  });
  }
}

function calcPoint() {
  var dirOrigen = document.getElementById('dirOrigen').value;  
  if (dirOrigen != ""){
    var request = {
      origin: dirOrigen,
      destination: dirOrigen,    
      travelMode: google.maps.TravelMode.DRIVING
    };

    directionsService.route(request, function(response, status) {
      if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
      }
    });
  }
  else{
    alert("Debe completar la direccion de origen");
  }
}

function calcularUbicacion(dirOrigen) {
	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById('directionsPanel'));
	if (dirOrigen != ""){
	  var request = {
	    origin: dirOrigen,
	    destination: dirOrigen,    
	    travelMode: google.maps.TravelMode.DRIVING
	  };
	
	  directionsService.route(request, function(response, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(response);
	    }
	  });
	}
	else{
		alert("No se encuentra registrada la ubicación del evento!");
	}
}

function actualizarTrayecto(result) {
  var total = (result.routes[0].legs[0].distance.value)/1000;  
  document.getElementById('kilometros').value = total;  
  guardarPuntosTrayecto();
}

function guardarPuntosTrayecto(){
    var t=[];
    var pt;
    var tleg = directionsDisplay.directions.routes[0].legs[0];
    data.start = {'lat': tleg.start_location.lat(), 'lng':tleg.start_location.lng()}
    data.end = {'lat': tleg.end_location.lat(), 'lng':tleg.end_location.lng()}
    var pt = tleg.via_waypoints;
    for(var i = 0; i < pt.length; i++){
    	t[i] = [pt[i].lat(),pt[i].lng()]
    }
    data.waypoints = t;
    var str = JSON.stringify(data);
    document.getElementById('puntosTrayecto').value = str;
}

/* --------------------------- */
	
function inicializarRegistroEvento() {

	  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
	  directionsDisplay.setMap(map);
	  directionsDisplay.setPanel(document.getElementById('directionsPanel'));

	  var inputO = (document.getElementById('dirOrigen')); 
	  var searchBoxO = new google.maps.places.SearchBox(inputO);  
	  google.maps.event.addListener(searchBoxO, 'places_changed', function() {
	    var places = searchBoxO.getPlaces();
	    if (places.length == 0) {
	      return;
	    }
	  });
}

function inicializarEdicionEvento(){
	inicializarRegistroEvento();
	calcPoint();
}

function inicializarBusquedaViaje() {
	  var inputO = (document.getElementById('dirOrigen')); 
	  var filtro = {componentRestrictions: {country: ['AR']}};
	  var searchBoxO = new google.maps.places.SearchBox(inputO);  
	  google.maps.event.addListener(searchBoxO, 'places_changed', function() {
	    var places = searchBoxO.getPlaces(filtro);
	    if (places.length == 0) {
	      return;
	    }
	  });

	  var inputD = (document.getElementById('dirDestino')); 
	  var searchBoxD = new google.maps.places.SearchBox(inputD);  
	  google.maps.event.addListener(searchBoxD, 'places_changed', function() {
	    var places = searchBoxD.getPlaces();
	    if (places.length == 0) {
	      return;
	    }
	  });
	}

function inicializarRecorridoViaje(wp){	
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  directionsDisplay.setOptions({draggable: false});
  directionsDisplay.setMap(map);
  directionsDisplay.setPanel(document.getElementById('directionsPanel'));
  var wpV = JSON.parse(wp)
  
  var waypoints = [];
  for(i in wpV.waypoints){
	var latlng = wpV.waypoints[i];
	waypoints.push({
		location: new google.maps.LatLng(parseFloat(latlng[0]), parseFloat(latlng[1])),
		stopover: false
	});
  }
  var start = wpV.start;
  var end = wpV.end;
  
  if ((wpV.start != "") && (wpV.end != "")){	
    var request = {
      origin: start,
      destination: end,
      waypoints: waypoints,
      travelMode: google.maps.TravelMode.DRIVING
    };

    directionsService.route(request, function(response, status) {
      if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
      }
    });
  }
  else{
    alert("El recorrido no se muestra porque los datos son incorrectos");
  }
}

