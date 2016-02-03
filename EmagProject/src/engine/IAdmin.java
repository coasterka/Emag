package engine;

import products.IProduct;

public interface IAdmin {
	void addCategory(ICategory category);
	void addProduct(IProduct product, ICategory category);
	void removeProduct(IProduct product);
}
