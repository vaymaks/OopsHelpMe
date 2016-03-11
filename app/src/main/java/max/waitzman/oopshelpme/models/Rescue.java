package max.waitzman.oopshelpme.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by User7 on 06/03/2016.
 */
public class Rescue implements Parcelable {
	private String title="";
	private String description="";
	private Location location=new Location(0f,0f);
	private Date date = new Date();
	private User stuckUser= new User();
	private User extractorUser=new User();

	public Rescue() {
	}

	public Rescue(String title, String description, Location location, Date date, User stuckUser, User extractorUser) {
		this.setTitle(title);
		this.setDescription(description);
		this.setLocation(location);
		this.setDate(date);
		this.setStuckUser(stuckUser);
		this.setExtractorUser(extractorUser);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getStuckUser() {
		return stuckUser;
	}

	public void setStuckUser(User stuckUser) {
		this.stuckUser = stuckUser;
	}

	public User getExtractorUser() {
		return extractorUser;
	}

	public void setExtractorUser(User extractorUser) {
		this.extractorUser = extractorUser;
	}

	@Override
	public String toString() {
		return   "Rescue "+stuckUser +" "+ date ;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.title);
		dest.writeString(this.description);
		dest.writeParcelable(this.location, 0);
		dest.writeLong(date != null ? date.getTime() : -1);
		dest.writeParcelable(this.stuckUser, 0);
		dest.writeParcelable(this.extractorUser, 0);
	}

	protected Rescue(Parcel in) {
		this.title = in.readString();
		this.description = in.readString();
		this.location = in.readParcelable(Location.class.getClassLoader());
		long tmpDate = in.readLong();
		this.date = tmpDate == -1 ? null : new Date(tmpDate);
		this.stuckUser = in.readParcelable(User.class.getClassLoader());
		this.extractorUser = in.readParcelable(User.class.getClassLoader());
	}

	public static final Creator<Rescue> CREATOR = new Creator<Rescue>() {
		public Rescue createFromParcel(Parcel source) {
			return new Rescue(source);
		}

		public Rescue[] newArray(int size) {
			return new Rescue[size];
		}
	};
}
