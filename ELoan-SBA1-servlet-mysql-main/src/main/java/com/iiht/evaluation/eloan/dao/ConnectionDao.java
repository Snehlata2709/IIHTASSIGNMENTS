package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.iiht.evaluation.eloan.dto.LoanDto;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.sun.jmx.snmp.Timestamp;


public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.cj.jdbc.Driver//com.mysql.jdbc.Driver
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	// put the relevant DAO methods here..
	public int getUserCount(String username) throws ClassNotFoundException
	{
		
		int flag=0;
		try (Connection con= connect();
		
		PreparedStatement ps=jdbcConnection.prepareStatement("SELECT count(*) from users where username=?")){
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();	
			rs.next();
			flag=rs.getInt(1);						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return flag;		
		
	}
	
	public User getUser(String username) throws ClassNotFoundException
	{
		
		User  existingUser = null;
		try (Connection con= connect();
		
		PreparedStatement ps=jdbcConnection.prepareStatement("SELECT * from users where username=?")){
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
						
			if(rs.next())
			{
				existingUser= new User();
				existingUser.setUsername(rs.getString("username"));
				existingUser.setPassword(rs.getString("password"));					
				
				//System.out.println("gshjgjhsgdhjfgdsj" + existingUser.getPassword());
			}							
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return existingUser;		
		
	}
	public void registerUser(String userName,String password) throws ClassNotFoundException, ServletException
	{		try (Connection con= connect();							    	
				PreparedStatement psI=jdbcConnection.prepareStatement("Insert into users values(?,?,?)")){
						//	PreparedStatement psI=jdbcConnection.prepareStatement("Insert into users(username,password) select ?,? "
						//		+ "from dual where not exists (select * from admins where username=?");
						String role="User";												
						psI.setString(1, userName);
						psI.setString(2, password);									
						psI.setString(3, role);				
			
						psI.executeUpdate();
		} catch (Exception ex) {
			// TODO handle exception
			throw new ServletException(ex.getMessage());
		}		
	}
	
	public void insertLoanInfo(LoanInfo loan,String userName) throws ClassNotFoundException, ServletException
	{		try (Connection con= connect();							    	
				PreparedStatement psLoan=jdbcConnection.prepareStatement("Insert into LOANINFO values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)")){
				
				java.util.Date today = new java.util.Date();
				java.sql.Date now= new java.sql.Date(today.getTime());
				DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				String parsedDate=df.format(now);
				String loanapplno=  loan.getMobile()+parsedDate;
				String status="OPEN";
				loan.setApplno(loanapplno);
				loan.setStatus(status);;
				System.out.println("1:"+userName );
								psLoan.setString(1, userName);						
								psLoan.setString(2, loan.getApplno());						
								psLoan.setString(3, loan.getPurpose());									
								psLoan.setInt(4, loan.getAmtrequest());															
								psLoan.setString(5, loan.getBstructure());
								psLoan.setString(6, loan.getBindicator());
								psLoan.setString(7, loan.getTaxindicator());
								psLoan.setString(8, loan.getAddress());
								psLoan.setString(9, loan.getEmail());
								psLoan.setString(10, loan.getMobile());
								psLoan.setString(11, loan.getStatus());
								psLoan.setDate(12,now);
								psLoan.setDate(13,null);
								
								psLoan.setDate(14,null);
	
								psLoan.executeUpdate();
			
		} catch (Exception ex) {
			// TODO handle exception
			throw new ServletException(ex.getMessage());
		}	

	}
	
	public LoanInfo getLoanApplNo(String username) throws ClassNotFoundException
	{	
		LoanInfo loanApplicationNo=null;
		try (Connection con= connect();	
		PreparedStatement ps=jdbcConnection.prepareStatement("SELECT max(loanapplno) as loanapplno  from LOANINFO where username=?")){
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();						
			if(rs.next())
			{
				 loanApplicationNo= new LoanInfo();
				loanApplicationNo.setApplno(rs.getString("loanapplno"));								
				
				//System.out.println("gshjgjhsgdhjfgdsj" + existingUser.getPassword());
			}					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return loanApplicationNo;		
		
	}
	public LoanInfo getLoanDetail(String username,String loanAppNo1) throws ClassNotFoundException
	{	
		LoanInfo loaninfo1=new LoanInfo() ;
		//System.out.println("gshjgjhsgdhjfgdsj1" + loaninfo1.getApplno());
		try (Connection con= connect();	
		PreparedStatement ps2=jdbcConnection.prepareStatement("SELECT * from LOANINFO where username=? and loanapplno=?")){
			ps2.setString(1, username);
			ps2.setString(2, loanAppNo1);
			
			ResultSet rs=ps2.executeQuery();						
			if(rs.next())
			{ //add date
				
				//loaninfo1= new LoanInfo();
				loaninfo1.setApplno(rs.getString("loanapplno"));	
				loaninfo1.setPurpose(rs.getString("loanpurpose"));	
				loaninfo1.setAmtrequest(Integer.parseInt(rs.getString("loanamtrequest")));	
				loaninfo1.setBstructure(rs.getString("loanbstructure"));	
				loaninfo1.setBindicator(rs.getString("loanbindicator"));	
				loaninfo1.setTaxindicator(rs.getString("loantindicator"));	
				loaninfo1.setAddress(rs.getString("loanaddress"));	
				loaninfo1.setEmail(rs.getString("loanemail"));	
				loaninfo1.setMobile(rs.getString("loanmobile"));	
				loaninfo1.setStatus(rs.getString("loanstaus"));							
				loaninfo1.setDoa(rs.getString("loandoa"));
				
			}					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return loaninfo1;		
		
	}
	public void updateLoanInfo(LoanInfo loan,String userName) throws ClassNotFoundException, ServletException
	{		
		
		try (Connection con= connect();							    	
				PreparedStatement psLoan=jdbcConnection.prepareStatement("Update loaninfo set loanpurpose=? , loanamtrequest=? , loanbstructure=? , loanbindicator=? ,loantindicator=?, loanaddress=? ,	loanemail=? , loanmobile=? , loanstaus=? ,loanupdatedao=?  where  loanapplno=?")){
				
				java.util.Date today = new java.util.Date();
				java.sql.Date now= new java.sql.Date(today.getTime());
				DateFormat df = new SimpleDateFormat("YYYYMMDDHH24mmss");
							
				String status="OPEN";				
				loan.setStatus(status);;
				System.out.println("1:"+userName );
							//	psLoan.setString(1, userName);															
								psLoan.setString(1, loan.getPurpose());									
								psLoan.setInt(2, loan.getAmtrequest());															
								psLoan.setString(3, loan.getBstructure());
								psLoan.setString(4, loan.getBindicator());
								psLoan.setString(5, loan.getTaxindicator());
								psLoan.setString(6, loan.getAddress());
								psLoan.setString(7, loan.getEmail());
								psLoan.setString(8, loan.getMobile());
								psLoan.setString(9, loan.getStatus());
								//psLoan.setDate(10,now);
								psLoan.setDate(10,now);
								psLoan.setString(11, loan.getApplno());										
								
								psLoan.executeUpdate();
								
			
		} catch (Exception ex) {
			// TODO handle exception
			throw new ServletException(ex.getMessage());
		}	

	}
	public List<LoanInfo> getAll() throws ServletException 
	{
		
		List<LoanInfo> loanInfo=new ArrayList<LoanInfo>();
		try (Connection con= connect();	
				PreparedStatement psGetALL=jdbcConnection.prepareStatement("SELECT Username,loanapplno ,loanpurpose,loanamtrequest,"
						+ "loanbstructure,loanbindicator,loantindicator ,loanaddress ,loanemail ,loanmobile ,loanstaus,loandoa "
						+ ",loanupdatedao,admincomment from LOANINFO ")){
					
			ResultSet rs =psGetALL.executeQuery();
			while(rs.next())
			{
				LoanInfo loanInfoGetAll = new LoanInfo();
				loanInfoGetAll.setApplno(rs.getString(2));	
				loanInfoGetAll.setPurpose(rs.getString(3));
				loanInfoGetAll.setAmtrequest(rs.getInt(4));
				loanInfoGetAll.setBstructure(rs.getString(5));
				loanInfoGetAll.setBindicator(rs.getString(6));
				loanInfoGetAll.setTaxindicator(rs.getString(7));
				loanInfoGetAll.setAddress(rs.getString(9));
				loanInfoGetAll.setEmail(rs.getString(10));
				loanInfoGetAll.setMobile(rs.getString(11));
				loanInfoGetAll.setStatus(rs.getString(12));
				loanInfoGetAll.setDoa(rs.getString(13));
				
				loanInfo.add(loanInfoGetAll);
				
			}
		}catch(Exception ex)
		{
			throw new ServletException(ex.getMessage());
		}
			
	
		return loanInfo;
		
	}
	
	public void updateLoanStatus(String loanapplno, String status) throws ClassNotFoundException, ServletException
	{		
		
		try (Connection con= connect();							    	
				PreparedStatement psLoan=jdbcConnection.prepareStatement("Update loaninfo set loanstaus=? where  loanapplno=?")){
				
								
								psLoan.setString(1, status);								
								psLoan.setString(2,loanapplno);									
								psLoan.executeUpdate();
								
			
		} catch (Exception ex) {
			// TODO handle exception
			throw new ServletException(ex.getMessage());
		}	
	
	}
	public ApprovedLoan AdmingetLoanDetail(String loanemi) throws ClassNotFoundException
	{	
		ApprovedLoan loanemio=new ApprovedLoan() ;
		//System.out.println("gshjgjhsgdhjfgdsj1" + loaninfo1.getApplno());
		try (Connection con= connect();	
		PreparedStatement ps2=jdbcConnection.prepareStatement("SELECT * from LOANEMIINFO where applno=?")){
			ps2.setString(1, loanemi);		
			ResultSet rs=ps2.executeQuery();						
			if(rs.next())
			{ //add date
				
				loanemio.setApplno(rs.getString("applno"));	
				loanemio.setAmotsanctioned(rs.getInt("amotsanctioned"));
				loanemio.setInterest(rs.getDouble("interestRate"));
				loanemio.setLoanterm(rs.getInt("loanterm"));
				loanemio.setPsd(rs.getDate("psd"));
				loanemio.setLcd(rs.getDate("lcd"));
				loanemio.setEmi(rs.getInt("emi"));
				
			}					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return loanemio;		
		
	}
	
	
	
	
public LoanInfo add(LoanInfo loanInfoGetAll)
	{		
		if(loanInfoGetAll!=null)
		{
			try (Connection con= connect();	
					PreparedStatement psGetALL=jdbcConnection.prepareStatement("Insert into LOANINFO (loanapplno ,loanpurpose,"
							+ "loanamtrequest,"
							+ "loanbstructure,loanbindicator,loantindicator ,loanaddress ,loanemail ,loanmobile ,loanstaus,loandoa)"
							+ "values(?,?,?,?,?,?,?,?,?,?,?)" ))
							{
						
				psGetALL.setString(1,loanInfoGetAll.getApplno());
				psGetALL.setString(2,loanInfoGetAll.getPurpose());
				psGetALL.setInt(3,loanInfoGetAll.getAmtrequest());
				psGetALL.setString(4,loanInfoGetAll.getBstructure());
				psGetALL.setString(5,loanInfoGetAll.getBindicator());
				psGetALL.setString(6,loanInfoGetAll.getTaxindicator());
				psGetALL.setString(7,loanInfoGetAll.getAddress());
				psGetALL.setString(4,loanInfoGetAll.getEmail());
				psGetALL.setString(5,loanInfoGetAll.getMobile());
				psGetALL.setString(6,loanInfoGetAll.getStatus());
				psGetALL.setString(7,loanInfoGetAll.getDoa());
				
				psGetALL.executeUpdate();
							}
							catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}	
		
		
	}
		return loanInfoGetAll;
		

}
}


	
			
		
	
	

