package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


@WebServlet({"/admin" ,"/process"} )
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "callemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		String loanApplNo ="";
		String Status ="";
		//LoanInfo aloaninfo= connDao.AdmingetLoanDetail("");
		
		return "adminhome1.jsp";
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		
		return null;
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
	/* return to process page */
	
		String aloanNo= request.getParameter("loanapplicationno");
		ApprovedLoan aloaninfo= connDao.AdmingetLoanDetail(aloanNo);
		
		request.setAttribute("aapplno", aloaninfo.getApplno());
		request.setAttribute("aamotsanctioned", aloaninfo.getAmotsanctioned());
		request.setAttribute("ainterestRate", aloaninfo.getInterest());
		request.setAttribute("aloanterm", aloaninfo.getLoanterm());
		request.setAttribute("apsd", aloaninfo.getPsd());
		request.setAttribute("alsd", aloaninfo.getLcd());
		request.setAttribute("aemi", aloaninfo.getEmi());

		//Double termPaymnetAmount=aloaninfo.getAmotsanctioned()*(1+aloaninfo.getInterest()/100)^(aloaninfo.getLoanterm();
		//Double MonthlyPayment=(termPaymnetAmount)/(aloaninfo.getLoanterm());
		
		
		
		return "calemi.jsp";
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException {
	/* write the code to display all the loans */
		request.setAttribute("loanInfo",connDao.getAll());
		return "listall.jsp";
	}

	
}