import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
{
PrintWriter out=response.getWriter();
response.sendRedirect("index.html");
HttpSession session = request.getSession(false);
session.invalidate();
}
}