var rendererOptions = {draggable: true};  
var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
var directionsService = new google.maps.DirectionsService();
var geocoder = new google.maps.Geocoder;
var infowindow = new google.maps.InfoWindow;
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
var origen;
var destino;
var data = {};
var marcadores=[];
var marker;

function inicializarRegistroViaje() {

  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);  
  directionsDisplay.setMap(map);
  directionsDisplay.addListener('directions_changed', function() {
	  actualizarTrayecto(directionsDisplay.getDirections(),'');
  });
  var inputO = $("#dirOrigen");
  var inputD = $("#dirDestino");
  map.addListener('click',function(e){
	  if(inputO.attr("value")!="" && inputD.attr("value") !=""){
		  return;
	  }
	  if(inputO.attr("value")==""){
		  clickMapaOrigen(e);
	  }else if(inputD.attr("value")==""){ 
		  var latlng = {lat: parseFloat(e.latLng.lat()), lng: parseFloat( e.latLng.lng())};
		  geocoder.geocode({'location': latlng}, function(results,status){		 
			 if (status === google.maps.GeocoderStatus.OK) {
			      if (results[0]) {
			    	inputD.attr("value",results[0].formatted_address);
			    	destino=results[0].place_id;
			    	calcularTrayecto(origen,destino);
			      } else {
			        //window.alert('No results found');
			      }
			    } else {
			      //window.alert('Geocoder failed due to: ' + status);
			    }
		 });
	  }
	  
  });
	


	function clickMapaOrigen(e){
		var latlng = {lat: parseFloat(e.latLng.lat()), lng: parseFloat( e.latLng.lng())};
		  geocoder.geocode({'location': latlng}, function(results,status){		 
			 if (status === google.maps.GeocoderStatus.OK) {
			      if (results[0]) {
			    	inputO.attr("value",results[0].formatted_address);
			    	origen=results[0].place_id;
			    	marker  = new google.maps.Marker({
			             position: latlng,
			             draggable:true,
			             map: map
			           });			
			    	marker.addListener('dragend',function(e){
			    		clickMapaOrigen(e);
			    	});
			    	marcadores.push(marker);
			        infowindow.setContent(results[0].formatted_address);
			        infowindow.open(map, marker);
			    	calcularTrayecto(origen,destino);
			      } else {
			        //window.alert('No results found');
			      }
			    } else {
			      //window.alert('Geocoder failed due to: ' + status);
			    }
		 });
	}
  function expandViewportToFitPlace(map, place) {
	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(15);
	    }
	  }
   
  var searchBoxO = new google.maps.places.SearchBox(inputO[0]);  
  google.maps.event.addListener(searchBoxO, 'places_changed', function() {
    var places = searchBoxO.getPlaces();
    if (!places[0].geometry) {
        window.alert("Autocomplete's returned place contains no geometry");
        return;
      }
    expandViewportToFitPlace(map, places[0]);
    origen=places[0].place_id;
    calcularTrayecto(origen,destino);
  });
   
  var searchBoxD = new google.maps.places.SearchBox(inputD[0]);  
  google.maps.event.addListener(searchBoxD, 'places_changed', function() {
    var places = searchBoxD.getPlaces();
    if (!places[0].geometry) {
        window.alert("Autocomplete's returned place contains no geometry");
        return;
      }
    expandViewportToFitPlace(map, places[0]);
    destino=places[0].place_id;
    calcularTrayecto(origen,destino);
  });
  map.addListener('bounds_changed', function() {
	  searchBoxO.setBounds(map.getBounds());
	  searchBoxD.setBounds(map.getBounds());
	  });
}

function mostrarRecorridoFrecuencia(frecuencia){
	  var directionsDisplayF = new google.maps.DirectionsRenderer(rendererOptions);
	  var directionsServiceF = new google.maps.DirectionsService();
	  var mapF = new google.maps.Map(document.getElementById('map-canvas'+frecuencia), mapOptions);	  
	  directionsDisplayF.setMap(mapF);	  
	  directionsDisplayF.addListener('directions_changed', function() {
		  //ACA SE DEBERIAN ACTUALIZAR LOS WAYPOINTS Y EL KM
		  actualizarTrayecto(directionsDisplayF.getDirections(),"_"+frecuencia);		  
	  });
	  directionsDisplayF.setPanel(document.getElementById('directionsPanel'));
	  var wp = document.getElementById("puntosTrayecto_"+frecuencia).value;	  
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

	    directionsServiceF.route(request, function(response,status) {
	      if (status == google.maps.DirectionsStatus.OK){
	        directionsDisplayF.setDirections(response);
	      }
	    });
	  }
	  else{
	    alert("El recorrido no se muestra porque los datos son incorrectos");
	  }	
}



/* FUNCIONES DE CALCULO */
function calcularTrayecto(elOrigen,elDestino) {
//  var dirOrigen = document.getElementById('dirOrigen').value;
//  var dirDestino = document.getElementById('dirDestino').value;
	if(elOrigen==null || elDestino==null){
		  return;
	  }
	for (var i = 0; i < marcadores.length; i++) {
		marcadores[i].setMap(null);
	  }
	  directionsService.route({
	      origin: {'placeId': elOrigen},
	      destination: {'placeId': elDestino},
	      travelMode: google.maps.TravelMode.DRIVING
	    }, function(response, status) {
	      if (status === google.maps.DirectionsStatus.OK) {
	        directionsDisplay.setDirections(response);
	      } else {
	        window.alert('Directions request failed due to ' + status);
	      }
	    });
  
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

function actualizarTrayecto(result,contenedor) {
	origen=result.routes[0].legs[0].start_address;
	primeraPalabraOrigen=origen.split(",")[1].split(" ")[1];
	if(primeraPalabraOrigen.length==7 || primeraPalabraOrigen.length==8){
		origen.split(",")[1].split(" ")[1]="";
		origen=origen.replace(primeraPalabraOrigen,"");
	}
	destino=result.routes[0].legs[0].end_address;
	primeraPalabraDestino=destino.split(",")[1].split(" ")[1];
	if(primeraPalabraDestino.length==7 || primeraPalabraDestino.length==8){
		destino.split(",")[1].split(" ")[1]="";
		destino=destino.replace(primeraPalabraDestino,"");
	}
  	$("#dirOrigen").val(origen);
  	$("#dirDestino").val(destino);
  	var total = (result.routes[0].legs[0].distance.value)/1000;  
  	document.getElementById('kilometros'+contenedor).value = total;
  	guardarPuntosTrayecto(result,"puntosTrayecto"+contenedor);  
}

function guardarPuntosTrayecto(result,contenedor){	
	var t=[];
    var pt;
    var tleg = result.routes[0].legs[0];
    data.start = {'lat': tleg.start_location.lat(), 'lng':tleg.start_location.lng()}
    data.end = {'lat': tleg.end_location.lat(), 'lng':tleg.end_location.lng()}
    var pt = tleg.via_waypoints;
    for(var i = 0; i < pt.length; i++){
    	t[i] = [pt[i].lat(),pt[i].lng()]
    }
    data.waypoints = t;
    var str = JSON.stringify(data);
    document.getElementById(contenedor).value = str;
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

