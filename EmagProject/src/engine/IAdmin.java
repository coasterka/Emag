package engine;

import products.IProduct;

public interface IAdmin {
	void addCategory(Category category);
	void addProduct(IProduct product, Category category);
	void removeProduct(IProduct product);
}
