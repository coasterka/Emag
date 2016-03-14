package engine;

import java.util.ArrayList;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public class Category implements ICategory {

	private int categoryID;
	private String name;
	private ArrayList<IProduct> products;
	private Catalog catalog;

	public Category(String name) throws EmagInvalidArgumentException {		
		this.products = new ArrayList<IProduct>();
		this.catalog = Catalog.theCatalog;
		this.catalog.addCategory(this);
		setName(name);
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
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
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

	public void setCatalog(Catalog catalog) throws EmagInvalidArgumentException {
		if (catalog == null) {
			throw new EmagInvalidArgumentException("No such catalog!");
		}
		this.catalog = catalog;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
}
