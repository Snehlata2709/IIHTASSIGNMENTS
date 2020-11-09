<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan System</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div style="color: #FF0000;" >${msg }</div>

	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method  
	-->
	<div align="left"><a href="userhome.jsp">UserHome</a></div>
	<div align=center>
	<h3>e-Loan Application Status</h3>			
	       
	        	<label>Loan Application No.</label>
				<div><input type="text" name="applno"  value=${TrackLoanApplicationNp } readonly/></div>
			
			 	<label >Status</label>
			     <div><input type="text" name="status" value=${TrackLoanStatus } readonly/></div>
			<!--  <input type="hidden" name="action" value="loanDetails" />-->
	</div>
<jsp:include page="footer.jsp"/>
</body>
</html>