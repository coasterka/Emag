package engine;

import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidArgumentException;
import products.IProduct;
import products.Product;

public class Admin extends User implements IAdmin {

	private Catalog catalog;

	public Admin(String name, String username, String password) throws EmagInvalidArgumentException {
		super(name, username, password);
		super.setIsAdmin(true);
	}

	@Override
	public void addCategory(String categoryName) throws EmagInvalidArgumentException, EmagExistingObjectException {
		ICategory newCategory = new Category(categoryName);
		for (ICategory category : catalog.getCategories()) {
			if (!catalog.getCategories().contains(newCategory)) {
				catalog.addCategory(newCategory);
			} else {
				throw new EmagExistingObjectException("Category " + categoryName + " already exist!");
			}
		}
	}

	public void addAttributeToProduct(Product product, String attribute, String value)
			throws EmagInvalidArgumentException {
		product.setAttributesWithValues(attribute, value);
	}

	@Override
	public void addProduct(Product product, ICategory category) throws EmagInvalidArgumentException {
		if (product == null) {
			throw new EmagInvalidArgumentException("Product is not defined!");
		}
		if (category == null) {
			throw new EmagInvalidArgumentException("Category is not defined!");
		}
		// check if the category exists in the store catalog
		for (ICategory currentCategory : this.catalog.getCategories()) {
			if (currentCategory == category) {
				break;
			} else {
				catalog.addCategory(category);
				System.out.println("New category \"" + category.getName() + "\" added to catalog.");
			}
		}
		category.addProduct(product);
	}

	@Override
	public void removeProduct(IProduct product) throws EmagInvalidArgumentException {
		if (product == null) {
			throw new EmagInvalidArgumentException("Product is not defined!");
		}
		for (ICategory category : this.catalog.getCategories()) {
			if (category.getProducts().contains(product)) {
				category.getProducts().remove(product);
				System.out.println("Product with ID " + product.getId()
						+ " has been successfully removed from Category " + category.getName() + ".");
				break;
			} else {
				throw new EmagInvalidArgumentException("This product does not exist in any of the categories!");
			}
		}
	}

}
