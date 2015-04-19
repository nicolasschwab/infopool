var rendererOptions = {draggable: true};  
var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
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


function inicializarRegistroViaje() {

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

  var inputD = (document.getElementById('dirDestino')); 
  var searchBoxD = new google.maps.places.SearchBox(inputD);  
  google.maps.event.addListener(searchBoxD, 'places_changed', function() {
    var places = searchBoxD.getPlaces();
    if (places.length == 0) {
      return;
    }
  });
}

function inicializarEdicionViaje(){
	inicializarRegistroViaje();
	calcRoute();
}


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

	 // map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
	  //directionsDisplay.setMap(map);
	  //directionsDisplay.setPanel(document.getElementById('directionsPanel'));

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

//google.maps.event.addDomListener(window, 'load', initialize);

function inicializarRecorridoViaje(dirO, dirD){	
	  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
	  directionsDisplay.setMap(map);
	  directionsDisplay.setPanel(document.getElementById('directionsPanel'));
	  if ((dirO != "") && (dirD != "")){
	    var request = {
	      origin: dirO,
	      destination: dirD,    
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

function calcRoute() {
  var dirOrigen = document.getElementById('dirOrigen').value;
  var dirDestino = document.getElementById('dirDestino').value;

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
  else{
    alert("Debe completar la direccion de origen y de destino");
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
	  
	  map.setZoom(12);
	}
	else{
		alert("No se encuentra registrada la ubicación del evento!");
	}
}