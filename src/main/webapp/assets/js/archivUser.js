/**
 * 
 */

$('.archived').on('change', function() {
	console.log(this.value);
	var email = $('td:nth-child(3)', $(this).parents('tr')).text();
	console.log(email);

	$.ajax({
		type : "POST",
		url : "/Wgis/Lock",
		async : true,
		data : {
			'email' : $('td:nth-child(3)', $(this).parents('tr')).text()
			
		}
	

	});

});