import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentLogin extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id=null;
		
		String n=request.getParameter("Username");
		String p=request.getParameter("Password");
		
		boolean status=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	
			PreparedStatement ps=con.prepareStatement("select USERNAME, PASSWORD, ID from Student_Online where USERNAME=? AND PASSWORD=?");
			ps.setString(1,n);
			ps.setString(2,p);
	
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			id=rs.getString(3);
			}
		catch(Exception e){System.out.println(e);out.println(e);}

		if(status){
			HttpSession session=request.getSession();  
        	session.setAttribute("uname",n);
        	session.setAttribute("ID",id);
			RequestDispatcher rd=request.getRequestDispatcher("StudentHome");
			rd.forward(request,response);
		}
		else{
			out.print("<font color=\"Red\">Sorry Username or Password is Incorrect</font>");
			RequestDispatcher rd=request.getRequestDispatcher("Student_Login.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}
