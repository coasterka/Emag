package engine;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public interface ICategory {

	void addProduct(IProduct product) throws EmagInvalidArgumentException;

	void listAllProducts();
	
	String getName();
	
	ArrayList<IProduct> getProducts();

}