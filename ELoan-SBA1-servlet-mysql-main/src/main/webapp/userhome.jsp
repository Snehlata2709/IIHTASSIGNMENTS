<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<p>Welcome ${LoggedName }</p>
<div style="color: #FF0000;" >${msg }</div>

<div align="center"><h4>User Dash Board</h4></div>


<!--  <a href="user?action=application">Apply for Loan</a><br>-->
<div align="center">
<a href="user?action=placeloan">Apply for Loan</a><br>
<a href="trackloan.jsp">Track Loan Application</a><br>
<a href="editloan.jsp">Edit Loan Application</a><br>
<a href="index.jsp">Logout</a>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>