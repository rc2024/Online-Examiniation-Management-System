import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyDeleteQuestion extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession(false);
		String qn=(String)request.getParameter("qn");

		if(session!=null){ 

        String n=(String)session.getAttribute("uname");
        String cid=(String)session.getAttribute("courseid");

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

				PreparedStatement ps=con.prepareStatement("DELETE FROM QUESTIONS WHERE QUESTION=? and COURSEID=?");
				ps.setString(1,qn);
				ps.setString(2,cid);
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
