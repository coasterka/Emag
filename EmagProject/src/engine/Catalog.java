package engine;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;
import products.Product;

public class Catalog {

	public static Catalog theCatalog = null;
	private int id;

	private ArrayList<ICategory> categories;

	private Catalog() {
		this.categories = new ArrayList<ICategory>();
	}

	public synchronized static Catalog createCatalog() {
		if (theCatalog == null) {
			theCatalog = new Catalog();
		}
		return theCatalog;
	}

	public void addCategory(ICategory newCategory) throws EmagInvalidArgumentException {
		if (newCategory == null) {
			throw new EmagInvalidArgumentException("No such category!");
		}
		this.categories.add(newCategory);
	}
	
	public Product searchProduct(String productName) {
		return null;
	}

	public ArrayList<ICategory> getCategories() {
		ArrayList<ICategory> copyOfCategories = new ArrayList<ICategory>(categories);
		return copyOfCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
