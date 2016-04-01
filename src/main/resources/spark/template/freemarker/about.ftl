<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dr.Know | Home</title>
<link rel="stylesheet" href="stylesheets/style.css" type="text/css" media="all" /> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>

	window.onload = about;

	function about () {
		$.ajax({
			url : "/api/about",
			type : "get",
			success : function(result) {
				
				console.log(result);//Testing
				person = result.getElementsByTagName("Person");
				
	        $("div.blank").append(
	        	'<p>Name:'+person[0].childNodes[0].firstChild.nodeValue+'</p>'+
	        	'<p>Profession:'+person[0].childNodes[1].firstChild.nodeValue+'</p>'+
	        	'<p>Age:'+person[0].childNodes[2].firstChild.nodeValue+'</p>'+
	        	'<p>University:'+person[0].childNodes[3].firstChild.nodeValue+'</p>'+
	        	'<p>School:'+person[0].childNodes[4].firstChild.nodeValue+'</p>'+
	        	'<p>Major:'+person[0].childNodes[5].firstChild.nodeValue+'</p>'+
	        	'<p>Course:'+person[0].childNodes[6].firstChild.nodeValue+'</p>'+
	        	'<p>Phone:'+person[0].childNodes[7].firstChild.nodeValue+'</p>'+
	        	'<p>Email:'+person[0].childNodes[8].firstChild.nodeValue+'</p>'+
	        	'<p>Address:'+person[0].childNodes[9].firstChild.nodeValue+'</p>'
	        													
			);
				
			}
		});
	}
</script>
</head>

<body>	
					
	<div class="banner">
		<div class="img2"><img src="images/slogan.png" alt="Welcome to Dr.Know!"/></div>
		<a href="login.html"><p class="login">Login  /  Sign Up</p></a>
	</div>
	
	<div class="main">
		
	<div class="top">
		<div class="img"><img src="images/logo.png" alt="Welcome to Dr.Know!" height="100" width="180"/></div>

		<div class="menu">
		<ul>
			<li><a href="/following">Followings</a></li>
			<li><a href="ask.html">Ask</a></li>
			<li><a href="tags.html">Tags</a></li>
			<li><a href="index.html">Home</a></li>
		</ul>	
		</div>
		
	  <div class="bar">
		<input name="search" type="submit" id="search" value="Search"/>
	    <input name="searchbox" type="text" size="30" id="searchbox" value=" Search your question..." onBlur="if(this.value=='') {this.value=' Search your question...'}" onFocus="if(this.value==' Search your question...') 		{this.value=''; this.style.color='#999999';}" />
	  </div>
	
	</div>

	
	<div class="content">
		<br>
		<div class="blank"></div>

	</div>
	
	<div class="bottom">
	<br/>
	<p class="creator">Nice to meet you in this knowledgeable world! &nbsp; | &nbsp; Created by <a href="/about">Yichao Chen</a></p>	
	</div>

	</div>
	
</body>
</html>
