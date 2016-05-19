	/**
	 * 
	 */
	
	//var extent2 = [0,0,492,792];
	//
	//      var projection2 = new ol.proj.Projection({
	//    	  code: "EPSG:4326",
	//          //units: 'pixels',
	//    	  extent: extent2
	//      });
	//    var imageStatic=  new ol.layer.Image({
	//          source: new ol.source.ImageStatic({
	//            imageSize: [492, 792],
	//            url: '/Wgis/assets/img/allertaMeteoGeo.tif',
	//            projection: projection2,
	//            imageExtent: extent2
	//          })
	//        });
	//
	//      var map2 = new ol.Map({
	//        layers: [imageStatic],
	//        target: 'map2',
	//        view: new ol.View({
	//          projection: projection2,
	//          center:[0, 0],
	//          zoom: 1,
	//          extent: extent2
	//         
	//        })
	//      });
	//
	//  map.addLayer(imageStatic);
	
	
	var agentUrl = 'http://localhost:9925/Wgis/assets/img/allertaMeteoGeo.tif';
	
	var bounds = [ 713101.704, 4044061.027, 713101.704, 4044061.027];
	

	
	var view2 = new ol.View({
		center : [ -87.7302542509315, 43.744459064634 ],
		projection : "EPSG:3857",
		zoom : 12
	});
	
	var sorgente = new ol.source.ImageMapGuide({
		projection : "EPSG:3857",
		url : agentUrl,
		metersPerUnit : 111319.4908,
		
		imageSize: [792, 452],
		ratio : 2
	});
	
	var raster = new ol.layer.Image({
		extent : bounds,
		source : sorgente
	});
	
	var map2 = new ol.Map({
		layers : [ raster ],
		target : 'map2',
		view : view2
	});
