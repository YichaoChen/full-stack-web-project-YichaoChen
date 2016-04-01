
window.onload = init;



function init() {
	setControls();
}

function setControls() {
	//Populating the array with objects
	//containing default texts and corresponding validation functions
	var setupArray = [
         {
		 	defaultText: '3 to 15 characters',
		    validate: checkUsername
		 },
		 {
		 	defaultText: 'at least 6 digits',
			validate: checkPassword
		 },
		 {
		 	defaultText: 'type again',
			validate: checkPasswordConfirm
		 },
		 {
		 	defaultText: 'enter valid email address',
		    validate: checkEmail
		 },
	];

// Set required fields
	var requiredFields = document.getElementsByClassName('required');
	//Displaying default text in each field
	for (var i=0; i<requiredFields.length; i++) {
		requiredFields[i].value = setupArray[i].defaultText;
		requiredFields[i].style.color = '#a8a8a8';
		requiredFields[i].style.fontStyle = 'normal';

		// Assigning each object to the corresponding field
		requiredFields[i].setupObject = setupArray[i];

		requiredFields[i].onfocus = function() {
			if (this.value === this.setupObject.defaultText) {
				this.value = '';
				this.style.color = '#000';
				this.style.fontStyle = 'normal';
			}
		}; //end focus
		requiredFields[i].onblur = function() {
			if (this.value === '') {
				this.value = this.setupObject.defaultText;
				this.style.color = '#a8a8a8';
				this.style.fontStyle = 'normal';
			}
			this.setupObject.validate();
		}; //end blur
	} //end for loop
} //end setup

// Validation functions
function checkUsername() {
	var username = document.getElementById('username');
	var errUName = document.getElementById('errUname');
	if (username.value === '' || username.value === '3 to 15 characters') {
		errUName.innerHTML='Please enter your user name';
		errUName.style.display='block';
		return false;
	} else if(username.value.length<3||username.value.length>15){
		errUName.innerHTML='Please enter a valid user name';
		errUName.style.display='block';
		return false;
		}
	else {
		errUName.style.display='none';
		return true;
	}
}

function checkPassword() {
	var password = document.getElementById('password');
	var errPass = document.getElementById('errPass');
	if (password.value === '' || password.value === 'at least 6 digits') {
		errPass.innerHTML='Please enter your password';
		errPass.style.display='block';
		return false;
	} else if(password.value.length<6||password.value.length>30){
		errPass.innerHTML='Please enter a valid password';
		errPass.style.display='block';
		return false;
		}
	else {
		errPass.style.display='none';
		return true;
	}

}

function checkPasswordConfirm() {
	var confirm = document.getElementById('confirm');
	var password = document.getElementById('password');
	var errComfirm = document.getElementById('errConfirm');
	if (confirm.value === '' || confirm.value === 'type again') {
		errComfirm.innerHTML='Please re-enter your password';
		errComfirm.style.display='block';
		return false;
	} else if(confirm.value != password.value){
		errComfirm.innerHTML='The password is not match';
		errComfirm.style.display='block';
		return false;
		}
	else {
		errComfirm.style.display='none';
		return true;
	}

}

function checkEmail(){
	var email = document.getElementById('email');
	var emailRegex = /[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}/;
	var errEmail = document.getElementById('errEmail');
	if (email.value === '' || email.value === 'enter valid email address') {
		errEmail.innerHTML='Please enter your email address';
		errEmail.style.display='block';
		return false;
	} else if (!emailRegex.test(email.value)) {
		errEmail.innerHTML='Please enter a valid email address';
		errEmail.style.display='block';
		return false;
	} else {
		errEmail.style.display='none';
		return true;
	}

}


function submitTest(){
		  var result = true;
			result = checkUsername() && result;
			result = checkPassword() && result;
			result = checkEmail() && result;
			if(!result){
				window.alert("Please fill in all information");
			}else{
				window.alert("Successfully registered");
				}
			return result;
}

$(function(){
        $("#submit").click(function(){

        var username = $("#username").val();
		var password = $("#password").val();
        var email = $("#email").val();

        var obj = JSON.stringify({"username":username,"password":password,"email":email});
              $.ajax({
                  contentType:'application/json',
                  url: '/api/register',
                  type: "POST",
                  datatype: "json",
                  data: obj,
                  success: function(data) {
                      alert("You have registered successfully!");
                      window.location.href='/index.html';
											console.log(data);
                  }
              });
                    return false;
          });
});


