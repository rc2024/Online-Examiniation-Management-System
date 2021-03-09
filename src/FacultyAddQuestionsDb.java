import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyAddQuestionsDb extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("		<title>Faculty Add Question</title>");
		out.print("		<link rel=\"stylesheet\"  type=\"text/css\" href=\"CSS/sidemenu.css\">");
		out.print("</head>");

		HttpSession session=request.getSession(false);

		if(session!=null){ 

        String n=(String)session.getAttribute("uname");
		String cid=(String)session.getAttribute("courseid");
		String qn=(String)request.getParameter("qn");
		String op1=(String)request.getParameter("op1");
		String op2=(String)request.getParameter("op2");
		String op3=(String)request.getParameter("op3");
		String op4=(String)request.getParameter("op4");
		String cop=(String)request.getParameter("cop");

		if(cop.equals("op1"))
			cop=op1;
		else if(cop.equals("op2"))
			cop=op2;
		else if(cop.equals("op3"))
			cop=op3;
		else if(cop.equals("op4"))
			cop=op4;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

			PreparedStatement ps=con.prepareStatement("INSERT INTO QUESTIONS VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,qn);
			ps.setString(2,op1);
			ps.setString(3,op2);
			ps.setString(4,op3);
			ps.setString(5,op4);
			ps.setString(6,cop);
			ps.setString(7,cid);
			ps.executeQuery();
			request.getRequestDispatcher("FacultyAddQuestions").include(request, response);  
			out.print("        <div class=\"info\"><center> Question Added Successfully <div></center>");


		}
		catch(Exception e){System.out.println(e);out.println(e);}
	}
	else{  
            out.print("Please login first");  
            request.getRequestDispatcher("Faculty_Login.html").include(request, response);  
        }
		out.close();

	}

}
