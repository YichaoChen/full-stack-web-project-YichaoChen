function InputCheck(LoginForm)
{
  if (LoginForm.username.value === "")
  {
    alert("Please enter your username!");
    LoginForm.username.focus();
	return (false);
  }else if (LoginForm.password.value === "")
  {
    alert("Please enter your password!");
    LoginForm.password.focus();
    return (false);
  }else if (LoginForm.password.value !== LoginForm.confirmpassword.value)
  {
    alert("Password does not match!");
    LoginForm.password.focus();
    return (false);
  }else if (LoginForm.email.value === "")
  {
    alert("Please enter your email address!");
    LoginForm.password.focus();
    return (false);
  }else{
	return (true); 
  }
}