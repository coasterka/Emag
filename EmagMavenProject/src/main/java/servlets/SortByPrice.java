package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import exceptions.CategoryDAOException;
import exceptions.EmagInvalidArgumentException;
import products.Product;

/**
 * Servlet implementation class SortByPrice
 */
@WebServlet("/Sort")
public class SortByPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SortByPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		try {
			Set<Product> products = new TreeSet<Product>(new ProductDAO().getAllProducts());
			pw.println("<html> <body>");
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
			pw.println("</body> </html>");
		} catch (EmagInvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoryDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
