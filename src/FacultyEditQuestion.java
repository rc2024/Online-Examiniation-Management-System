import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyEditQuestion extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Faculty Add Question</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");
		

		int i=0;
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
			String qn=(String)request.getParameter("qn");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select * from QUESTIONS where COURSEID=? AND QUESTION=?");
			ps.setString(1,cid);
			ps.setString(2,qn);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String op1=rs.getString(2), op2=rs.getString(3), op3=rs.getString(4), op4=rs.getString(5), cop=rs.getString(6);

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
		out.print("        <div class=\"info\"><center><form method=\"post\" action=\"FacultyEditQuestionsDb\">");
		out.print("<input type=\"hidden\" name=\"oldqn\" value=\""+qn+"\">");
        out.print("<br>Question:&nbsp&nbsp");out.print("<textarea name=\"qn\" autofocus >"+qn+"</textarea>");
        out.print("<br>Option 1:&nbsp&nbsp");out.print("<input type=\"text\" name=\"op1\" value=\""+op1+"\">");
        out.print("<br>Option 2:&nbsp&nbsp");out.print("<input type=\"text\" name=\"op2\" value=\""+op2+"\">");
        out.print("<br>Option 3:&nbsp&nbsp");out.print("<input type=\"text\" name=\"op3\" value=\""+op3+"\">");
        out.print("<br>Option 4:&nbsp&nbsp");out.print("<input type=\"text\" name=\"op4\" value=\""+op4+"\">");

        if(cop.equals(op1))
        {
        	out.print("<br> Correct Option:&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op1+"\" checked>"+op1+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op2+"\">"+op2+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op3+"\">"+op3+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op4+"\">"+op4+"&nbsp&nbsp");
        }
        else if(cop.equals(op2))
        {
        	out.print("<br> Correct Option:&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op1+"\">"+op1+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op2+"\" checked>"+op2+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op3+"\">"+op3+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op4+"\">"+op4+"&nbsp&nbsp");
        }
        else if(cop.equals(op3))
        {
        	out.print("<br> Correct Option:&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op1+"\">"+op1+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op2+"\">"+op2+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op3+"\" checked>"+op3+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op4+"\">"+op4+"&nbsp&nbsp");
        }
        else if(cop.equals(op4))
        {
        	out.print("<br> Correct Option:&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op1+"\">"+op1+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op2+"\">"+op2+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op3+"\">"+op3+"&nbsp&nbsp");
        out.print("<input type=\"radio\" name=\"cop\" value=\""+op4+"\" checked>"+op4+"&nbsp&nbsp");
        }
    	out.print("<br><br><input type=\"submit\" name=\"submit\" value=\"Change\">");
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