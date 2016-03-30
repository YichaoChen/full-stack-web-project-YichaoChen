$(function() {
	$.ajax({
		url : "/api/index",
		success : function(result) {
			var question = JSON.parse(result);
			console.log(result);
			for ( var i = 0; i < question.length; i++) {
				$("#que").prepend(
						'<p class="question" id="' + question[i].id + '">' + question[i].title + '</p>' + 
						'<p class="para" id="' + question[i].id + '">' + question[i].description + '</p>' +
						'<p class="more" id="' + question[i].id + '">' + '<span>' + '<a href="">' + 'MORE' + '</a>' + '</p>' +
						'<br/>' + 
						'<p class="info" id="' + question[i].id + '">' + 'Follow&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answers' + '</p>' + 
						'<br/>' + 
						'<hr style="border:0.5px #cccccc dashed"/>' +
						'<br/>'			
				);
			}
		}
	});
});