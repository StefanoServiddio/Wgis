	/**
	 * 
	 */
	
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
					color : 'green'
				})
			})
		})
	});
	
	map.addLayer(pos_user_layer);
	
	var s = 1000;
	var min = 60 * s;
	var hour = 3600 * s;
	
	$('document').ready(
			function() {
				setInterval(function() {
	
					$.ajax({
						url : 'http://localhost:9925/Wgis/PositionList',
						data : {
							user : 'stefano@example.com'
						},
						type :'POST',
						dataType : 'json',
						success : function(response) {
							console.log("aggiorno posizione utente");
							var coordinates = [ response.lon, response.lat ];
							var valueGeoPosSte = new ol.Feature(new ol.geom.Point(
									coordinates));
							source_vect_pos.addFeature(valueGeoPosSte);
	
						}
					});
	
				}, 10*s);
			});
	

	
	var element = document.getElementById('popup');

	var popup = new ol.Overlay({
	  element: element,
	  positioning: 'bottom-center',
	  stopEvent: false
	});
	map.addOverlay(popup);

	// display popup on click
	map.on('click', function(evt) {
	  var feature = map.forEachFeatureAtPixel(evt.pixel,
	      function(feature, layer) {
		  
	        return feature;
	      });
	  console.log(feature);
	  
	  
	  if (feature) {
		 
	    popup.setPosition(evt.coordinate);
	    $(element).popover({
	      'placement': 'top',
	      'html': true,
	      'content': 'Stefano'
	    });
	    $(element).popover('show');
	  } else {
	    $(element).popover('destroy');
	  }
	  
	  
	});
	


	