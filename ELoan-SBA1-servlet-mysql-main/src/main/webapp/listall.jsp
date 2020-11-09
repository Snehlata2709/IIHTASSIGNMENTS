<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
<c:choose>
	<c:when test="${loanInfo==null || loanInfo.isEmpty() }">
		<p>No Records fetched</p>
	</c:when>
<c:otherwise>
	<table>
		<thead>
			<tr>
				<th>LoanApplicationNo.</th>
				<th>LoanPurpose</th>
				<th>LoanAmtReuested</th>
				<th>LoanBStruture</th>
				<th>LoanBindicator</th>
				<th>LoanTaxIndicator</th>
				<th>Address</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Status</th>
				<th>DOA</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${loanInfoGetAll}" >
			<tr>
				<td>${c.billingindicator }</td>
				<td>${loanInfoGetAll.set(purpose) }</td>
				<td>${loanInfoGetAll.setApplno(amtrequest) }</td>
				<td>${loanInfoGetAll.setApplno(bstructure) }</td>
				<td>${loanInfoGetAll.setApplno(bindicator) }</td>
				<td>${loanInfoGetAll.setApplno(tindicator) }</td>
				<td>${loanInfoGetAll.setApplno(address) }</td>
				<td>${loanInfoGetAll.setApplno(email) }</td>
				<td>${loanInfoGetAll.setApplno(mobile) }</td>
				<td>${loanInfoGetAll.setApplno(status) }</td>
				<td>${loanInfoGetAll.setApplno(doa) }</td>
		
			</tr>
			</c:forEach>		
		</tbody>
	</table>	
</c:otherwise>	
</c:choose>	
</body>
</html>