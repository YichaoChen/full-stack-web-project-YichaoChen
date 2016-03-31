$(function() {
	$.ajax({
		url : "/api/index",
		success : function(result) {
			var question = JSON.parse(result);
			for ( var i = 0; i < question.length; i++) {
				$("div.question_wrapper").append(
						'<p class="question" id="' + question[i].id + '">' + question[i].title + '</p>' + '<br/>'
				);
			}
		}
	});
});