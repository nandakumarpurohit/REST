$(document).ready(function() {

	$("#fetch").click(function() {
		$.ajax({
	
			url: "http://localhost:8080/rest-jackson-simple/rest/json/metallica/get"
			
		}).then(function(data) {
			console.log(data);
			$("#content").html("<b>" + data.title + "</b><br/><b>" + data.singer + "</b>");
		})
	});
	
	$("#send").click(function() {
		$.ajax({
			url: 'http://localhost:8080/rest-jackson-simple/rest/json/metallica/post',
		    dataType: 'text',
		    type: 'post',
		    contentType: 'application/json',
		    data: JSON.stringify( { "title": $('#title').val(), "singer": $('#singer').val() } ),
		    processData: false,
		    success: function( data, textStatus, jQxhr ){
		        $('#content').html( JSON.stringify( data ) );
		    },
		    error: function( jqXhr, textStatus, errorThrown ){
		        console.log( errorThrown );
		    }	
		})
	})
	
	
	
})

