<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.*,java.util.*" %>
 <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan System</title>
</head>

<body onload="noBack();" onpageshow="if(event.persisted) noback();">
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->	

	
<div align=center>
<jsp:include page="header.jsp"/>

	<h2>Welcome To e-Loan Service</h2>				
<div align=center>
	<h2>Login</h2>		
	<form action="user" method="post" >
		<div>
		<div style="color: #FF0000;" >${msg }</div>
			<div><label >Enter LoginId</label></div>
			<div><input type="text" id="loginid" name="loginid" required/></div>
		</div>
		<div>
			<div><label>Enter password</label></div>
			<div><input type="password" id="password" name="password" required /></div>
		</div>		
		<div>
			<div> 
			<!--  <input type="submit" name="action" value="validate"/>	-->	
			<!-- <!-- <a href="user?action=registerNewUser">New User? register here</a> <a href='/user?action=validate'></a> -->
			
			<input type="submit" value="Log In"/>
			<input type="hidden" name="action" value="validate"/>
			</div>		
		</div>		     
	  <h3>You don't have a account?<a href="register.jsp">Register</a></h3> 
	</form>
</div>

</div>
<jsp:include page="footer.jsp"/>	
</body>
</html>