package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import exceptions.CategoryDAOException;
import exceptions.EmagInvalidArgumentException;
import products.Product;

//@WebServlet("ListProducts")
public class ListOfAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Product> allProducts;
		try {
			allProducts = (new ProductDAO()).getAllProducts();
			out.println("<table style=\"border: 2px solid black; border-collapse: collapse;\">");
			out.println("<tr><td>Brand</td><td>Model</td><td>Color</td><td>Characteristics</td><td>Price</td></tr>");
			for (Product product : allProducts) {
				out.println("<tr>");
				out.println("<td style=\"border: 1px solid black;\">" + product.getBrand().toString() + "</td>");
				out.println("<td style=\"border: 1px solid black;\">" + product.getModel().toString() + "</td>");
				out.println("<td style=\"border: 1px solid black;\">" + product.getColor().toString() + "</td>");
				out.println("<td style=\"border: 1px solid black;\">" + product.getCharacteristics() + "</td>");
				out.println("<td style=\"border: 1px solid black;\">" + product.getPrice() + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (CategoryDAOException | EmagInvalidArgumentException e) {
			e.printStackTrace();
		}
	}

}
