package classes;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public class Category {

	private static int numberOfCategories = 0;

	private final int categoryID; // or update automatically from DB?
	private String name;
	private ArrayList<IProduct> products;
	private Catalog catalog;

	public Category(String name, Catalog cat) throws EmagInvalidArgumentException {
		this.categoryID = ++numberOfCategories;
		setName(name);
		products = new ArrayList<IProduct>();
		setCatalog(cat);
		cat.addCategory(this);
	}

	public void addProduct(IProduct product) throws EmagInvalidArgumentException {
		if (product == null) {
			throw new EmagInvalidArgumentException("No such product!");
		}
		this.products.add(product);
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
		if (name == null || name.isEmpty()) {
			throw new EmagInvalidArgumentException("Invalid category name!");
		}
		this.name = name;
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) throws EmagInvalidArgumentException {
		if (catalog == null) {
			throw new EmagInvalidArgumentException("No such catalog!");
		}
		this.catalog = catalog;
	}
}
