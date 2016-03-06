package max.waitzman.oopshelpme;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.firebase.client.Firebase;

import max.waitzman.oopshelpme.utils.AppStartProfiles;

/*import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;*/



public class ApplicationMy extends MultiDexApplication {//Application
    private static Context context;
    private static Firebase firebase;

    private static Context mAppContext;
    private static Handler mUIhanlder;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.firebase_url));
        /*new Runnable() {
            @Override
            public void run() {
                //Your code
            }
        }.run();*/

        context = this;

	    mAppContext = getApplicationContext();
	    mUIhanlder = new Handler();

        //AppStartProfiles appStartProfiles= new AppStartProfiles();
        switch ( new AppStartProfiles().checkAppStart()) {
            case NORMAL:
                // We don't want to get on the user's nerves
                break;
            case FIRST_TIME_VERSION:
                // TODO show what's new
                //ReceiverAdvisoryNotification.scheduleAdvisoryNotification(getApplicationContext());
                break;
            case FIRST_TIME:
                // TODO show a tutorial
                //ReceiverAdvisoryNotification.scheduleAdvisoryNotification(getApplicationContext());
                break;
            default:
                break;
        }

    }

    /**
     * Set the base context for this ContextWrapper.  All calls will then be
     * delegated to the base context.  Throws
     * IllegalStateException if a base context has already been set.
     *
     * @param //base The new base context for this wrapper.
     */
    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    public static Context getContext(){
        return context;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static Handler getUIhanlder() {
        return mUIhanlder;
    }

    public static Firebase getFirebase() {
        return firebase;
    }


    /*public static ApiClient getApiClient()
    {
        return apiClient;
    }*/
}
