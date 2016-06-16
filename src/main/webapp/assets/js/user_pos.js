	
		
	var source_vect_pos = new ol.source.Vector({
			
	});
	
	var pos_user_layer = new ol.layer.Vector({
		source : source_vect_pos,
		style : new ol.style.Style({
			image : new ol.style.Circle({
				radius : 10,
				stroke : new ol.style.Stroke({
					color : '#fff'
				}),
				fill : new ol.style.Fill({
					color : 'yellow'
				})
			})
		}),
		name : 'posizione_utenti'
	});
	
	map.addLayer(pos_user_layer);
	
	var s = 1000;
	var min = 60 * s;
	var hour = 3600 * s;
	function isArray(check_element) {
		return Object.prototype.toString.call(check_element) === '[object Array]';
	}

	
	$('document').ready(
			function() {
				setInterval(function() {
	
					$.ajax({
						url : './PositionList',
						type : 'GET',
						dataType : 'json',
						success : function(response) {
							len = response.length;
	
							console.log("aggiorno posizione utente");
							if (isArray(response)) {
								console.log("Un intero Array");
								for (var i=0; i<4;i++)
								console.log(response[i]);
								var pos_user_features=new Array();
								var coordinates= new Array();
								
								
								source_vect_pos.clear();
							
								$.each(response, function(idx, oggetto) {
									coordinates[0] = oggetto.lon;
									coordinates[1]=oggetto.lat ;	
									console.log("email: "+oggetto.user_email);
									console.log("lon: "+coordinates[0]);
									console.log("lat: "+coordinates[1]);
									pos_user_features[idx] = new ol.Feature({
											
											geometry: new ol.geom.Point(coordinates),
									    'user': oggetto.user_email  
								});
										
									
								});
								 
								
								
								source_vect_pos.addFeatures(pos_user_features);	
								
								
								
							}else{
								console.log("Solo un oggetto");
								var coordinates = [ response.lon, response.lat ];
								var valueGeoPosSte = new ol.Feature({
										geometry: new ol.geom.Point(coordinates),
										'user': response.user_email
								
								
								});
								source_vect_pos.clear();
								source_vect_pos.addFeature(valueGeoPosSte);
								
								
							}
	
						}
					});
	
				}, 10* s);
				
			
				
				
				
			});
	
	
	var element = document.getElementById('popup');
	
	var popup = new ol.Overlay({
		element : document.getElementById('popup'),
		 
		
		positioning : 'bottom-center',
		stopEvent : false
		
	});
	
	map.addOverlay(popup);
	
	
	map.on('singleclick', function(event) {
		text=event.coordinate + "\nStefano";
		var feature = map.forEachFeatureAtPixel(event.pixel, function(feature,layer) {
			console.log(feature);
	            
			if (layer.get('name') == 'posizione_utenti')
				{
				console.log(feature);
				return feature;
				}
			return null;
		});
		
		if (feature) {
			
			popup.setPosition(event.coordinate);
			$(element).popover({
				'placement' : 'top',
				'html' : true,
				
				'content': feature.get('user')
				
	
			});
			
		
			$(element).popover('show');
			
		} else {
			$(element).popover('destroy');
		}
	
	});
	
	
	
	
	
	
	
	
	
	
			
	
	
	