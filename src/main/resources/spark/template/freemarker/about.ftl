<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dr.Know | Home</title>
<link rel="stylesheet" href="stylesheets/style.css" type="text/css" media="all" /> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
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
			<li><a href="/test">Home</a></li>
		</ul>	
		</div>
		
	  <div class="bar">
		<input name="search" type="submit" id="search" value="Search"/>
	    <input name="searchbox" type="text" size="30" id="searchbox" value=" Search your question..." onBlur="if(this.value=='') {this.value=' Search your question...'}" onFocus="if(this.value==' Search your question...') 		{this.value=''; this.style.color='#999999';}" />
	  </div>
	
	</div>

	<script>
	    $(document).ready(function () {
		    $.ajax({
		        type: "GET",
		        url: "info.xml",
		        dataType: "xml",
		        success: xmlParser
		    });
		});
	    function xmlParser(xml) {
	       $(xml).find("Person").each(function () {
	           $(".blank").append($(this).find("Name").text()+' '+$(this).find("Profession").text()+' '+$(this).find("Age").text()+$(this).find("Phone").text()+$(this).find("Address").text()+$(this).find("Description").text()+$(this).find("Course").text());
	       });
	    }
    </script>
	
	<div class="content">
		<div class="blank"></div>

	</div>
	
	<div class="bottom">
	<br/>
	<p class="creator">Nice to meet you in this knowledgeable world! &nbsp; | &nbsp; Created by Yichao Chen</p>	
	</div>

	</div>
	
</body>
</html>
