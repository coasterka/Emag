package engine;

import exceptions.EmagInvalidArgumentException;
import exceptions.EmagInvalidPassException;

public interface ICustomer {
	void displayCart();
	void makeAnOrder() throws EmagInvalidArgumentException;
	Order checkOut() throws EmagInvalidArgumentException;
}
