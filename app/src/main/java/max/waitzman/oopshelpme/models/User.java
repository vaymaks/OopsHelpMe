package max.waitzman.oopshelpme.models;

/**
 * Created by User7 on 06/03/2016.
 */
public class User {

	public enum UserType {
		Admin("Admin", 0),
		Regular("Regular", 1);
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

	private String login ;
	private String firstName ;
	private String lastName ;
	private String phoneNumber ;
	private String address;
	private Car car;
	private Analytics analytics;
	private UserType userType=UserType.Regular ;

	public User() {
	}

	public User(String login, String firstName, String lastName, String phoneNumber, String address, Car car ,Analytics analytics, UserType userType) {
		this.setLogin(login);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
		this.setCar(car);
		this.setAnalytics(analytics);
		this.setUserType(userType);
	}

	/*public User(String login, String firstName, String lastName, String phoneNumber, String address, Car car ,Analytics analytics, UserType userType) {
		this.setLogin(login);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
		this.setCar(car);
		this.setAnalytics();
		this.setUserType(userType);
	}*/

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public String toString() {
		return getFirstName()+" "+getLastName();
	}
}
