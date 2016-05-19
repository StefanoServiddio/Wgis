

var regStyle = new ol.style.Style ({

           fill: new ol.style.Fill({

            color: 'rgba(127,129,112,0.1)'


           }),
           stroke: new ol.style.Stroke({

            color: 'blue', width: 2})                             
        });


var reg = new ol.layer.Vector({
  source: new ol.source.Vector({
    url: './assets/geojson/regioni.geojson',
    format: new ol.format.GeoJSON(),
    projection: 'EPSG:3857' 
  }),

  style: regStyle
});


  

var prov = new ol.layer.Vector({
  source: new ol.source.Vector({
    url: './assets/kml/province.kml',
    format: new ol.format.KML(),
    projection: 'EPSG:3857'
    
  })
});

  
  var base_layer = new ol.layer.Tile({

    source: new ol.source.OSM()
  }); 

  var italy = ol.proj.fromLonLat([14.334, 40.965])

  var view = new ol.View({
  
    center: italy,
    zoom: 6,
  });


var scale = new ol.control.ScaleLine({

  className: 'ol-scale-line',
  target: document.getElementById('scale-line')
});
 

function formatCoord(fraction) {
  var template = 'Long: {x} | Lat: {y}';
  return (
    function(coordinate) {
        return ol.coordinate.format(coordinate, template, fraction);
    });
}


var mousePositionControl = new ol.control.MousePosition({
        coordinateFormat: formatCoord(8),
        projection: 'EPSG:38576',
        // comment the following two lines to have the mouse position
        // be placed within the map.
        className: 'custom-mouse-position',
        target: document.getElementById('mouse-position'),
        undefinedHTML: '&nbsp;'
      });

  var map = new ol.Map({

     controls: ol.control.defaults({
          attributionOptions: ({  collapsible: false })

           }).extend([mousePositionControl,scale]),
    renderer: 'canvas',
    target: 'map',
    layers: [base_layer, prov,reg],
    view: view

  });
  
  


