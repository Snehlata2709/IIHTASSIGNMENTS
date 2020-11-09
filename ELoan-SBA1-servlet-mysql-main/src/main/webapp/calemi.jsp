<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
  -->
<jsp:include page="header.jsp"/>

<div align="left"><a href="adminhome1.jsp">AdminHome</a></div>
	<form action="" method='post'>
	<div align=center>
		<p><Strong>Note: User Name, Loan Application no,status fields are non editable.</Strong> </p>
	</div>
	<div style="color: 00ff00;" >${msg }</div>
	
	<div><label >Loan Amount Requested</label>
	<input type="text" name="username" value=${" } readonly></input></div>
	
	<div><label >Loan Application No.</label>
	<input type="text" name="loanapplicationno" value=${TrackLoanApplicationNp } readonly></input></div>
	
	<div><label >Loan Purpose</label>
	<input type="text" name="loanpurpose" value=${TrackLoanpurpose }></input></div>
		
	<div><label >Loan Amount Requested</label>
	<input type="number" name="loanamountrequested"  value=${TrackLoanAmtRequest } ></input></div>
	
	<div><label >Business Structure[Individual/Organization]</label>
	<<input type="text" name=businessstructure value=${TrackLoanBStructure } ></input></div>
	
	<div><label >Billing Indicator[Salaried/Not Salaried]</label>
	<input type="text" name="billingindicator" value=${TrackLoanBIndicator } ></input></div>
	
	<div><label>Tax Indicator[TaxPayer/Not TaxPayer]</label>
	<input type="text" name="taxindicator" value=${TrackLoanTaxIndicator } ></input>	</div>
	
	<div><label >Address</label>
	<input type="text" name="address" value=${TrackLoanAddress } ></input></div>
	
	<div><label>Email</label>
	<input type="text" name="email" value=${TrackLoanEmail }></input>
	
	<div><label >Mobile</label>
	<input type="text" name="mobile" value=${TrackLoanMobile }></input>	</div>
	
	<div><label >Status</label>
	<input type="text" name="LoanStatus" value=${TrackLoanStatus } readonly></input>	</div>
	
	
	<input type="submit" name="submit" />
	<input type="hidden" name="action" value=""></input>
</form>
	
<jsp:include page="footer.jsp"/>
 

</body>
</html>