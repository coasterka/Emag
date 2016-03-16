package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import engine.Brand;
import engine.Category;
import engine.Color;
import exceptions.BrandDAOException;
import exceptions.CategoryDAOException;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;
import exceptions.ProductDAOException;
import products.Product;

public class ProductDAO extends AbstractDAO implements IProductDAO {
	
	private static final String INSERT_NEW_PRODUCT_SQL = "INSERT INTO products VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_PRODUCT_SQL = "UPDATE products SET brand_id = ?, color_id = ?, category_id = ?, "
			+ "model = ?, characteristics = ?, price = ?, qty_left = ? WHERE product_id = ?;";
	private static final String FIND_PRODUCT_BY_ID_SQL = "SELECT * FROM products WHERE product_id = ?";
	
	@Override
	public int addProduct (Product product) throws ProductDAOException {
		if (product != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(INSERT_NEW_PRODUCT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
								
				ps.setInt(1, product.getBrand().getId());
				ps.setInt(2, product.getColor().getId());
				ps.setInt(3, product.getCategory().getCategoryID());				
				ps.setString(4, product.getModel());
				ps.setString(5, product.getCharacteristics());
				ps.setDouble(6, product.getPrice());
				ps.setInt(7, product.getQuantityLeft());				
				
				ps.executeUpdate();

				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new ProductDAOException("The product cannot be added right now. Thank you.", e);
			}
		} else {
			throw new ProductDAOException("No such product!");
		}
	}
	
	@Override
	public void updateProduct(Product product) throws ProductDAOException {
		if (product != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(UPDATE_PRODUCT_SQL);

				ps.setInt(1, product.getBrand().getId());
				ps.setInt(2, product.getColor().getId());
				ps.setInt(3, product.getCategory().getCategoryID());				
				ps.setString(4, product.getModel());
				ps.setString(5, product.getCharacteristics());
				ps.setDouble(6, product.getPrice());
				ps.setInt(7, product.getQuantityLeft());
				ps.setInt(8, product.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ProductDAOException("The driver cannot be updated right now. Thank you.", e);
			}
		}
		else {
			throw new ProductDAOException("No such User");
		}
	}
	
	@Override
	public void removeProduct(int productId) throws ProductDAOException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement("DELETE FROM products WHERE id = ?");
			ps.setInt(1, productId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new ProductDAOException("The driver cannot be deleted right now. Thank you.", e);
		}
	}
	
	@Override
	public Product getProductById(int productId) throws ProductDAOException,
				EmagInvalidArgumentException, BrandDAOException, ColorDAOException, CategoryDAOException{
		Product wantedProduct = null;

		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_PRODUCT_BY_ID_SQL);
			ps.setInt(1, productId);
			ResultSet result = ps.executeQuery();
			result.next();

			IBrandDAO brand = new BrandDAO();
			IColorDAO color = new ColorDAO();
			ICategoryDAO category = new CategoryDAO();	
			
			wantedProduct = new Product(brand.getBrandById(result.getInt(2)),
					result.getString(5),
					color.getColorById(result.getInt(3)),
					result.getDouble(7),
					result.getInt(8),
					category.getCategoryById(result.getInt(4)),
					result.getString(6));
			
			return wantedProduct;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductDAOException("The product with id " + productId + " cannot be found . Thank you.", e);
		}
	}
	
	@Override
	public List<Product> getProductsByCategory(int categoryId) throws EmagInvalidArgumentException, ProductDAOException,
						BrandDAOException, ColorDAOException, CategoryDAOException {
		List<Product> productsByCategory = new ArrayList<Product>();
		
		
		try {
			PreparedStatement ps;
			ps = getCon().prepareStatement("SELECT * FROM products WHERE category_id = ?");
			ps.setInt(1, categoryId);
			
			ResultSet resultSet = ps.executeQuery();
			
			IBrandDAO brand = new BrandDAO();
			IColorDAO color = new ColorDAO();
			ICategoryDAO category = new CategoryDAO();			
						
			while(resultSet.next()) {				
				productsByCategory.add(new Product(brand.getBrandById(resultSet.getInt(2)),
								resultSet.getString(5),
								color.getColorById(resultSet.getInt(3)),
								resultSet.getDouble(7),
								resultSet.getInt(8),
								category.getCategoryById(resultSet.getInt(4)),
								resultSet.getString(6)));
			}		
			return productsByCategory;
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new ProductDAOException("The driver cannot be updated right now. Thank you.", e);
		}
	}
	
	@Override
	public List<Product> getAllProducts() throws CategoryDAOException, EmagInvalidArgumentException {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		try {
			con = getCon();
			ResultSet productsSet = con.prepareStatement("SELECT * FROM products;").executeQuery();
			while (productsSet.next()) {
				int productId = productsSet.getInt(1); // we don't use the returned id
				int brandId = productsSet.getInt(2);
				int colorId = productsSet.getInt(3);
				int categoryId = productsSet.getInt(4);
				String model = productsSet.getString(5);
				String characteristics = productsSet.getString(6);
				double price = productsSet.getDouble(7);
				int quantityLeft = productsSet.getInt(8);
				Brand brand = (new BrandDAO()).getBrandById(brandId);
				Color color = (new ColorDAO()).getColorById(colorId);
				Category category = (new CategoryDAO()).getCategoryById(categoryId);
				products.add(new Product(brand, model, color, price, quantityLeft, category, characteristics));
			}
			return products;
		} catch (SQLException | BrandDAOException | ColorDAOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
