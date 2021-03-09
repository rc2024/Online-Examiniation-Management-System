import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyUploadExam extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Faculty Upload Exam</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=0;
		HttpSession session=request.getSession(false);
		if(session!=null){   
        String n=(String)session.getAttribute("uname");
		try{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select COURSE_NAME, COURSE_ID from COURSES");
			ResultSet rs=ps.executeQuery();

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
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center><form method=\"post\" action=\"FacultyUploadExamDb\">");
        out.print("Select Subject:&nbsp&nbsp");out.print("<select id=\"course\" name=\"course\" autofocus >");
        while(rs.next())
        {
        	out.print("<option value=\""+rs.getString(2)+"\">"+rs.getString(1)+"</option>");
    	}
    	out.print("<input type=\"submit\" name=\"submit\" value=\"Upload\">");
		out.print("</select></form></center></div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		}
		catch(Exception e){System.out.println(e);out.println(e);}
		}		
		else
		{
			out.print("Please login first");  
            request.getRequestDispatcher("Faculty_Login.html").include(request, response); 
		}
		out.close();
	}
}
