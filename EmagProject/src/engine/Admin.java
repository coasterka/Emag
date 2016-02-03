package engine;

import exceptions.EmagExistingObjectException;
import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public class Admin extends User implements IAdmin {

	private Catalog catalog;
	
	public Admin(String name, String username, String password) throws EmagInvalidArgumentException {
		super(name, username, password);
	}

	@Override
	public void addCategory(String categoryName) throws EmagInvalidArgumentException, EmagExistingObjectException {
		ICategory newCategory = new Category(categoryName);
		for (ICategory category : catalog.getCategories()) {
			if (!catalog.getCategories().contains(newCategory)) {
				catalog.addCategory(newCategory);
			}
			else {
				throw new EmagExistingObjectException("Category " + categoryName + " already exist!");
			}
		}
	}

	@Override
	public void addProduct(IProduct product, ICategory category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProduct(IProduct product) {
		// TODO Auto-generated method stub
		
	}

}
