package max.waitzman.oopshelpme.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
//region origin LoginActivity
/*public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

	private static final String TAG = LoginActivity.class.getSimpleName();
	Firebase firebase;

	*//* TextView that is used to display information about the logged in user *//*
	private TextView mLoggedInStatusTextView;


	*//* A dialog that is presented until the Firebase authentication finished. *//*
	private ProgressDialog mAuthProgressDialog;

	*//* A reference to the Firebase *//*
	private Firebase mFirebaseRef;

	*//* Data from the authenticated user *//*
	private AuthData mAuthData;

	*//* Listener for Firebase session changes *//*
	private Firebase.AuthStateListener mAuthStateListener;

	*//* *************************************
	 *              FACEBOOK               *
	 ***************************************//*
    *//* The login button for Facebook *//*
	private LoginButton mFacebookLoginButton;
	*//* The callback manager for Facebook *//*
	private CallbackManager mFacebookCallbackManager;
	*//* Used to track user logging in/out off Facebook *//*
	private AccessTokenTracker mFacebookAccessTokenTracker;

	*//**
	 * Id to identity READ_CONTACTS permission request.
	 *//*
	private static final int REQUEST_READ_CONTACTS = 0;

	*//**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 *//*
	private static final String[] DUMMY_CREDENTIALS = new String[]{"foo@example.com:hello", "bar@example.com:world","max@:xxx"};
	*//**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 *//*
	private UserLoginTask mAuthTask = null;

	// UI references.
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//firebase = new Firebase(getString(R.string.firebase_url));
		firebase = ApplicationMy.getFirebase();

		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
		populateAutoComplete();

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		mEmailSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.login_progress);
	}

	private void populateAutoComplete() {
		if (!mayRequestContacts()) {
			return;
		}

		getLoaderManager().initLoader(0, null, this);
	}

	private boolean mayRequestContacts() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			return true;
		}
		if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
			return true;
		}
		if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
			Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
					.setAction(android.R.string.ok, new View.OnClickListener() {
						@Override
						@TargetApi(Build.VERSION_CODES.M)
						public void onClick(View v) {
							requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
						}
					});
		} else {
			requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
		}
		return false;
	}

	*//**
	 * Callback received when a permissions request has been completed.
	 *//*
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == REQUEST_READ_CONTACTS) {
			if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				populateAutoComplete();
			}
		}
	}

	*//**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 *//*
	private void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!isEmailValid(email)) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(email, password);
			mAuthTask.execute((Void) null);
		}
	}

	private boolean isEmailValid(String email) {
		//TODO: Replace this with your own logic
		return email.contains("@");
	}

	private boolean isPasswordValid(String password) {
		//TODO: Replace this with your own logic
		return password.length() >= 3;
	}

	*//**
	 * Shows the progress UI and hides the login form.
	 *//*
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
		return new CursorLoader(this,
				                       // Retrieve data rows for the device user's 'profile' contact.
				                       Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

				                       // Select only email addresses.
				                       ContactsContract.Contacts.Data.MIMETYPE + " = ?", new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},

				                       // Show primary email addresses first. Note that there won't be
				                       // a primary email address if the user hasn't specified one.
				                       ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
		List<String> emails = new ArrayList<>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			emails.add(cursor.getString(ProfileQuery.ADDRESS));
			cursor.moveToNext();
		}

		addEmailsToAutoComplete(emails);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursorLoader) {

	}

	private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
		//Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
		ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
		mEmailView.setAdapter(adapter);
	}


	private interface ProfileQuery {
		String[] PROJECTION = {ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.IS_PRIMARY,};

		int ADDRESS = 0;
		int IS_PRIMARY = 1;
	}

	*//**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 *//*
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private final String mEmail;
		private final String mPassword;

		UserLoginTask(String email, String password) {
			mEmail = email;
			mPassword = password;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				Intent toMainActivity = new Intent( getBaseContext(), LoginAdvancedActivity.class);
				startActivity(toMainActivity);
				finish();
			} else {
				mPasswordView.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}*/
