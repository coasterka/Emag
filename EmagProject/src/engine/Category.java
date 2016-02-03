package engine;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public class Category implements ICategory {

	private static int numberOfCategories = 1;

	private final int categoryID;
	private String name;
	private ArrayList<IProduct> products;
	private Catalog catalog;

	public Category(String name, Catalog cat) throws EmagInvalidArgumentException {
		this.categoryID = numberOfCategories++;
		this.products = new ArrayList<IProduct>();
		cat.addCategory(this);
		setName(name);
		setCatalog(cat);
	}
	
	@Override
	public void addProduct(IProduct product) throws EmagInvalidArgumentException {
		if (product == null) {
			throw new EmagInvalidArgumentException("No such product!");
		}
		this.products.add(product);
	}

	@Override
	public void listAllProducts() {
		for (IProduct product : products) {
			System.out.println(product);
		}
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<IProduct> getProducts() {
		ArrayList<IProduct> copy = new ArrayList<IProduct>(products);
		return copy;
	}

	private void setName(String name) throws EmagInvalidArgumentException {
		if (name == null || name.isEmpty()) {
			throw new EmagInvalidArgumentException("Invalid category name!");
		}
		this.name = name;
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	private void setCatalog(Catalog catalog) throws EmagInvalidArgumentException {
		if (catalog == null) {
			throw new EmagInvalidArgumentException("No such catalog!");
		}
		this.catalog = catalog;
	}
}
