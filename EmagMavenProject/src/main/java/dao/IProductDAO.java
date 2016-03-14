package dao;

import java.util.List;

import exceptions.BrandDAOException;
import exceptions.CategoryDAOException;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;
import exceptions.ProductDAOException;
import products.Product;

public interface IProductDAO {

	int addProduct(Product product) throws ProductDAOException;

	void updateProduct(Product product) throws ProductDAOException;

	void removeProduct(int productId) throws ProductDAOException;
	
	Product getProductById(int productId) throws ProductDAOException,
	EmagInvalidArgumentException, BrandDAOException, ColorDAOException, CategoryDAOException;

	List<Product> getProductsByCategory(int categoryId) throws EmagInvalidArgumentException, ProductDAOException,
			BrandDAOException, ColorDAOException, CategoryDAOException;

}