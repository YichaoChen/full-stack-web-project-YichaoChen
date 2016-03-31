$(function() {
	$.ajax({
		url : "/api/test",
		success : function(result) {
			var question = JSON.parse(result);
			console.log(result);
			for ( var i = 0; i < question.length; i++) {
				$(".question_wrapper").apend(
						'<p class="question" id="' + question[i].id + '">' + question[i].title + '</p>' 
						
				);
			}
		}
	});
});