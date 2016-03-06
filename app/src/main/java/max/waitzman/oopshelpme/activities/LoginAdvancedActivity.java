package max.waitzman.oopshelpme.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;
import max.waitzman.oopshelpme.utils.LogUtil;

public class LoginAdvancedActivity extends FirebaseLoginBaseActivity {

	Firebase firebase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_advanced);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		firebase = new Firebase(getString(R.string.firebase_url));



	}

	@Override
	protected void onStart() {
		super.onStart();
		// All providers are optional! Remove any you don't want.
		setEnabledAuthProvider(AuthProviderType.FACEBOOK);
		//setEnabledAuthProvider(AuthProviderType.PASSWORD);

		showFirebaseLoginPrompt();
	}

	@Override
	protected Firebase getFirebaseRef() {
		//return null;
		return ApplicationMy.getFirebase();
	}

	@Override
	protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
		LogUtil.e(firebaseLoginError.toString());
	}

	@Override
	protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
		LogUtil.e(firebaseLoginError.toString());
	}
}
