package proekt.classes;



import java.util.ArrayList;

import proekt.exceptions.EmagInvalidArgumentException;
import proekt.products.IProduct;


public class Category {
	
	private static int numberOfCategories = 0;
	
	private final int categoryID;
	private String name;
	private ArrayList<IProduct> products;
	private Catalog catalog;
	
	public Category(String name, Catalog cat) throws EmagInvalidArgumentException {
		this.categoryID = ++numberOfCategories;
		this.setName(name);
		this.products = new ArrayList<IProduct>();
		this.setCatalog(cat);
		cat.addCategory(this);
	}
	
	public void addProduct(IProduct p) throws EmagInvalidArgumentException {
		if (p != null) {
			this.products.add(p);
		}
		else {
			throw new EmagInvalidArgumentException("No such product!");
		}
	}

	public int getCategoryID() {
		return this.categoryID;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<IProduct> getProducts() {
		ArrayList<IProduct> copy = new ArrayList<IProduct>(products);
		return copy;
	}

	public void setName(String name) throws EmagInvalidArgumentException {
		if (name != null && !name.equals("")) {
			this.name = name;
		}
		else {
			throw new EmagInvalidArgumentException("Invalid category name!");
		}
		
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) throws EmagInvalidArgumentException {
		if (catalog != null) {
			this.catalog = catalog;
		}
		else {
			throw new EmagInvalidArgumentException("No such catalog!");
		}
		
	}

	

}
