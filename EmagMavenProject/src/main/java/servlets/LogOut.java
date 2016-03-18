package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/Logout")
public class LogOut extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("name");
		response.getWriter().println("<script>alert(\"Successful logout - " + username + "!\");</script>");
		session.invalidate();
		response.getWriter().println("<script>document.location = \"index.html\";</script>");
		
//		response.sendRedirect("./");
	}

}
