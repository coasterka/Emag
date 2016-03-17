package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import exceptions.BrandDAOException;
import exceptions.CategoryDAOException;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;
import exceptions.ProductDAOException;
import products.Product;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			List<Product> products = new ArrayList<Product>(new ProductDAO().getProductByBrand(request.getParameter("search")));
			pw.println("<!DOCTYPE html> "
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\" />"
					+ "<title>Sign in :: IT Talents</title>"
					+ "<link type=\"image/x-icon\" rel=\"icon\" href=\"images/emagIcon.ico\" />"
					+ "<link type=\"text/css\" rel=\"stylesheet\" href=\"styles/signIn.css\" />"
					+ "	<link type=\"text/css\" rel=\"stylesheet\" href=\"styles/global.css\" />"
					+ "</head>"
					+ "<body>"
					+ "<div id=\"wrapper\">"
					+ "<header>"
					+ "<div id=\"siteTitle\">"
					+ "<p>Emag Project - IT Talents</p>"
					+ "<div id=\"userActions\">"
					+ "<a href=\"signIn.html\">Sign in</a><span>/</span><a href=\"signUp.html\">Sign up</a>"
					+ "</div>"
					+ "</div>"
					+ "<ul>"
					+ "<li><a href=\"index.html\">Home</a></li>"
					+ "<li><a href=\"products.html\">Products</a></li>"
					+ "<li><a href=\"contacts.html\">Contacts</a></li>"
					+ "<li><a href=\"aboutUs.html\">About us</a></li>"
					+ "<li><a href=\"adminPages/admin.html\" id=\"adminMenu\">Admin</a></li>"
					+ "<li><form action=\"Search\" method=\"post\">"
					+ "<input type=\"search\" results onsearch=\"javascript:alert('SEARCH: ' + this.value);\" placeholder=\"Search products...\"  name=\"search\"/>"
					+ "<input type=\"submit\" value=\"Search\" />"
					+ "</form></li>"
					+ "</ul>"
					+ "</header>"
					+ "<div id=\"main\">"
					+ "	<aside>"
					+ "<ul id=\"categories\">"
					+ "<li><a href=\"#\">Mobile devices</a></li>"
					+ "<li><a href=\"#\">Television sets</a></li>"
					+ "<li><a href=\"#\">Appliances</a></li>"
					+ "<li><a href=\"shoppingCart.html\">Shopping cart</a></li>"
					+ "<li><a href=\"#\">Q &amp; A</a></li>"
					+ "</ul>"
					+ "<div id=\"promotionsBanner\">"
					+ "<a href=\"#\"> <img alt=\"promotions\" src=\"images/banner1.jpg\" />"
					+ "</a>"
					+ "</div>"
					+ "</aside>"
					+ "<section>"
					+ "<div>");
									pw.println("<table style=\"border: 2px solid black; border-collapse: collapse;\">");
						pw.println("<tr><td>Category</td><td>Price</td><td>Brand</td><td>Model</td></tr>");
						for (Product product : products) {
							pw.println("<tr>");
							pw.println("<td style=\"border: 1px solid black;\">" + product.getCategory().getName() + "</td>");
							pw.println("<td style=\"border: 1px solid black;\">" + product.getPrice() + "</td>");
							pw.println("<td style=\"border: 1px solid black;\">" + product.getBrand() + "</td>");
							pw.println("<td style=\"border: 1px solid black;\">" + product.getModel() + "</td>");
							pw.println("</tr>");
						}
						pw.println("</table>");
						pw.println("</div>"
								+ "</section>"
								+ "</div>"
								+ "	<footer>"
								+ "	<ul>"
								+ "		<li><a href=\"index.html\">Home</a></li>"
								+ "		<li><a href=\"products.html\">Products</a></li>"
								+ "<li><a href=\"products.html\">Contacts</a></li>"
								+ "<li><a href=\"products.html\">About us</a></li>"
								+ "<li><a href=\"http://ittalents.bg/\" target=\"_blank\">IT"
								+ "Talents</a></li>"
								+ "</ul>"
								+ "<p>&copy; Emag Project - IT Talents Season 5 March 2016</p>"
								+ "</footer>"
								+ "	</div>"
								+ "	</body>"
								+ "</html>");
		} catch (EmagInvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoryDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrandDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ColorDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
