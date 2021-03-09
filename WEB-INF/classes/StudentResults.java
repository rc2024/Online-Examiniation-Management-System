import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentResults extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Student Results</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		
		String cid=null;
		String cname=null;		
		int i=0;
		HttpSession session=request.getSession(false);  

		if(session!=null){  
        String n=(String)session.getAttribute("uname"),xz=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement pst=con.prepareStatement("select ID from STUDENT_ONLINE WHERE USERNAME=?");
			pst.setString(1,n);
			ResultSet rsT=pst.executeQuery();
			rsT.next();
			String sid=rsT.getString(1);

			PreparedStatement ps=con.prepareStatement("select R.MARKS, S.COURSE_NAME, R.COURSEID from RESULTS R, COURSES S WHERE R.COURSEID=S.COURSE_ID AND R.ID=?");
			ps.setString(1,sid);
			ResultSet rs=ps.executeQuery();
			ResultSet rst=null;
			pst=con.prepareStatement("select count(question) from QUESTIONS Q, COURSES C WHERE Q.COURSEID=C.COURSE_ID AND Q.COURSEID=?");
			int ss=0;
			double p=0.0;
			int m=0;
			i=1;
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
        String abz=null;
        //Main Content
		out.print("    </div>");
		out.print("    <div class=\"main_content\">");
		out.print("        <div class=\"header\">");
		out.print("        	<form action=\"logout\"><p align=\"right\">Welcome,"+n+"&nbsp&nbsp&nbsp&nbsp <input type=\"submit\" value=\"Logout\" name=\"Logout\" id=\"Logout\"></p></form>");
		out.print("        </div>  ");
		out.print("        <div class=\"info\"><center>");
        out.print("<table border=\"1\" align=\"center\"><tr><th>Sl. No.</th><th>Subject</th><th>Marks</th><th>Total Marks</th><th>Percentage</th></tr>");
		while(rs.next())
		{	
			abz=rs.getString(3);
			pst.setString(1,abz);
			rst=pst.executeQuery();
			rst.next();
			m=rs.getInt(1);
			ss=rst.getInt(1);
			p=((m*100.0)/ss);
			out.print("<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(1)+"</td><td>"+ss+"</td><td>"+p+"</td></tr>");
			i++;
		}
        out.print("</table><br><br>");
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
