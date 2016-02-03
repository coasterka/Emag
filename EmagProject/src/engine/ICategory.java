package engine;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public interface ICategory {

	void addProduct(IProduct product) throws EmagInvalidArgumentException;

	void listAllProducts();

}