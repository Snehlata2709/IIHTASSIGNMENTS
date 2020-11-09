<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->
	<form action="registeruser">
	<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid" required> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="Create password" required> </div>
		</div>
		<div>
			<div><input type="submit" value="Register"> </div>
		</div>
	</form>
</body>
</html>