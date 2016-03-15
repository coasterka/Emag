package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import engine.Validate;
import exceptions.EmagInvalidArgumentException;
import exceptions.ValidationException;

public class SignIn extends HttpServlet {

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
					response.sendRedirect("adminPages/admin.html");
//					rs.include(request, response);
				}
				else {
					rd = request.getRequestDispatcher("CheckUserResult");
					rd.forward(request, response);
				}
			} catch (ValidationException | EmagInvalidArgumentException e) {
				e.printStackTrace();
			}
			
		} else {
			out.println("<h1 style=\"color: #FFF\" align=\"center\">Username or Password incorrect!</h1>");
			rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
	}

}
