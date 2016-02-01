package proekt.products;

public interface IProduct {
	
	int getProductID();
	String getProductBrand();
	String getProductModel();
	String getProductColor();
	double getPrice();
	String getProductCategory();
	int getQuantityLeft();
	void displayProduct();

}
