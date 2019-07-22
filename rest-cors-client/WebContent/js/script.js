$(document).ready(function() {

	$.ajax({
		url: "http://localhost:8080/greeting"
	}).then(function(data) {
		$("#content").html(data.id + "<br/>" + data.content);
	})
	
})
