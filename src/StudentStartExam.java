import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentStartExam extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Student Change Password</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=1;
		HttpSession session=request.getSession(false); 

		if(session!=null){


        String n=(String)session.getAttribute("uname");
        String cid=(String)request.getParameter("course");
        String sid=(String)session.getAttribute("sid");
		try{
			session.setAttribute("course",cid);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select QUESTION,OPTION1,OPTION2,OPTION3,OPTION4 from QUESTIONS WHERE COURSEID=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
		//sidebar
		out.print("<body>");
		out.print("	<div class=\"wrapper\">");
		out.print("    <div class=\"sidebar\">");
		out.print("        <h2>TEST YOU</h2>");
		out.print("        <ul>");
		out.print("            <li><form action=\"StudentHome\" method=\"post\"><input type=\"submit\" value=\"Home\" class=\"btn-link\" disabled=true></form></li>");
		out.print("            <li><form action=\"StudentProfile\" method=\"post\"><input type=\"submit\" value=\"Profile\" class=\"btn-link\" disabled=true></form></li>");
		out.print("            <li><form action=\"StudentResults\" method=\"post\"><input type=\"submit\" value=\"Results\" class=\"btn-link\" disabled=true></form></li>");
		out.print("            <li><form action=\"StudentExams\" method=\"post\"><input type=\"submit\" value=\"Exams\" class=\"btn-link\" disabled=true></form></li>");
		out.print("            <li><form action=\"StudentChangePwd\" method=\"post\"><input type=\"submit\" value=\"Change Password\" class=\"btn-link\" disabled=true></form></li>");
		out.print("        </ul>");
        
        //Main Content
		out.print("    </div>");
		out.print("    <div class=\"main_content\">");
		out.print("        <div class=\"header\">");
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center>");

		out.print("<form action=\"StudentSubmitExam\" method=\"post\">");
		while(rs.next())
		{
			out.print(i+").&nbsp"+rs.getString(1)+"<br>");
			out.print("&nbsp&nbsp&nbsp&nbsp<input type=\"radio\" name=\"qn"+i+"\" value=\""+rs.getString(2)+"\">&nbsp"+rs.getString(2)+"&nbsp&nbsp<input type=\"radio\" name=\"qn"+i+"\" value=\""+rs.getString(3)+"\">&nbsp"+rs.getString(3)+"&nbsp&nbsp<input type=\"radio\" name=\"qn"+i+"\" value=\""+rs.getString(4)+"\">&nbsp"+rs.getString(4)+"&nbsp&nbsp<input type=\"radio\" name=\"qn"+i+"\" value=\""+rs.getString(5)+"\">&nbsp"+rs.getString(5)+"&nbsp&nbsp<br><br><br>");
			i++;
		}
        out.print("<br><br><input type=\"Submit\" Value=\"Submit Answers\"></form>  ");
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
