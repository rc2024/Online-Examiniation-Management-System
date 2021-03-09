import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FacultyDeleteCourse extends HttpServlet {
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

			PreparedStatement pst=con.prepareStatement("select question FROM questions WHERE COURSEID=?");
			pst.setString(1,cid);
			ResultSet rst=pst.executeQuery();
			boolean status=rst.next();


			if(!status)
			{
				PreparedStatement ps=con.prepareStatement("DELETE FROM COURSES WHERE COURSE_ID=?");
				ps.setString(1,cid);
				ps.executeQuery();	
			}
			else
			{
				PreparedStatement pstT=con.prepareStatement("SELECT COURSE_NAME FROM COURSES WHERE COURSE_ID=?");
				pstT.setString(1,cid);
				ResultSet rstT=pstT.executeQuery();
				rstT.next();
				String st=rstT.getString(1);



				PreparedStatement ps=con.prepareStatement("UPDATE RESULTS SET COURSEID=? where COURSEID=?");
				ps.setString(1,st);
				ps.setString(2,cid);
				ps.executeQuery();	
				
				ps=con.prepareStatement("DELETE FROM QUESTIONS WHERE COURSEID=?");
				ps.setString(1,cid);
				ps.executeQuery();	

				ps=con.prepareStatement("DELETE FROM COURSES WHERE COURSE_ID=?");
				ps.setString(1,cid);
				ps.executeQuery();	
			}
			
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
