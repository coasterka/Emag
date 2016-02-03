package engine;

import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public interface IAdmin {
	void addCategory(String categoryName) throws EmagInvalidArgumentException, EmagExistingObjectException;
	void addProduct(IProduct product, ICategory category);
	void removeProduct(IProduct product);
}
