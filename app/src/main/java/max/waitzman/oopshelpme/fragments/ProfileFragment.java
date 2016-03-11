package max.waitzman.oopshelpme.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;
import max.waitzman.oopshelpme.activities.LoginActivity;
import max.waitzman.oopshelpme.activities.MainApplicationActivity;
import max.waitzman.oopshelpme.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvUserFullName, tvUserEmail;
    ImageView ivUserPicture;
    Button btLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);
        tvUserFullName = (TextView) rootView.findViewById(R.id.tvUserFullName);
        ivUserPicture = (ImageView) rootView.findViewById(R.id.ivUserPicture);
        tvUserEmail = (TextView) rootView.findViewById(R.id.tvUserEmail);
        btLogout = (Button) rootView.findViewById(R.id.action_logout);
        btLogout.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUserProfile("");
    }

    private OnFragmentInteractionListener mListener;

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.action_logout :{
                logout();
                Intent intent = new Intent(getActivity() , LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
            Picasso.with(getContext()).load((String) authData.getProviderData().get("profileImageURL")).into(ivUserPicture);

        } catch (Exception e){
            e.printStackTrace();
        }
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
