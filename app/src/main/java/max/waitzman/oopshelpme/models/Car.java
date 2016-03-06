package max.waitzman.oopshelpme.models;

/**
 * Created by User7 on 06/03/2016.
 */
public class Car {
	private String brand;
	private String model;
	private int year;

	public Car(){
	}

	public Car(String brand, String model, int year) {
		this.setBrand(brand);
		this.setModel(model);
		this.setYear(year);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return  getBrand()  + " " + getModel() + " " + getYear() ;
	}
}
