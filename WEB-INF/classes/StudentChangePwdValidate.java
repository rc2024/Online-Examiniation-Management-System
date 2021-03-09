import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentChangePwdValidate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");

		PrintWriter out = response.getWriter();
		

		HttpSession session=request.getSession(false);  

		if(session!=null){  
        String n=(String)session.getAttribute("uname");
        String p=null;
		boolean status=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	
			PreparedStatement ps=con.prepareStatement("SELECT PASSWORD FROM Student_Online where USERNAME=?");
			ps.setString(1,n);
			
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			p=(String)rs.getString(1);
//Body
		
		if(oldpass.equals(p))
		{
			PreparedStatement psupdate=con.prepareStatement("UPDATE Student_Online SET PASSWORD=? where USERNAME=?");
			psupdate.setString(1,newpass);
			psupdate.setString(2,n);
			psupdate.executeQuery();

        	session.setAttribute("uname",n);
        	out.println("<font color=\"Green\">Password Changed Successful</font>");
			RequestDispatcher rd=request.getRequestDispatcher("ChangePwdSuccess");
			rd.forward(request,response);
		}
		else{

        	session.setAttribute("uname",n);
			RequestDispatcher rd=request.getRequestDispatcher("ChangePwdFailure");
			rd.include(request,response);
		}


		}
		catch(Exception e){System.out.println(e);out.println(e);}
		out.close();
	}
	else
	{
		out.print("<font color=\"red\">Please login first</font>");  
        response.sendRedirect("Student_Login.html");
	}

	}

public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");

		PrintWriter out = response.getWriter();
		

		HttpSession session=request.getSession(false); 

		if(session!=null){     
        String n=(String)session.getAttribute("uname");
        String p=null;
		boolean status=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	
			PreparedStatement ps=con.prepareStatement("SELECT PASSWORD FROM Student_Online where USERNAME=?");
			ps.setString(1,n);
			
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			p=(String)rs.getString(1);
//Body
		
		if(oldpass.equals(p))
		{
			PreparedStatement psupdate=con.prepareStatement("UPDATE Student_Online SET PASSWORD=? where USERNAME=?");
			psupdate.setString(1,newpass);
			psupdate.setString(2,n);
			psupdate.executeQuery();

        	session.setAttribute("uname",n);
        	out.println("<font color=\"Green\">Password Changed Successful</font>");
			RequestDispatcher rd=request.getRequestDispatcher("StudentChangePwdSuccess");
			rd.forward(request,response);
		}
		else{

        	session.setAttribute("uname",n);
			RequestDispatcher rd=request.getRequestDispatcher("StudentChangePwdFailure");
			rd.include(request,response);
		}


		}
		catch(Exception e){System.out.println(e);out.println(e);}
		out.close();
	}
	else
	{
		out.print("<font color=\"red\">Please login first</font>");  
        response.sendRedirect("Student_Login.html");
	}


	}

}
