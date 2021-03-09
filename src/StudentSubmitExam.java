import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentSubmitExam extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Student Submit Exam</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=1;
		HttpSession session=request.getSession(false); 

		if(session!=null){ 		 
        String n=(String)session.getAttribute("uname");
        String cid=(String)session.getAttribute("course");
        String sid=(String)session.getAttribute("sid");
        String ans=null;
        String ques=null;
        String m=null;
        int marks=0;
		try{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("SELECT QUESTION, COPTION from QUESTIONS WHERE COURSEID=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ans=(String)request.getParameter("qn"+i+"");
				ques=rs.getString(1);
				ps=con.prepareStatement("insert into ANSWERS values(?,?,?,?)");
				ps.setString(1,ques);
				ps.setString(2,ans);
				ps.setString(3,sid);
				ps.setString(4,cid);

				ps.executeQuery();
				m=rs.getString(2);
				if(ans.equals(m))
					marks++;
				i++;
			}

				ps=con.prepareStatement("SELECT MARKSPERQN from COURSES WHERE COURSE_ID=?");
				ps.setString(1,cid);
				ResultSet rst=ps.executeQuery();
				rst.next();
				int multi=rst.getInt(1);
				marks=marks*multi;
				ps=con.prepareStatement("INSERT INTO RESULTS values(?,?,?)");
				ps.setString(1,sid);
				ps.setString(2,cid);
				ps.setInt(3,marks);
				ps.executeQuery();

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
		out.print("<font color=\"green\">Answers submitted Successfully. You can now check your result by Going to the results section</font>");
        out.print("<br><br>");
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
