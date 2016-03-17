package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import dao.IProductDAO;
import dao.ProductDAO;
import engine.Brand;
import engine.Category;
import engine.Color;
import exceptions.BrandDAOException;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;
import exceptions.ProductDAOException;
import products.Product;


public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GET_PRODUCT_BY_MODEL = "SELECT * FROM products WHERE model = ?;";
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");		
		
		String brand = request.getParameter("brandName").toUpperCase();
		String color = request.getParameter("color").toUpperCase();
		String category = request.getParameter("category");
		String productModel = request.getParameter("productModel");
		String productChars = request.getParameter("productChars");
		double productPrice = Double.parseDouble(request.getParameter("productPrice")); 
		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		
		Brand newBrand = Brand.valueOf(brand);
		Color newColor = Color.valueOf(color);
		Category cat = null;
		try {
			cat = new Category(category);
		} catch (EmagInvalidArgumentException e) {			
			e.printStackTrace();
		}
		
		
			try {
				if (!doesProductExist(productModel)) {
					createProduct(productModel, productChars, productPrice, productQuantity, newBrand, newColor, cat);		
					response.getWriter().println("<script>alert(\"Product is created successfully!\");</script>");
					response.getWriter().println("<a href = \"./index.html\"> Go to main page </a>");
				}
				else {
					response.getWriter().println("<script>alert(\"Product already exists!\");</script>");
					response.getWriter().println("<a href = \"./index.html\"> Go to main page </a>");
				}
			} catch (EmagInvalidArgumentException e) {				
				e.printStackTrace();
			}
				
			
		
		

		
	}

	
	private boolean doesProductExist(String productModel) throws EmagInvalidArgumentException {
		boolean productExists = false;
		if (productModel == null || productModel.isEmpty()) {
			throw new EmagInvalidArgumentException("Invalid argument!");
		}
		Connection con = DBConnection.getInstance().getCon();
		
		try {
			PreparedStatement ps = con.prepareStatement(GET_PRODUCT_BY_MODEL);
			ps.setString(1, productModel);
			ResultSet result = ps.executeQuery();
			
			
			if (result.next()) {
				productExists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productExists;
		
	}


	private void createProduct(String productModel, String productChars, double productPrice, int productQuantity,
			Brand newBrand, Color newColor, Category cat) {
		Connection con = DBConnection.getInstance().getCon();
		IProductDAO dao = new ProductDAO();
		try {
			dao.addProduct(new Product(newBrand, productModel, newColor, productPrice, productQuantity, cat, productChars));
		} catch (ProductDAOException | EmagInvalidArgumentException e) {			
			e.printStackTrace();
		} catch (BrandDAOException e) {			
			e.printStackTrace();
		} catch (ColorDAOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
