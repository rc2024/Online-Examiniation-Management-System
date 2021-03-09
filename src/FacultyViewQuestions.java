import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyViewQuestions extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Faculty View Course</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=1;
		HttpSession session=request.getSession(false);
		if(session!=null){   
        String n=(String)session.getAttribute("uname");
        String cid=null;

        if((String)session.getAttribute("courseid")==null)
        {
        	cid=(String)request.getParameter("course");
        	session.setAttribute("courseid",cid);
        }
        else
        {
        	cid=(String)session.getAttribute("courseid");

        }
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select * from Questions where courseid=?");
			ps.setString(1,cid);
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
		out.print("        <div class=\"info\"><center>");
		out.print("<br><form action=\"FacultyDeleteAllQuestions\" method=\"post\"><input type=\"hidden\" name=\"courseid\" value=\""+cid+"\"><input type=\"submit\" value=\"Delete All Questions\" class=\"btn-link-table\"></form></td></tr>");
		out.print("<br><table border=\"1\" align=\"center\"><tr align=\"center\"><th>Sl. No.</th><th>Question</th><th>OPTION 1</th><th>OPTION 2</th><th>OPTION 3</th><th>OPTION 4</th><th>CORRECT OPTION</th><th>Edit</th><th>Delete</th></tr>");
		while(rs.next())
		{	
			out.print("<tr align=\"center\"><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
			out.print("<td><form action=\"FacultyEditQuestion\" method=\"post\"><input type=\"hidden\" name=\"qn\" value=\""+rs.getString(1)+"\"><input type=\"hidden\" name=\"courseid\" value=\""+rs.getString(7)+"\"><input type=\"submit\" value=\"Edit\" class=\"btn-link-table\"></form></td>");
			out.print("<td><form action=\"FacultyDeleteQuestion\" method=\"post\"><input type=\"hidden\" name=\"qn\" value=\""+rs.getString(1)+"\"><input type=\"hidden\" name=\"courseid\" value=\""+rs.getString(7)+"\"><input type=\"submit\" value=\"Delete\" class=\"btn-link-table\"></form></td></tr>");
			i++;
		}
        out.print("</table><br><br>");

		out.print("</center></div>");
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
