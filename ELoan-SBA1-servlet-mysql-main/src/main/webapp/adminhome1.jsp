<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin home</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h4>Admin Dash Board</h4>
<div align="center">
<a href="admin?action=listall">List All</a><br>
<!-- <a href="admin?action=process">Process Loan</a><br>	</div>-->
<a href="process.jsp">Process Loan</a><br>
<a href="admin?action=updatestatus">Update Loan status</a><br>
<a href="index.jsp">logout</a><br>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>