//endregion


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.plus.Plus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity
		//implements
		//		GoogleApiClient.ConnectionCallbacks,
		//		GoogleApiClient.OnConnectionFailedListener

{

	private static final String TAG = LoginActivity.class.getSimpleName();

	/* *************************************
	 *              GENERAL                *
	 ***************************************/
    /* TextView that is used to display information about the logged in user */
	private TextView mLoggedInStatusTextView;

	/* A dialog that is presented until the Firebase authentication finished. */
	private ProgressDialog mAuthProgressDialog;

	/* A reference to the Firebase */
	private Firebase mFirebaseRef;

	/* Data from the authenticated user */
	private AuthData mAuthData;

	/* Listener for Firebase session changes */
	private Firebase.AuthStateListener mAuthStateListener;

	/* *************************************
	 *              FACEBOOK               *
	 ***************************************/
    /* The login button for Facebook */
	private LoginButton mFacebookLoginButton;
	/* The callback manager for Facebook */
	private CallbackManager mFacebookCallbackManager;
	/* Used to track user logging in/out off Facebook */
	private AccessTokenTracker mFacebookAccessTokenTracker;


	/* *************************************
	 *              GOOGLE                 *
	 ***************************************/
    /* Request code used to invoke sign in user interactions for Google+ */
	public static final int RC_GOOGLE_LOGIN = 1;

	/* Client used to interact with Google APIs. */
	private GoogleApiClient mGoogleApiClient;

	/* A flag indicating that a PendingIntent is in progress and prevents us from starting further intents. */
	private boolean mGoogleIntentInProgress;

	/* Track whether the sign-in button has been clicked so that we know to resolve all issues preventing sign-in
	 * without waiting. */
	private boolean mGoogleLoginClicked;

	/* Store the connection result from onConnectionFailed callbacks so that we can resolve them when the user clicks
	 * sign-in. */
	private ConnectionResult mGoogleConnectionResult;

	/* The login button for Google */
	private SignInButton mGoogleLoginButton;

	/* *************************************
	 *              TWITTER                *
	 ***************************************/
	public static final int RC_TWITTER_LOGIN = 2;

	private Button mTwitterLoginButton;

	/* *************************************
	 *              PASSWORD               *
	 ***************************************/
	private Button mPasswordLoginButton;

	/* *************************************
	 *            ANONYMOUSLY              *
	 ***************************************/
	private Button mAnonymousLoginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        /* Load the view and display it */
		setContentView(R.layout.activity_login_facebook);

        /* *************************************
         *              FACEBOOK               *
         ***************************************/
        /* Load the Facebook login button and set up the tracker to monitor access token changes */
		mFacebookCallbackManager = CallbackManager.Factory.create();
		mFacebookLoginButton = (LoginButton) findViewById(R.id.login_with_facebook);
		mFacebookAccessTokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
				Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
				LoginActivity.this.onFacebookAccessTokenChange(currentAccessToken);
			}
		};

		/* *************************************
         *               GOOGLE                *
         ***************************************/
		//region GOOGLE
        /* Load the Google login button */
		/*mGoogleLoginButton = (SignInButton) findViewById(R.id.login_with_google);
		mGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mGoogleLoginClicked = true;
				if (!mGoogleApiClient.isConnecting()) {
					if (mGoogleConnectionResult != null) {
						resolveSignInError();
					} else if (mGoogleApiClient.isConnected()) {
						getGoogleOAuthTokenAndLogin();
					} else {
                    *//* connect API now *//*
						Log.d(TAG, "Trying to connect to Google API");
						mGoogleApiClient.connect();
					}
				}
			}
		});
        *//* Setup the Google API object to allow Google+ logins *//*
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				                   .addConnectionCallbacks(this)
				                   .addOnConnectionFailedListener(this)
				                   .addApi(Plus.API)
				                   .addScope(Plus.SCOPE_PLUS_LOGIN)
				                   .build();
		*/
		//endregion

        /* *************************************
         *                TWITTER              *
         ***************************************/
		//region TWITTER
		/*mTwitterLoginButton = (Button) findViewById(R.id.login_with_twitter);
		mTwitterLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loginWithTwitter();
			}
		});
		*/
		//endregion

         /* *************************************
         *               PASSWORD              *
         ***************************************/
		mPasswordLoginButton = (Button) findViewById(R.id.login_with_password);
		mPasswordLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loginWithPassword();
			}
		});


        /* *************************************
         *              ANONYMOUSLY            *
         ***************************************/
        /* Load and setup the anonymous login button */
		mAnonymousLoginButton = (Button) findViewById(R.id.login_anonymously);
		mAnonymousLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				loginAnonymously();
			}
		});

        /* *************************************
         *               GENERAL               *
         ***************************************/
		mLoggedInStatusTextView = (TextView) findViewById(R.id.login_status);

        /* Create the Firebase ref that is used for all authentication with Firebase */
		mFirebaseRef = new Firebase(getResources().getString(R.string.firebase_url));

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
		mAuthProgressDialog = new ProgressDialog(this);
		mAuthProgressDialog.setTitle("Loading");
		mAuthProgressDialog.setMessage("Authenticating with Firebase...");
		mAuthProgressDialog.setCancelable(false);
		mAuthProgressDialog.show();

		mAuthStateListener = new Firebase.AuthStateListener() {
			@Override
			public void onAuthStateChanged(AuthData authData) {
				mAuthProgressDialog.hide();
				setAuthenticatedUser(authData);
			}
		};
        /* Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated
         * user and hide hide any login buttons */
		mFirebaseRef.addAuthStateListener(mAuthStateListener);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// if user logged in with Facebook, stop tracking their token
		if (mFacebookAccessTokenTracker != null) {
			mFacebookAccessTokenTracker.stopTracking();
		}

		// if changing configurations, stop tracking firebase session.
		mFirebaseRef.removeAuthStateListener(mAuthStateListener);
	}

	/**
	 * This method fires when any startActivityForResult finishes. The requestCode maps to
	 * the value passed into startActivityForResult.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Map<String, String> options = new HashMap<String, String>();
		if (requestCode == RC_GOOGLE_LOGIN) {
            /* This was a request by the Google API */
			if (resultCode != RESULT_OK) {
				mGoogleLoginClicked = false;
			}
			mGoogleIntentInProgress = false;
			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		} else if (requestCode == RC_TWITTER_LOGIN) {
			options.put("oauth_token", data.getStringExtra("oauth_token"));
			options.put("oauth_token_secret", data.getStringExtra("oauth_token_secret"));
			options.put("user_id", data.getStringExtra("user_id"));
			authWithFirebase("twitter", options);
		} else {
            /* Otherwise, it's probably the request by the Facebook login button, keep track of the session */
			mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        /* If a user is currently authenticated, display a logout menu */
		if (this.mAuthData != null) {
			getMenuInflater().inflate(R.menu.activity_login_facebook, menu);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			logout();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Unauthenticate from Firebase and from providers where necessary.
	 */
	private void logout() {
		if (this.mAuthData != null) {
            /* logout of Firebase */
			mFirebaseRef.unauth();
            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
             * Facebook/Google+ after logging out of Firebase. */
			if (this.mAuthData.getProvider().equals("facebook")) {
                /* Logout from Facebook */
				LoginManager.getInstance().logOut();
			} /*else if (this.mAuthData.getProvider().equals("google")) {
                *//* Logout from Google+ *//*
				if (mGoogleApiClient.isConnected()) {
					Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
					mGoogleApiClient.disconnect();
				}
			}*/
            /* Update authenticated user and show login buttons */
			setAuthenticatedUser(null);
		}
	}

	/**
	 * This method will attempt to authenticate a user to firebase given an oauth_token (and other
	 * necessary parameters depending on the provider)
	 */
	private void authWithFirebase(final String provider, Map<String, String> options) {
		if (options.containsKey("error")) {
			showErrorDialog(options.get("error"));
		} else {
			mAuthProgressDialog.show();
			if (provider.equals("twitter")) {
				// if the provider is twitter, we pust pass in additional options, so use the options endpoint
				mFirebaseRef.authWithOAuthToken(provider, options, new AuthResultHandler(provider));
			} else {
				// if the provider is not twitter, we just need to pass in the oauth_token
				mFirebaseRef.authWithOAuthToken(provider, options.get("oauth_token"), new AuthResultHandler(provider));
			}
		}
	}

	/**
	 * Once a user is logged in, take the mAuthData provided from Firebase and "use" it.
	 */
	private void setAuthenticatedUser(AuthData authData) {
		if (authData != null) {
            /* Hide all the login buttons */
			mFacebookLoginButton.setVisibility(View.GONE);
			//mGoogleLoginButton.setVisibility(View.GONE);
			//mTwitterLoginButton.setVisibility(View.GONE);
			mPasswordLoginButton.setVisibility(View.GONE);
			mAnonymousLoginButton.setVisibility(View.GONE);
			mLoggedInStatusTextView.setVisibility(View.VISIBLE);
            /* show a provider specific status text */
			String name = null;
			if (authData.getProvider().equals("facebook") || authData.getProvider().equals("google") || authData.getProvider().equals("twitter")) {
				name = (String) authData.getProviderData().get("displayName");
			} else if (authData.getProvider().equals("anonymous") || authData.getProvider().equals("password")) {
				name = authData.getUid();
			} else {
				Log.e(TAG, "Invalid provider: " + authData.getProvider());
			}
			if (name != null) {
				mLoggedInStatusTextView.setText("Logged in as " + name + " (" + authData.getProvider() + ")");
			}
		} else {
            /* No authenticated user show all the login buttons */
			mFacebookLoginButton.setVisibility(View.VISIBLE);
			//mGoogleLoginButton.setVisibility(View.VISIBLE);
			//mTwitterLoginButton.setVisibility(View.VISIBLE);
			mPasswordLoginButton.setVisibility(View.VISIBLE);
			mAnonymousLoginButton.setVisibility(View.VISIBLE);
			mLoggedInStatusTextView.setVisibility(View.GONE);
		}
		this.mAuthData = authData;
        /* invalidate options menu to hide/show the logout button */
		supportInvalidateOptionsMenu();
	}

	/**
	 * Show errors to users
	 */
	private void showErrorDialog(String message) {
		new AlertDialog.Builder(this)
				.setTitle("Error")
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, null)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

	/**
	 * Utility class for authentication results
	 */
	private class AuthResultHandler implements Firebase.AuthResultHandler {

		private final String provider;

		public AuthResultHandler(String provider) {
			this.provider = provider;
		}

		@Override
		public void onAuthenticated(AuthData authData) {
			mAuthProgressDialog.hide();
			Log.i(TAG, provider + " auth successful");
			setAuthenticatedUser(authData);
		}

		@Override
		public void onAuthenticationError(FirebaseError firebaseError) {
			mAuthProgressDialog.hide();
			showErrorDialog(firebaseError.toString());
		}
	}

	/* ************************************
	 *             FACEBOOK               *
	 **************************************
	 */
	private void onFacebookAccessTokenChange(AccessToken token) {
		if (token != null) {
			mAuthProgressDialog.show();
			mFirebaseRef.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
		} else {
			// Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout
			if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
				mFirebaseRef.unauth();
				setAuthenticatedUser(null);
			}
		}
	}

	/* ************************************
	 *              GOOGLE                *
	 **************************************
	 */
	//region GOOGLE
	/*
    *//* A helper method to resolve the current ConnectionResult error. *//*
	private void resolveSignInError() {
		if (mGoogleConnectionResult.hasResolution()) {
			try {
				mGoogleIntentInProgress = true;
				mGoogleConnectionResult.startResolutionForResult(this, RC_GOOGLE_LOGIN);
			} catch (IntentSender.SendIntentException e) {
				// The intent was canceled before it was sent.  Return to the default
				// state and attempt to connect to get an updated ConnectionResult.
				mGoogleIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	private void getGoogleOAuthTokenAndLogin() {
		mAuthProgressDialog.show();
        *//* Get OAuth token in Background *//*
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			String errorMessage = null;

			@Override
			protected String doInBackground(Void... params) {
				String token = null;

				try {
					String scope = String.format("oauth2:%s", Scopes.PLUS_LOGIN);
					token = GoogleAuthUtil.getToken(MainActivity.this, Plus.AccountApi.getAccountName(mGoogleApiClient), scope);
				} catch (IOException transientEx) {
                    *//* Network or server error *//*
					Log.e(TAG, "Error authenticating with Google: " + transientEx);
					errorMessage = "Network error: " + transientEx.getMessage();
				} catch (UserRecoverableAuthException e) {
					Log.w(TAG, "Recoverable Google OAuth error: " + e.toString());
                    *//* We probably need to ask for permissions, so start the intent if there is none pending *//*
					if (!mGoogleIntentInProgress) {
						mGoogleIntentInProgress = true;
						Intent recover = e.getIntent();
						startActivityForResult(recover, RC_GOOGLE_LOGIN);
					}
				} catch (GoogleAuthException authEx) {
                    *//* The call is not ever expected to succeed assuming you have already verified that
                     * Google Play services is installed. *//*
					Log.e(TAG, "Error authenticating with Google: " + authEx.getMessage(), authEx);
					errorMessage = "Error authenticating with Google: " + authEx.getMessage();
				}
				return token;
			}

			@Override
			protected void onPostExecute(String token) {
				mGoogleLoginClicked = false;
				if (token != null) {
                    *//* Successfully got OAuth token, now login with Google *//*
					mFirebaseRef.authWithOAuthToken("google", token, new AuthResultHandler("google"));
				} else if (errorMessage != null) {
					mAuthProgressDialog.hide();
					showErrorDialog(errorMessage);
				}
			}
		};
		task.execute();
	}

	@Override
	public void onConnected(final Bundle bundle) {
        *//* Connected with Google API, use this to authenticate with Firebase *//*
		getGoogleOAuthTokenAndLogin();
	}


	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!mGoogleIntentInProgress) {
            *//* Store the ConnectionResult so that we can use it later when the user clicks on the Google+ login button *//*
			mGoogleConnectionResult = result;

			if (mGoogleLoginClicked) {
                *//* The user has already clicked login so we attempt to resolve all errors until the user is signed in,
                 * or they cancel. *//*
				resolveSignInError();
			} else {
				Log.e(TAG, result.toString());
			}
		}
	}

	@Override
	public void onConnectionSuspended(int i) {
		// ignore
	}
*/
	//endregion

	/* ************************************
	 *               TWITTER              *
	 **************************************
	 */
	//region TWITTER
	/*
	private void loginWithTwitter() {
		startActivityForResult(new Intent(this, TwitterOAuthActivity.class), RC_TWITTER_LOGIN);
	}
	*/
	//endregion

	/* ************************************
	 *              PASSWORD              *
	 **************************************
	 */
	public void loginWithPassword() {
		mAuthProgressDialog.show();
		mFirebaseRef.authWithPassword("test@firebaseuser.com", "test1234", new AuthResultHandler("password"));
	}

	/* ************************************
	 *             ANONYMOUSLY            *
	 **************************************
	 */
	private void loginAnonymously() {
		mAuthProgressDialog.show();
		mFirebaseRef.authAnonymously(new AuthResultHandler("anonymous"));
	}
}

