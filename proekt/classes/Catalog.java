package proekt.classes;

import java.util.ArrayList;

import proekt.exceptions.EmagInvalidArgumentException;


public class Catalog {
	
	private static Catalog  theCatalog = null;
	
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
		if (cat != null) {
			this.categories.add(cat);
		}
		else {
			throw new EmagInvalidArgumentException("No such category!");
		}
	}
	
	

	public ArrayList<Category> getCategories() {
		ArrayList<Category> copy = new ArrayList<Category>(categories);
		return copy;
	}

	

}
