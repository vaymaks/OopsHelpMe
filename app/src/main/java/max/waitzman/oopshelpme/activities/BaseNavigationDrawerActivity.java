package max.waitzman.oopshelpme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;
import max.waitzman.oopshelpme.utils.LogUtil;

public class BaseNavigationDrawerActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	TextView tvUserFullName, tvUserEmail;
	ImageView ivUserPicture;
	NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_navigation_drawer);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null)
						.show();
			}
		});

		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();


		//NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		//navigationView.setNavigationItemSelectedListener(this);
		setNavigationHeader();
		setUserProfile("");

	}

	/*
        Set Navigation header by using Layout Inflater.
     */
	public void setNavigationHeader(){
		navigationView = (NavigationView) findViewById(R.id.nav_view);


		View header = navigationView.getHeaderView(0);
		//View header = LayoutInflater.from(this).inflate(R.layout.nav_header_base_navigation_drawer, null);
		//navigationView.addHeaderView(header);

		tvUserFullName = (TextView) header.findViewById(R.id.tvUserFullName);
		ivUserPicture = (ImageView) header.findViewById(R.id.ivUserPicture);
		tvUserEmail = (TextView) header.findViewById(R.id.tvUserEmail);

		navigationView.setNavigationItemSelectedListener(this);

		//ivUserPicture.setImageDrawable(getResources().getDrawable(R.drawable.com_facebook_profile_picture_blank_square));
	}

	/*
       Set User Profile Information in Navigation Bar.
     */
	public  void  setUserProfile(String jsondata){
		Firebase firebase = ApplicationMy.getFirebase();
		AuthData authData= firebase.getAuth();
		LogUtil.e(firebase.getAuth() + "");
		try {
			//response = new JSONObject(jsondata);
			//tvUserEmail.setText(response.get("email").toString());
			tvUserEmail.setText((String)authData.getProviderData().get("email"));

			//tvUserFullName.setText(response.get("name").toString());
			tvUserFullName.setText((String)authData.getProviderData().get("displayName"));

			//profile_pic_data = new JSONObject(response.get("picture").toString());
			//profile_pic_url = new JSONObject(profile_pic_data.getString("data"));


			//Picasso.with(this).load(profile_pic_url.getString("url")).into(ivUserPicture);
			Picasso.with(this).load((String) authData.getProviderData().get("profileImageURL")).into(ivUserPicture);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_navigation_drawer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			Intent intent = new Intent(this, RescuesActivity.class);
			startActivity(intent);
			//finish();

		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.navigation_drawer_logout) {
			logout();
			Intent intent = new Intent(BaseNavigationDrawerActivity.this , LoginActivity.class);
			startActivity(intent);
			finish();

		}

		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	private void logout() {
		Firebase firebase = ApplicationMy.getFirebase();
		AuthData authData = firebase.getAuth();
		if (authData != null) {
            /* logout of Firebase */
			firebase.unauth();
            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into Facebook/Google+ after logging out of Firebase. */
			if (authData.getProvider().equals("facebook")) {
                /* Logout from Facebook */
				LoginManager.getInstance().logOut();
			}
		}
	}
}
