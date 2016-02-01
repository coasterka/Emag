import java.util.ArrayList;

public class ShopingCart {
	private double moneyAmount=0;;
	private ArrayList<IProduct> products=new ArrayList<IProduct>();
	private int brProdukti=0;
	public void addProdukt(IProduct product){
		if(product!=null){
		products.add(product);
		brProdukti++;
		moneyAmount=+product.getPrice();
		}
	}
	public void removeProdukt(IProduct product){
		if(product!=null){
			moneyAmount=-product.getPrice();
		products.remove(product);
		brProdukti--;
		}
	}
	}