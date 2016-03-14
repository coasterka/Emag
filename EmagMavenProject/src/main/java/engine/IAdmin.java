package engine;

import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidArgumentException;
import products.IProduct;
import products.Product;

public interface IAdmin {
	void addCategory(String categoryName) throws EmagInvalidArgumentException, EmagExistingObjectException;
	void removeProduct(IProduct product) throws EmagInvalidArgumentException;
	void addProduct(Product product, ICategory category) throws EmagInvalidArgumentException;
}
