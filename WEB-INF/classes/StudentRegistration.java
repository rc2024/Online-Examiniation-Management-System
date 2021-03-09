import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentRegistration extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String uname=request.getParameter("Username");
		String pwd=request.getParameter("Password");
		String college=request.getParameter("collname");
		String email=request.getParameter("Email");
		String phone=request.getParameter("phno");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	
			PreparedStatement ps=con.prepareStatement("insert into Student_Online values(?,?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,id);
			ps.setString(3,uname);
			ps.setString(4,pwd);
			ps.setString(5,college);
			ps.setString(6,email);
			ps.setString(7,phone);
	
			ps.executeQuery();
			out.println("<TITLE>Registration Successful</TITLE>");
			out.println("<STYLE>a {text-decoration: none;} a:link {color: #095484;} a:visited {color: #095484;} a:hover {border-bottom: 1px solid #095484;}h2{border-right-style: solid;}</STYLE>");
			out.print("<center>");
			out.print("<a  href= \"index.html\"><h2 style=\"display: inline;border-left-style: solid;\" class=\"underlineHover\">Home &nbsp&nbsp&nbsp</h2></a>");
  			out.print("<a  href=\"Student_Login.html\"><h2 style=\"display: inline\" class=\"underlineHover\">Student Login &nbsp&nbsp&nbsp</h2></a>");
  			out.print("<a  href=\"Faculty_Login.html\"><h2 style=\"display: inline\" class=\"underlineHover\">Faculty Login &nbsp&nbsp&nbsp</h2></a>");
  			out.print("<a  class=\"active\" href=\"#\"><h2 style=\"display: inline\" class=\"active\">Student Registration &nbsp&nbsp&nbsp</h2></a>");
  			out.print("<a  href=\"Faculty_Registration.html\"><h2 style=\"display: inline\" class=\"underlineHover\">Faculty Registration &nbsp&nbsp&nbsp</h2></a>");
  			out.print("</center><br><hr><br><br>");
			out.print("User Created Successfully");
			}
		catch(Exception e){System.out.println(e);out.println(e);}
		out.close();
	}

}
