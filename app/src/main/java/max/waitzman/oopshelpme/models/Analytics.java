package max.waitzman.oopshelpme.models;

/**
 * Created by User7 on 06/03/2016.
 */
public class Analytics {
	private int asStuckUser;
	private int asExtractorUser;

	public Analytics() {
	}

	public Analytics(int asStuckUser, int asExtractorUser) {
		this.setAsStuckUser(asStuckUser);
		this.setAsExtractorUser(asExtractorUser);
	}

	public int getAsStuckUser() {
		return asStuckUser;
	}

	public void setAsStuckUser(int asStuckUser) {
		this.asStuckUser = asStuckUser;
	}

	public int getAsExtractorUser() {
		return asExtractorUser;
	}

	public void setAsExtractorUser(int asExtractorUser) {
		this.asExtractorUser = asExtractorUser;
	}

	@Override
	public String toString() {
		return "asStuckUser="+asStuckUser +" asExtractorUser="+asExtractorUser ;
	}
}
