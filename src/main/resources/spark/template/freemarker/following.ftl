<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>Dr.Know | Following</title>
<link rel="stylesheet" href="/stylesheets/style.css" type="text/css" media="all" /> 

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
		<br/>
		<p class="title">Newest Questions</p>
		<hr/><br/>
		
		<p class="question">${title[0]}</p>
		<p class="para">${description[0]}</p>
		<p class="more"><span><a href="question.html">MORE</a></span></p>
		<br/>
		<p class="info">Follow&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answers(68)</p>
		<br/>
		<hr style="border:0.5px #cccccc dashed"/>
		<br/>
		
		<p class="question">${title[1]}</p>
		<p class="para">${description[1]}</p>
		<p class="more"><span>MORE</span></p>
		<br/>
		<p class="info">Follow&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answers(99)</p>
		<br/>
		<hr style="border:0.5px #cccccc dashed"/>
		<br/>
		
		<p class="question">${title[2]}</p>
		<p class="para">${description[2]}</p>
		<p class="more"><span>MORE</span></p>
		<br/>
		<p class="info">Follow&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answers(32)</p>
		<br/>
		<hr style="border:0.5px #cccccc dashed"/>
		<br/>
		<p class="explore">Explore more questions --></p>
		<br/>

	</div>
	
	<div class="bottom">
	<br/>
	<p class="creator">Nice to meet you in this knowledgeable world! &nbsp; | &nbsp; Created by Yichao Chen</p>	
	</div>

	</div>
	
</body>
</html>
