package max.waitzman.oopshelpme.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import max.waitzman.oopshelpme.ApplicationMy;

//import max.waitzman.oopshelpme.ApplicationMy;

/**
 * Created by User7 on 31/01/2016.
 */
public class NetworkUtil {

	public boolean isConnectedToInternet() {  //make sure manifest has ACCESS_NETWORK_STATE

		//Warning not tested. Proof of concept to help alert / handle cases where Internet connection is poor, aka use offline maps

		Context context = ApplicationMy.getAppContext();

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

		return isConnected;

	}
}
