package max.waitzman.oopshelpme.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User7 on 06/03/2016.
 */
public class User implements Parcelable {

	public enum UserType {
		Regular("Regular", 0),
		Admin("Admin", 1);
		private String stringValue;
		private int intValue;
		UserType(String toString, int value) {
			stringValue = toString;
			intValue = value;
		}
		/*public static List<ItemIdName> getForecastTargetTypeEnumList (){
			List<ItemIdName> list=new ArrayList<>();
			list.add(new ItemIdName(ForecastTargetTypeEnum.TEMP.toInt(), ForecastTargetTypeEnum.TEMP.toString()));
			list.add(new ItemIdName(ForecastTargetTypeEnum.RAIN.toInt(), ForecastTargetTypeEnum.RAIN.toString()));
			list.add(new ItemIdName(ForecastTargetTypeEnum.PENMAN_EVAPORATION_etadut.toInt(), ForecastTargetTypeEnum.PENMAN_EVAPORATION_etadut.toString()));
			return list;
		}*/
		@Override
		public String toString() {
			return stringValue;
		}
		public Integer toInt() {
			return intValue;
		}
	}

	private String id;
	private String firstName ;
	private String lastName ;
	private String phoneNumber ;
	private String email;
	private String profileImageURL;
	private String facebookProfileURL;
	private String address;
	private Car car;
	private Analytics analytics;
	private UserType userType=UserType.Regular ;

	public User() {
	}

	public User(String id, String firstName, String lastName, String phoneNumber, String email, String profileImageURL, String facebookProfileURL,String address, Car car, Analytics analytics, UserType userType) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
		this.setProfileImageURL(profileImageURL);
		this.setFacebookProfileURL(facebookProfileURL);
		this.setAddress(address);
		this.setCar(car);
		this.setAnalytics(analytics);
		this.setUserType(userType);
	}

	/*public User(String id, String firstName, String lastName, String phoneNumber, String address, Car car ,Analytics analytics, UserType userType) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
		this.setCar(car);
		this.setAnalytics();
		this.setUserType(userType);
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Analytics getAnalytics() {
		return analytics;
	}

	public void setAnalytics(Analytics analytics) {
		this.analytics = analytics;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}

	public String getFacebookProfileURL() {
		return facebookProfileURL;
	}

	public void setFacebookProfileURL(String facebookProfileURL) {
		this.facebookProfileURL = facebookProfileURL;
	}

	@Override
	public String toString() {
		return getFirstName()+" "+getLastName();
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.firstName);
		dest.writeString(this.lastName);
		dest.writeString(this.phoneNumber);
		dest.writeString(this.email);
		dest.writeString(this.profileImageURL);
		dest.writeString(this.facebookProfileURL);
		dest.writeString(this.address);
		dest.writeParcelable(this.car, flags);
		dest.writeParcelable(this.analytics, flags);
		dest.writeInt(this.userType == null ? -1 : this.userType.ordinal());
	}

	protected User(Parcel in) {
		this.id = in.readString();
		this.firstName = in.readString();
		this.lastName = in.readString();
		this.phoneNumber = in.readString();
		this.email = in.readString();
		this.profileImageURL = in.readString();
		this.facebookProfileURL = in.readString();
		this.address = in.readString();
		this.car = in.readParcelable(Car.class.getClassLoader());
		this.analytics = in.readParcelable(Analytics.class.getClassLoader());
		int tmpUserType = in.readInt();
		this.userType = tmpUserType == -1 ? null : UserType.values()[tmpUserType];
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
