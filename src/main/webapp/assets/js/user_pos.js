	/**
	 * 
	 */
	
	var source_vect_pos = new ol.source.Vector({
			
	});
	
	var pos_user_layer = new ol.layer.Vector({
		source : source_vect_pos,
		style : new ol.style.Style({
			image : new ol.style.Circle({
				radius : 4,
				stroke : new ol.style.Stroke({
					color : '#fff'
				}),
				fill : new ol.style.Fill({
					color : 'green'
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
						url : 'http://localhost:9925/Wgis/PositionList',
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
								
								
							
								$.each(response, function(idx, oggetto) {
									coordinates[0] = oggetto.lon;
									coordinates[1]=oggetto.lat ;	
									console.log("lon: "+coordinates[0]);
									console.log("lat: "+coordinates[1]);
									pos_user_features[idx] = new ol.Feature(new ol.geom.Point(coordinates));
										
									
								});
								 
								
								
								source_vect_pos.addFeatures(pos_user_features);	
								
								
								
							}else{
								console.log("Solo un oggetto");
								var coordinates = [ response.lon, response.lat ];
								var valueGeoPosSte = new ol.Feature(
										new ol.geom.Point(coordinates));
								source_vect_pos.addFeature(valueGeoPosSte);
								
								
							}
	
						}
					});
	
				}, 5* s);
			});
	
	var element = document.getElementById('popup');
	
	var popup = new ol.Overlay({
		element : element,
		positioning : 'bottom-center',
		stopEvent : false,
		content : 'Stefano'
	});
	map.addOverlay(popup);
	//map.addInteraction(selectSingleClick);
	
	// display popup on click
	map.on('dblclick', function(event) {
		console.log(event)
		var feature = map.forEachFeatureAtPixel(event.pixel, function(feature,
				layer) {
	
			if (layer.get('name') == 'posizione_utenti')
				return feature;
			return null;
		});
	
		if (feature) {
	
			popup.setPosition(event.coordinate);
			$(element).popover({
				'placement' : 'top',
				'html' : true
	
			});
			$(element).popover('show');
		} else {
			$(element).popover('destroy');
		}
	
	})
