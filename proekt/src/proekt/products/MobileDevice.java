package proekt.products;

import proekt.classes.Category;
import proekt.exceptions.EmagInvalidArgumentException;

public abstract class MobileDevice extends Product {

	private static final int MIN_DISPLAY_SIZE_MOBILE_DEVICE = 1;
	private double displaySize;
	private String operationalSystem;

	public MobileDevice(String brand, String model, String color, double price, int quantityLeft, String OS,
			Category cat) throws EmagInvalidArgumentException {
		super(brand, model, color, price, quantityLeft, cat);
		setOperationalSystem(OS);
	}

	public double getDisplaySize() {
		return this.displaySize;
	}

	public String getOperationalSystem() {
		return this.operationalSystem;
	}

	public void setDisplaySize(double displaySize) throws EmagInvalidArgumentException {
		if (displaySize < MIN_DISPLAY_SIZE_MOBILE_DEVICE) {
			throw new EmagInvalidArgumentException(
					"Invalid display size! Must be over " + MIN_DISPLAY_SIZE_MOBILE_DEVICE + "!");
		}
		this.displaySize = displaySize;
	}

	public void setOperationalSystem(String operationalSystem) throws EmagInvalidArgumentException {
		if (operationalSystem == null || operationalSystem.isEmpty()) {
			throw new EmagInvalidArgumentException("Invalid OS! OS can not be null or empty!");
		}
		this.operationalSystem = operationalSystem;
	}
}
