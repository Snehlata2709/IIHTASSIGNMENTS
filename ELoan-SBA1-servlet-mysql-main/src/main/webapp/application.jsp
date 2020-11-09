<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel='stylesheet' />
<title>Loan Application Form</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<style type="text/css">

</style>
</head>
<body onload="myFunction()">
<jsp:include page="header.jsp"/>

<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
<div align=center>
		<h1>Apply For Mortage Loan</h1>
</div>
<div align="left"><a href="userhome.jsp">UserHome</a></div>

<form action="placeloan" method="POST">
	<div class="regform" >
	<div align=center>
		<p><Strong>Note: Please insert decimal  using the dot notation e.g 5.9</Strong> </p>
	</div>
	<div style="color: 00ff00;" >${msg }</div>
	<ul style="list-style:none;">
	<li><label for="UserName">User Name</label>
	<input type="text" name="username" value=${LoggedName } /></li>	
	<li><label for="loanpurpose">Loan Purpose</label>
	<input type="text" name="loanpurpose" /></li>	
	<li><label for="loanamountrequested">Loan Amount Requested</label>
	<input type="number" name="loanamountrequested" /></li>		
	<li><label for="businessstructure">Business Structure[Individual[I]/Organization[o]]</label>
	<input type="text" name=businessstructure size="50" /></li>
	<li><label for="billingindicator">Billing Indicator[Salarie[Y]/NotSalaried[N]]</label>
	<input type="text" name="billingindicator" size="50" /></li>
	<li><label for="taxindicator">Tax Indicator[TaxPayer[Y]/NoNTaxPayer[N]]</label>
	<input type="text" name="taxindicator" size="50" /></li>	
	<li><label for="address">Address</label>
	<input type="text" name="address" size="100" /></li>	
	<li><label for="email">Email</label>
	<input type="text" name="email" size="50" /></li>	
	<li><label for="mobile">Mobile</label>
	<input type="text" name="mobile" size="50" /></li>	
	<li><label for="submit"></label>
	<input type="submit" name="submit" />
	<input type="hidden" name="action" value="application" />	
	</li>
	</ul>
	
</div>	
	
</form>


<jsp:include page="footer.jsp"/>
</body>
</html>