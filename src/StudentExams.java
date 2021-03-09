import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentExams extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Student Change Password</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=0;
		HttpSession session=request.getSession(false); 

		if(session!=null){   
        String n=(String)session.getAttribute("uname");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select * from EXAMS");
			ResultSet rs=ps.executeQuery();
			ps=con.prepareStatement("select ID from STUDENT_ONLINE WHERE USERNAME=?");
			ps.setString(1,n);
			ResultSet rsT=ps.executeQuery();
			rsT.next();
			String sID=rsT.getString(1);
			session.setAttribute("sid",sID);


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
		out.print("        <div class=\"info\"><center><form action=\"StudentStartExam\" method=\"post\"><select  id=\"course\" name=\"course\">");
        
		while(rs.next())
		{
			out.print("<option value=\""+rs.getString(2)+"\">"+rs.getString(1)+"</option>");
		}
        out.print("</select><br><br><input type=\"Submit\" Value=\"Start Exam\"></form>  ");
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
