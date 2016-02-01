package proekt.classes;

import java.util.ArrayList;

import proekt.exceptions.EmagInvalidArgumentException;
import proekt.products.Product;

public class Catalog {

	private static Catalog theCatalog = null;

	private ArrayList<Category> categories;

	private Catalog() {
		this.categories = new ArrayList<Category>();
	}

	public static Catalog createCatalog() {
		if (theCatalog == null) {
			theCatalog = new Catalog();
		}
		return theCatalog;
	}

	public void addCategory(Category cat) throws EmagInvalidArgumentException {
		if (cat == null) {
			throw new EmagInvalidArgumentException("No such category!");
		}
		this.categories.add(cat);
	}
	
	public Product searchProduct(String productName) {
		return null;
	}

	public ArrayList<Category> getCategories() {
		ArrayList<Category> copyOfCategories = new ArrayList<Category>(categories);
		return copyOfCategories;
	}

}
