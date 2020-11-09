<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
  <div align=center>
<jsp:include page="header.jsp"/>

<div align="left"><a href="adminhome1.jsp">AdminHome</a></div>	     
	<form action="process" method="post">
		<div align=center>
			<h2>Loan application No </h2>			
		</div>
		<div style="color: 00ff00;" >${msg }</div>	
			
		<label >Loan Application No.</label>
		<input type="text" name="loanapplicationno">
		
		<input type="submit" name="submit" />
		<input type="hidden" name="submit"  value="updatestatus"/>	
		
	</form>
	
	
	
<jsp:include page="footer.jsp"/> 

</body>
</html>