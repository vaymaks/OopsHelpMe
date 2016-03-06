package max.waitzman.oopshelpme.models;

import java.util.Date;

/**
 * Created by User7 on 06/03/2016.
 */
public class Rescue {
	private Location location;
	private Date date;
	private User stuckUser;
	private User extractorUser;

	public Rescue() {
	}

	public Rescue(Location location, Date date, User stuckUser, User extractorUser) {
		this.setLocation(location);
		this.setDate(date);
		this.setStuckUser(stuckUser);
		this.setExtractorUser(extractorUser);
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
}
