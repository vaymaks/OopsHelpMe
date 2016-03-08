package max.waitzman.oopshelpme.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User7 on 06/03/2016.
 */
public class Car implements Parcelable {
	private String brand="";
	private String model="";
	private int year=1946;

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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.brand);
		dest.writeString(this.model);
		dest.writeInt(this.year);
	}

	protected Car(Parcel in) {
		this.brand = in.readString();
		this.model = in.readString();
		this.year = in.readInt();
	}

	public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
		public Car createFromParcel(Parcel source) {
			return new Car(source);
		}

		public Car[] newArray(int size) {
			return new Car[size];
		}
	};
}
