package max.waitzman.oopshelpme.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User7 on 06/03/2016.
 */
public class Analytics implements Parcelable {
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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.asStuckUser);
		dest.writeInt(this.asExtractorUser);
	}

	protected Analytics(Parcel in) {
		this.asStuckUser = in.readInt();
		this.asExtractorUser = in.readInt();
	}

	public static final Parcelable.Creator<Analytics> CREATOR = new Parcelable.Creator<Analytics>() {
		public Analytics createFromParcel(Parcel source) {
			return new Analytics(source);
		}

		public Analytics[] newArray(int size) {
			return new Analytics[size];
		}
	};
}
