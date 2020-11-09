package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.iiht.evaluation.eloan.dao.ConnectionDao;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.xdevapi.Statement;


@WebServlet({"/user" , "/registernewuser","/placeloan","/trackloan","/editloan","/editloanui" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
 String LoggedInUser;
	
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
		
		String action = request.getParameter("action");
		String MessageNote=null;
		//
		System.out.println(action     +  " " + action);
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {				
			throw new ServletException(ex.getMessage());			
		}
			RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	//private String validate(HttpServletRequest request, HttpServletResponse response) 
	private String validate(HttpServletRequest request, HttpServletResponse response) throws Exception,SQLException, ClassNotFoundException,  IOException,NullPointerException {
		/* write the code to validate the user */		
		HttpSession session=request.getSession();
		String userName1 = request.getParameter("loginid");
		String password1 = request.getParameter("password");
		System.out.println(userName1+ "+++"+password1);		
		String view="";
			try {		
					int userExistFlag=connDao.getUserCount(userName1);
					if(!(userExistFlag==0))
					{	
						System.out.println("Before");
						User user =connDao.getUser(userName1);					
						
					
						if (userName1.equals("admin") && password1.equals(user.getPassword()))
						{															
							view = "adminhome1.jsp";
							return view;						
							
						}					
						else if (userName1.equals(user.getUsername()) && password1.equals(user.getPassword()))
						{
												
							view="userhome.jsp";	
							LoggedInUser=userName1;
							//request.setAttribute("LoggedName", LoggedInUser);
							session.setAttribute("LoggedName", LoggedInUser);		
							return view;
						}				
						else 
						{	System.out.println("Password failure");							
							request.setAttribute("msg", "Password is incorrect or Account does not exist.Please re-try with correct Passsword.");	
							
						}
					}
					else  
					{
						request.setAttribute("msg", "Account does not exist. Please click on Register link to avail e-loan service.");
					}
			
			} 	catch (Exception e) {				
				throw new ServletException(e.getMessage());	
			}				
		return view;
		
	}
	
	//private String placeloan
	private String placeloan(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		/* write the code to place the loan information*/	
		System.out.println("welcome to placeloan page");
		return "application.jsp";
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		
		return "loanDetails.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("LoggedName");
		//String username=(String) request.getAttribute("LoggedName");
		String loanAppNo = request.getParameter("editloanapplicationno");
		
		LoanInfo LoanAppNoFetched;
		LoanAppNoFetched=connDao.getLoanDetail(username,loanAppNo);	
		if(LoanAppNoFetched.getApplno()==null)
		{
			request.setAttribute("msg", "This Loan Application does not exist");
		}
		request.setAttribute("TrackLoanApplicationNp", LoanAppNoFetched.getApplno());
		request.setAttribute("TrackLoanpurpose", LoanAppNoFetched.getPurpose());
		request.setAttribute("TrackLoanAmtRequest", LoanAppNoFetched.getAmtrequest());
		request.setAttribute("TrackLoanBStructure", LoanAppNoFetched.getBstructure());
		request.setAttribute("TrackLoanBIndicator", LoanAppNoFetched.getBindicator());
		request.setAttribute("TrackLoanTaxIndicator", LoanAppNoFetched.getTaxindicator());
		request.setAttribute("TrackLoanAddress", LoanAppNoFetched.getAddress());
		request.setAttribute("TrackLoanEmail", LoanAppNoFetched.getEmail());
		request.setAttribute("TrackLoanMobile", LoanAppNoFetched.getMobile());
		request.setAttribute("TrackLoanStatus", LoanAppNoFetched.getStatus());
		request.setAttribute("TrackLoanDao", LoanAppNoFetched.getDoa());		
		
		if(request.getAttribute("TrackLoanStatus").toString().equals("OPEN") || request.getAttribute("TrackLoanStatus").toString().equals("INPROGRESS"))
		{
		return "editloanui.jsp";
		}
		else
		{
			request.setAttribute("msg", "This Loan application cannot be edited. Please get in touch with Admin");
			return "userhome.jsp";
		}
	
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		
//		user.login(user);
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException,  ServletException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */				
		String userName = request.getParameter("loginid");
		String password = request.getParameter("password");
		System.out.println(userName + "++++" + password);
		String viewN="index.jsp";
			int userCount=connDao.getUserCount(userName);	
			if((userCount==0))
			{
				connDao.registerUser(userName,password);							
				viewN = "register.jsp";
				request.setAttribute("msg", "Registered Successfully. Please click on login link to Login.");					
			}
			else{
				request.setAttribute("msg", "Account aleady exist. It cannot be registered again.");				
			}			
				return viewN;
	
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */	
		
		return "register.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("LoggedName");
		String loanAppNo = request.getParameter("loanapplicationno");
		System.out.println("TrackLoan:loanAppNo"+ loanAppNo + "Username"+username);
		LoanInfo LoanAppNoFetched;
		LoanAppNoFetched=connDao.getLoanDetail(username,loanAppNo);	
		if(LoanAppNoFetched.getApplno()==null)
		{
			request.setAttribute("msg", "This Loan Application does not exist");
		}
		request.setAttribute("TrackLoanApplicationNp", LoanAppNoFetched.getApplno());
		request.setAttribute("TrackLoanStatus", LoanAppNoFetched.getStatus());
		
		
		System.out.println("TrackLoan:"  + LoanAppNoFetched.getApplno() +"loanAppNo"+ loanAppNo);					
		return "loanDetails.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		LoanInfo loanInfo = new LoanInfo();		
		String userName=request.getParameter("username") ;//getting fetched form jsp
			
		//String userName= LoggedInUser;	
		loanInfo.setApplno(request.getParameter("loanapplicationno"));
		loanInfo.setPurpose(request.getParameter("loanpurpose"));
		loanInfo.setAmtrequest(Integer.parseInt(request.getParameter("loanamountrequested")) );		
		loanInfo.setBstructure(request.getParameter("businessstructure") );
		loanInfo.setBindicator(request.getParameter("billingindicator")) ;
		loanInfo.setTaxindicator(request.getParameter("taxindicator")) ;
		loanInfo.setAddress(request.getParameter("address")) ;
		loanInfo.setEmail(request.getParameter("email")) ;
		loanInfo.setMobile(request.getParameter("mobile")) ;
//			//1:Sneh 2:to buy a house 3:8.372839495E9 4:Individual 5:Salaried 6:Tax Payer 7:zxvzx 8:abc@gmail.com 9:1234567890  10:mobileparsedDate11:mobileparsedDate12:1970-01-01
		System.out.println("Loan Application appple for "+ userName);
		connDao.updateLoanInfo(loanInfo,userName);		
		LoanInfo loanApplicationNo=connDao.getLoanApplNo(userName);
		request.setAttribute("msg", "Loan Application has been edited successfully for Loan application no  "+loanApplicationNo.getApplno());		
		
		return "editloan.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page-- v */	
				
		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */	
		
		LoanInfo loanInfo = new LoanInfo();
		System.out.println("welcome to Application page");
		String userName=request.getParameter("username") ; //getting fetched form jsp
		System.out.println("welcome to Application page useranme : "+ userName );	
		//String userName= LoggedInUser;	
		
		loanInfo.setPurpose(request.getParameter("loanpurpose"));
		loanInfo.setAmtrequest(Integer.parseInt(request.getParameter("loanamountrequested")) );		
		loanInfo.setBstructure(request.getParameter("businessstructure") );
		loanInfo.setBindicator(request.getParameter("billingindicator")) ;
		loanInfo.setTaxindicator(request.getParameter("taxindicator")) ;
		loanInfo.setAddress(request.getParameter("address")) ;
		loanInfo.setEmail(request.getParameter("email")) ;
		loanInfo.setMobile(request.getParameter("mobile")) ;
//			//1:Sneh 2:to buy a house 3:8.372839495E9 4:Individual 5:Salaried 6:Tax Payer 7:zxvzx 8:abc@gmail.com 9:1234567890  10:mobileparsedDate11:mobileparsedDate12:1970-01-01
//		System.out.println("Loan Application appple for "+ userName);
		connDao.insertLoanInfo(loanInfo,userName);		
		LoanInfo loanApplicationNo=connDao.getLoanApplNo(userName);
		request.setAttribute("msg", "Loan Application submitted successfully.Your Loan application no is "+loanApplicationNo.getApplno());	
		return "trackloan.jsp";
	}
}