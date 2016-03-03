package max.waitzman.oopshelpme.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import max.waitzman.oopshelpme.ApplicationMy;


/**
 * Created by User7 on 11/10/2015.
 */
public class AppStartProfiles {

	//region Example use
	/*switch (checkAppStart()) {
		case NORMAL:
			// We don't want to get on the user's nerves
			break;
		case FIRST_TIME_VERSION:
			// TODO show what's new
			break;
		case FIRST_TIME:
			// TODO show a tutorial
			break;
		default:
			break;
	}*/
	//endregion


	public AppStartProfiles() {
	}

	/**
	 * Distinguishes different kinds of app starts: <li>
	 * <ul>
	 * First start ever ({@link #FIRST_TIME})
	 * </ul>
	 * <ul>
	 * First start in this version ({@link #FIRST_TIME_VERSION})
	 * </ul>
	 * <ul>
	 * Normal app start ({@link #NORMAL})
	 * </ul>
	 *
	 * @author schnatterer
	 *
	 */
	public enum AppStart {
		FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
	}

	/**
	 * The app version code (not the version name!) that was used on the last
	 * start of the app.
	 */
	private static final String LAST_APP_VERSION = "last_app_version";

	/**
	 * Finds out started for the first time (ever or in the current version).<br/>
	 * <br/>
	 * Note: This method is <b>not idempotent</b> only the first call will
	 * determine the proper result. Any subsequent calls will only return
	 * {@link AppStart#NORMAL} until the app is started again. So you might want
	 * to consider caching the result!
	 *
	 * @return the type of app start
	 */
	public AppStart checkAppStart() {
		Context context= ApplicationMy.getContext();
		PackageInfo pInfo;
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		AppStart appStart = AppStart.NORMAL;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			int lastVersionCode = sharedPreferences.getInt(LAST_APP_VERSION, -1);
			int currentVersionCode = pInfo.versionCode;
			appStart = checkAppStart(currentVersionCode, lastVersionCode);
			// Update version in preferences
			sharedPreferences.edit()
					.putInt(LAST_APP_VERSION, currentVersionCode).commit();
		} catch (PackageManager.NameNotFoundException e) {
			Log.w("Envitech", "Unable to determine current app version from pacakge manager. Defenisvely assuming normal app start.");
		}
		return appStart;
	}

	public AppStart checkAppStart(int currentVersionCode, int lastVersionCode) {
		if (lastVersionCode == -1) {
			return AppStart.FIRST_TIME;
		} else if (lastVersionCode < currentVersionCode) {
			return AppStart.FIRST_TIME_VERSION;
		} else if (lastVersionCode > currentVersionCode) {
			Log.w("Envitech", "Current version code (" + currentVersionCode
					                  + ") is less then the one recognized on last startup ("
					                  + lastVersionCode
					                  + "). Defenisvely assuming normal app start.");
			return AppStart.NORMAL;
		} else {
			return AppStart.NORMAL;
		}
	}
}


