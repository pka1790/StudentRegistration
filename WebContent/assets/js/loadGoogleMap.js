var blurBox;
$(document).ready(function(){
	var geolocation = window.navigator.geolocation
	var options = {
			enableHighAccuracy:true,
			maximumAge:0,
			timeout:20000
	};
	geolocation.getCurrentPosition(onComplete, onFail, options);

	$("#pac-input-start, #pac-input-end").blur(function(e){
		blurBox = e.target;
	});
});

function onComplete(positionObj){
	initializeMap(positionObj.coords.latitude, positionObj.coords.longitude);
}
function onFail(errorObj){
	$("#message").html("Failed to get position details : " + errorObj.message);
}

function initializeMap(latitude, longitude){
	var location = new google.maps.LatLng(latitude, longitude); 
	var mapProps = {
			zoom:12, 
			center: location, 
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	var directionsDisplay = new google.maps.DirectionsRenderer();
	var directionsService = new google.maps.DirectionsService();
	
	var map = new google.maps.Map(document.getElementById("googleMap"), mapProps);
	directionsDisplay.setMap(map);
	google.maps.event.addListener(map, 'click', function(e){
		chooseLocation(e.latLng);
	});
	
	//---start point----------------------------------------------------------------------------------------------------
	//get location input box
	var startAutocomplete = new google.maps.places.Autocomplete(document.getElementById("pac-input-start"));
	startAutocomplete.bindTo('bounds',map);
	
	var startInfowindow = new google.maps.InfoWindow();
	var startMarker = new google.maps.Marker({
		map: map,
		anchorPoint: new google.maps.Point(0,-29)
	});
	
	var startPlace;
	google.maps.event.addListener(startAutocomplete, 'place_changed', function(){
		startPlace = startAutocomplete.getPlace();
		showStartPlace()
	});
	
	function showStartPlace(){
		startInfowindow.close();
		startMarker.setVisible(false);
		
		//if place return do not have geometry
		if(!startPlace.geometry){
			window.alert('Autocomplete returned places contains no geometry.');
			return;
		}
		
		//if returned places with geometry
		if(startPlace.geometry.viewport){
			map.fitBounds(startPlace.geometry.viewport);
		}else{
			map.setCenter(startPlace.geometry.location);
			map.setZoom(12);
		}
		
		startMarker.setIcon(({
			url: 'http://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png', //startPlace.icon,
			size: new google.maps.Size(71,71),
			origin: new google.maps.Point(0,0),
			anchor: new google.maps.Point(17,34),
			scaledSize: new google.maps.Size(35,35)
		}));
		startMarker.setPosition(startPlace.geometry.location);
		startMarker.setVisible(true);
		
		var address='';
		if(startPlace.address_component){
			address = [
			           (startPlace.address_component[0] && startPlace.address_component[0].short_name || ''),
			           (startPlace.address_component[1] && startPlace.address_component[1].short_name || ''),
			           (startPlace.address_component[2] && startPlace.address_component[2].short_name || '')
			           ].join();
		}
		startInfowindow.setContent('<div><string>' + startPlace.name + '</strong><br/>' + address + '</div>');
		startInfowindow.open(map,startMarker);
		
		drawRoute();
	}
	
	//---end point----------------------------------------------------------------------------------------------------
	//get location input box
	var endAutocomplete = new google.maps.places.Autocomplete(document.getElementById("pac-input-end"));
	endAutocomplete.bindTo('bounds',map);
	
	var endInfowindow = new google.maps.InfoWindow();
	var endMarker = new google.maps.Marker({
		map: map,
		anchorPoint: new google.maps.Point(0,-29)
	});
	
	var endPlace;
	google.maps.event.addListener(endAutocomplete, 'place_changed', function(){
		endPlace = endAutocomplete.getPlace();
		showEndPlace();
	});
	
	function showEndPlace(){
		endInfowindow.close();
		endMarker.setVisible(false);
		
		//if place return do not have geometry
		if(!endPlace.geometry){
			window.alert('Autocomplete returned places contains no geometry.');
			return;
		}
		
		//if returned places with geometry
		if(endPlace.geometry.viewport){
			map.fitBounds(endPlace.geometry.viewport);
		}else{
			map.setCenter(endPlace.geometry.location);
			map.setZoom(12);
		}
		
		endMarker.setIcon(({
			url: 'http://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png', //endPlace.icon,
			size: new google.maps.Size(71,71),
			origin: new google.maps.Point(0,0),
			anchor: new google.maps.Point(17,34),
			scaledSize: new google.maps.Size(35,35)
		}));
		endMarker.setPosition(endPlace.geometry.location);
		endMarker.setVisible(true);
		
		var address='';
		if(endPlace.address_component){
			address = [
			           (endPlace.address_component[0] && endPlace.address_component[0].short_name || ''),
			           (endPlace.address_component[1] && endPlace.address_component[1].short_name || ''),
			           (endPlace.address_component[2] && endPlace.address_component[2].short_name || '')
			           ].join();
		}
		endInfowindow.setContent('<div><string>' + endPlace.name + '</strong><br/>' + address + '</div>');
		endInfowindow.open(map,endMarker);
		
		drawRoute();
	}
	
	function drawRoute(){
		if(startPlace && endPlace){
			var bounds = new google.maps.LatLngBounds();
			bounds.extend(startPlace.geometry.location);
			bounds.extend(endPlace.geometry.location);
			map.fitBounds(bounds);

			var routeRequest = {
					origin: startPlace.geometry.location,
					destination: endPlace.geometry.location,
					travelMode: google.maps.TravelMode.DRIVING
			}
			directionsService.route(routeRequest, function(response, status){
				if(status == google.maps.DirectionsStatus.OK){
					directionsDisplay.setDirections(response);
					directionsDisplay.setMap(map);
					var kms = response.routes[0].legs[0].distance.text;
					kms = kms.replace(" km","");
					$("#distance").val(kms);
				}else{
					alert('Direction request from ' + startPlace.geometry.location.toUrlValue(6) + ' to ' + endPlace.geometry.location.toUrlValue(6) + ' failed: ' + status);
				}
			});
		}
	}
	
	function chooseLocation(selectedLocation){
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			latLng:selectedLocation
		},function(results, status){
			if(status == google.maps.GeocoderStatus.OK){
				if(results[0]){
					//var clickedPlace = results[0];
					var address = results[0].formatted_address;
					var placeId = results[0].place_id;
					
					var placeRequest = {
							placeId: placeId
					};
					var service = new google.maps.places.PlacesService(map);
					service.getDetails(placeRequest, function(place, placeStatus){
						if(placeStatus == google.maps.places.PlacesServiceStatus.OK){
							$('#'+blurBox.id).val(address);
							if(blurBox.id=='pac-input-start'){
								startPlace = place;
								showStartPlace();
							}else if(blurBox.id=='pac-input-end'){
								endPlace = place;
								showEndPlace();
							}
						}
					});
				}
			}
		});
	}
}