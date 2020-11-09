<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info	     
	     -->
<div align=center>
<jsp:include page="header.jsp"/>

<div align="left"><a href="userhome.jsp">UserHome</a></div>	     
	<form action="editloan" method='post'>
		<div align=center>
			<h2>Edit Loan application No </h2>			
		</div>
		<div style="color: 00ff00;" >${msg }</div>	
		
		<label >Loan Application No.</label>
		<input type="text" name="editloanapplicationno">
		
		<input type="submit" name="submit" />
		<input type="hidden" name="action" value="editLoanProcess" />
	</form>
	
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>