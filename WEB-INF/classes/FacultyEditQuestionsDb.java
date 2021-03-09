import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyEditQuestionsDb extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession(false);

		if(session!=null){ 

        String n=(String)session.getAttribute("uname");
		String cid=(String)session.getAttribute("courseid");
		String oldqn=(String)request.getParameter("oldqn");
		String qn=(String)request.getParameter("qn");
		String op1=(String)request.getParameter("op1");
		String op2=(String)request.getParameter("op2");
		String op3=(String)request.getParameter("op3");
		String op4=(String)request.getParameter("op4");
		String cop=(String)request.getParameter("cop");

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

			PreparedStatement ps=con.prepareStatement("UPDATE QUESTIONS SET QUESTION=?,OPTION1=?,OPTION2=?,OPTION3=?,OPTION4=?,COPTION=? where courseid=? AND QUESTION=?");
			ps.setString(1,qn);
			ps.setString(2,op1);
			ps.setString(3,op2);
			ps.setString(4,op3);
			ps.setString(5,op4);
			ps.setString(6,cop);
			ps.setString(7,cid);
			ps.setString(8,oldqn);
			ps.executeQuery();
            request.getRequestDispatcher("FacultyViewQuestions").include(request, response); 
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
