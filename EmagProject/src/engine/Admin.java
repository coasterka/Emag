package engine;

import exceptions.EmagInvalidArgumentException;
import products.IProduct;

public class Admin extends User implements IAdmin {

	public Admin(String name, String username, String password) throws EmagInvalidArgumentException {
		super(name, username, password);
	}

	@Override
	public void addCategory(ICategory category) {
		// TODO Auto-generated method stub
		
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
