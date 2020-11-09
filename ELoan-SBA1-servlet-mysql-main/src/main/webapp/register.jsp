<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan Register </title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<div align=center>
	<h2>Welcome! Register YourSelf</h2>
	<!--  <form action="registerNewUser" method="post"> -->
	<c:if test="${msg!=null }">
			<p><strong>${msg }</strong></p>
		</c:if>
	  
	<form action="registernewuser" method="post">
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="password" id="password" name="password"> </div>
		</div>
		<div>
			<div>
			<input type="submit"  value="Register">
			<input type="hidden" name="action" value="registernewuser"> </div>
		</div>
		<!-- <a action="user?action=registerNewUser">New User? register here</a>  -->
		 <h3>Once Registered Login here <a href="index.jsp">Login</a></h3>
	
	</form>
	</div>
	</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>