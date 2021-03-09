import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyDeleteAllQuestions extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession(false);
		String cid=(String)request.getParameter("courseid");

		if(session!=null){ 

        String n=(String)session.getAttribute("uname");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
				PreparedStatement ps=con.prepareStatement("DELETE FROM QUESTIONS WHERE COURSEID=?");
				ps.setString(1,cid);
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
