<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan System</title>
</head>
<body>

<div align=center>
<jsp:include page="header.jsp"/>
	<h2>Track e-Loan Request</h2>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	<div align="left"><a href="userhome.jsp">UserHome</a></div>
	<form action="trackloan" method="POST">
     		<div style="color: #
     		FF0000;" >${msg }</div>
			<div><label > e-Loan Application Status </label></div>
			<div><input type="text" id="loanapplicationno" name="loanapplicationno" required/></div>			
			<div><input type="submit" value="submit" required/></div>
			<div><input type="hidden" name="action" value="displaystatus" /></div>
				  
		</div>
	</form>	
</body>
</html>