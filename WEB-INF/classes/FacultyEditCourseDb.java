import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyEditCourseDb extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession(false);

		if(session!=null){ 

        String n=(String)session.getAttribute("uname");
		String oldcid=(String)session.getAttribute("course");
		String cid=(String)request.getParameter("cid");
		String cname=(String)request.getParameter("cname");
		String mpq=(String)request.getParameter("mpq");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

			PreparedStatement ps=con.prepareStatement("UPDATE COURSES SET COURSE_NAME=?, COURSE_ID=?, MARKSPERQN=? where course_id=?");
			ps.setString(1,cname);
			ps.setString(2,cid);
			ps.setString(3,mpq);
			ps.setString(4,oldcid);
			ps.executeQuery();
			
		out.print("<font color=\"green\">Course Updated Successfully");
            request.getRequestDispatcher("FacultyViewCourse").include(request, response); 
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
