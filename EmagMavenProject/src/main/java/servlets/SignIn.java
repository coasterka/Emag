package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import engine.Validate;
import exceptions.EmagInvalidArgumentException;
import exceptions.ValidationException;

public class SignIn extends HttpServlet {

	private static final String RELOAD_PAGE_SCRIPT = "<script>document.location = \'./signIn.html\'</script>";
	private static final String WRONG_USERNAME_PASSWORD_MESSAGE = "<script>alert(\'Username or password incorrect!\')</script>";
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		RequestDispatcher rd = null;
		
		if (Validate.checkUserOnSignIn(username, password)) {			
			try {
				if (Validate.isUserAdminById()) {
					HttpSession session = request.getSession();
					session.setAttribute("name", username);
					out.println("<script>alert(\"Welcome, " + username + "!\");</script>");
//					response.sendRedirect("adminPages/admin.html");
					out.println("<script>document.location = \"adminPages/admin.html\";</script>");
				}
				else {
					rd = request.getRequestDispatcher("CheckUserResult");
					rd.forward(request, response);
				}
			} catch (ValidationException | EmagInvalidArgumentException e) {
				e.printStackTrace();
			}
			
		} else {
			out.println(WRONG_USERNAME_PASSWORD_MESSAGE);
			out.println(RELOAD_PAGE_SCRIPT);
		}
	}
}
