import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ChangePwdFailure extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");

		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Change Password</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		HttpSession session=request.getSession(false);

		if(session!=null){    
        String n=(String)session.getAttribute("uname");
        String p=null;
		boolean status=false;
		try{
//Body
		//sidebar
		out.print("<body>");
		out.print("	<div class=\"wrapper\">");
		out.print("    <div class=\"sidebar\">");
		out.print("        <h2>TEST YOU</h2>");
		out.print("        <ul>");
		out.print("            <li><form action=\"StudentHome\" method=\"post\"><input type=\"submit\" value=\"Home\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"StudentProfile\" method=\"post\"><input type=\"submit\" value=\"Profile\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"StudentResults\" method=\"post\"><input type=\"submit\" value=\"Results\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"StudentExams\" method=\"post\"><input type=\"submit\" value=\"Exams\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"StudentChangePwd\" method=\"post\"><input type=\"submit\" value=\"Change Password\" class=\"btn-link\"></form></li>");
		out.print("        </ul>");
        
        //Main Content
		out.print("    </div>");
		out.print("    <div class=\"main_content\">");
		out.print("        <div class=\"header\">");
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\" name=\"Logout\" id=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center>");
		
		
        session.setAttribute("uname",n);
		out.print("<font color=\"Red\">Sorry Old Password is Incorrect</font>");
		RequestDispatcher rd=request.getRequestDispatcher("StudentChangePwd");
		rd.include(request,response);
        
		out.print("</center></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");

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
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Student Change Password</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

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
		//sidebar
		out.print("<body>");
		out.print("	<div class=\"wrapper\">");
		out.print("    <div class=\"sidebar\">");
		out.print("        <h2>TEST YOU</h2>");
		out.print("        <ul>");
		out.print("            <li><a href=\"StudentHome.html\">Home</a></li>");
		out.print("            <li><a href=\"#\">Profile</a></li>");
		out.print("            <li><a href=\"StudentResults\">Results</a></li>");
		out.print("            <li><a href=\"StudentExams\">Exams</a></li>");
		out.print("            <li><a class=\"active\" href=\"StudentChangePwd\">Change Password</a></li>");
		out.print("        </ul>"); 
        
        //Main Content
		out.print("    </div>");
		out.print("    <div class=\"main_content\">");
		out.print("        <div class=\"header\">");
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\" name=\"Logout\" id=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center>");
		
		
        session.setAttribute("uname",n);
		out.print("<font color=\"Red\">Sorry Old Password is Incorrect</font>");
		RequestDispatcher rd=request.getRequestDispatcher("StudentChangePwd");
		rd.include(request,response);
        
		out.print("</center></div>");
		out.print("</div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");

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
