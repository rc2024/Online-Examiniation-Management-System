import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyChangePwd extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Change Password</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print(" <script src=\"JS/changepwd.js\"> </script>");
		out.print("</head>");
		

		int i=0;
		HttpSession session=request.getSession(false); 

		if(session!=null){   
        String n=(String)session.getAttribute("uname");
        String user[]=null;
		boolean status=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

//Body
		//sidebar
		out.print("<body>");
		out.print("	<div class=\"wrapper\">");
		out.print("    <div class=\"sidebar\">");
		out.print("        <h2>TEST YOU</h2>");
		out.print("        <ul>");
		out.print("            <li><form action=\"FacultyHome\" method=\"post\"><input type=\"submit\" value=\"Home\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyProfile\" method=\"post\"><input type=\"submit\" value=\"Profile\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyAddCourse\" method=\"post\"><input type=\"submit\" value=\"Add Course\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyViewCourse\" method=\"post\"><input type=\"submit\" value=\"View Course\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyAddQuestion\" method=\"post\"><input type=\"submit\" value=\"Add Questions\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyQuestions\" method=\"post\"><input type=\"submit\" value=\"View Questions\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyUploadExam\" method=\"post\"><input type=\"submit\" value=\"Upload Exam\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyRemoveExam\" method=\"post\"><input type=\"submit\" value=\"Remove Exam\" class=\"btn-link\"></form></li>");
		out.print("            <li><form action=\"FacultyChangePwd\" method=\"post\"><input type=\"submit\" value=\"Change Password\" class=\"btn-link\"></form></li>");
		out.print("        </ul>");
        
        //Main Content
		out.print("    </div>");
		out.print("    <div class=\"main_content\">");
		out.print("        <div class=\"header\">");
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\" name=\"Logout\" id=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center><form action=\"FacultyChangePwdValidate\" method=\"post\">");
        out.print("Enter Old Password:");
        out.print("<input type=\"password\" name=\"oldpass\" id=\"oldpass\">");
        out.print("<br>Enter New Password:");
        out.print("<input type=\"password\" name=\"newpass\" id=\"npwd\" onblur=\"validPassword()\"><span id=\"snpwd\"></span>");
        out.print("<br>Confirm New Password:");
        out.print("<input type=\"password\" name=\"confpass\" id=\"cnfnpwd\" onblur=\"validConfirmPassword()\"><span id=\"scnfnpwd\"></span>");
        out.print("<br><input type=\"Submit\" id=\"cnfm\" value=\"Change Password\"></form>  ");
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
        response.sendRedirect("Faculty_Login.html");
	}

	}
}
