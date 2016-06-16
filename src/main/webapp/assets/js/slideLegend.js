/**
 * 
 */


$(document).ready(function(){
    $("#legend").click(function(){
        $("#imgLegenda").slideToggle("slow");
        $(this).text(function(i, text){
        	console.log(this)
        	if ($(this).text() == "Mostra Legenda") 
        	  { 
        	     $(this).text("Nascondi Legenda"); 
        	  } 
        	  else 
        	  { 
        	     $(this).text("Mostra Legenda"); 
        	  }; 
        })
    });
});

