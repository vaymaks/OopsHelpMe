package max.waitzman.oopshelpme.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;
import max.waitzman.oopshelpme.models.Rescue;
import max.waitzman.oopshelpme.utils.LogUtil;
import max.waitzman.oopshelpme.views.MyViews.DividerItemDecoration;
import max.waitzman.oopshelpme.views.adapters.RescueRowListRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //RescuesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RescuesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RescuesListFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;



	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";


	private static final String TAG = RescuesListFragment.class.getSimpleName();
	private List<Rescue> rescuesList;
	private RecyclerView mRecyclerView;
	private RescueRowListRecyclerViewAdapter rescueRowListRecyclerViewAdapter;
	private ProgressBar progressBar;



	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static RescuesListFragment newInstance(int sectionNumber) {
		RescuesListFragment fragment = new RescuesListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment RescuesListFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static RescuesListFragment newInstance(String param1, String param2) {
		RescuesListFragment fragment = new RescuesListFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}


	public RescuesListFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		textView.setText(R.string.hello_blank_fragment);
		return textView;
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_rescues, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

		// Initialize recycler view
		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST);
		//RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
		mRecyclerView.addItemDecoration(itemDecoration);

		// Code to Add an item with default animation
		//((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

		// Code to remove an item with default animation
		//((MyRecyclerViewAdapter) mAdapter).deleteItem(index);

		progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
		progressBar.setVisibility(View.VISIBLE);

		return rootView;
	}

	/**
	 * Called when the fragment's activity has been created and this
	 * fragment's view hierarchy instantiated.  It can be used to do final
	 * initialization once these pieces are in place, such as retrieving
	 * views or restoring state.  It is also useful for fragments that use
	 * {@link #setRetainInstance(boolean)} to retain their instance,
	 * as this callback tells the fragment when it is fully associated with
	 * the new activity instance.  This is called after {@link #onCreateView}
	 * and before {@link #onViewStateRestored(Bundle)}.
	 *
	 * @param savedInstanceState If the fragment is being re-created from
	 *                           a previous saved state, this is the state.
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);


		// Downloading data from below url
		//final String url = "http://javatechig.com/?json=get_recent_posts&count=45";
		//new AsyncHttpTask().execute(url);


		//// read from firebase////

		Firebase ref = ApplicationMy.getFirebase();
		ref.child("rescues").
				addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						System.out.println(snapshot.getValue());
						rescuesList = new ArrayList<Rescue>();
						for (DataSnapshot postSnapshot : snapshot.getChildren()) {
							Rescue post = postSnapshot.getValue(Rescue.class);
							Log.e("rescue !!!!!!!", post + "");
							rescuesList.add(post);
						}

						progressBar.setVisibility(View.GONE);
						if (rescuesList.size() >= 1) {
							rescueRowListRecyclerViewAdapter = new RescueRowListRecyclerViewAdapter(getContext(), rescuesList);
							mRecyclerView.setAdapter(rescueRowListRecyclerViewAdapter);


							rescueRowListRecyclerViewAdapter.setOnItemClickListener(new
																							RescueRowListRecyclerViewAdapter.ClickListener() {
																								@Override
																								public void onItemClick(int position, View v) {
																									LogUtil.e(" Clicked on Item " + position);
																								}
																							});
						} else {
							Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
						}
					}


					@Override
					public void onCancelled(FirebaseError firebaseError) {
						System.out.println("The read failed: " + firebaseError.getMessage());
					}

				});


	}

	/**
	 * Called when the fragment is visible to the user and actively running.
	 * This is generally
	 * tied to {@link Activity#onResume() Activity.onResume} of the containing
	 * Activity's lifecycle.
	 */
	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		/*if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
		}*/
	}

	@Override
	public void onDetach() {
		super.onDetach();
		//mListener = null;
	}




	//region OnFragmentInteractionListener
	/*private OnFragmentInteractionListener mListener;

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	*//**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 *//*
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}*/
	//endregion




	public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected void onPreExecute() {
			//setProgressBarIndeterminateVisibility(true);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Integer doInBackground(String... params) {
			Integer result = 0;
			HttpURLConnection urlConnection;
			try {
				URL url = new URL(params[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				int statusCode = urlConnection.getResponseCode();

				// 200 represents HTTP OK
				if (statusCode == 200) {
					BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = r.readLine()) != null) {
						response.append(line);
					}
					parseResult(response.toString());
					result = 1; // Successful
				} else {
					result = 0; //"Failed to fetch data!";
				}
			} catch (Exception e) {
				LogUtil.d( e.getLocalizedMessage());
			}
			return result; //"Failed to fetch data!";
		}

		@Override
		protected void onPostExecute(Integer result) {
			// Download complete. Let us update UI
			progressBar.setVisibility(View.GONE);

			if (result == 1) {
				rescueRowListRecyclerViewAdapter = new RescueRowListRecyclerViewAdapter(getContext(), rescuesList);
				mRecyclerView.setAdapter(rescueRowListRecyclerViewAdapter);


				rescueRowListRecyclerViewAdapter.setOnItemClickListener(new
						                                                        RescueRowListRecyclerViewAdapter.ClickListener() {
							                                                        @Override
							                                                        public void onItemClick(int position, View v) {
								                                                        LogUtil.e(" Clicked on Item " + position);
							                                                        }
						                                                        });
			} else {
				Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
			}
		}

		private void parseResult(String result) {
			try {
				JSONObject response = new JSONObject(result);
				JSONArray posts = response.optJSONArray("posts");
				rescuesList = new ArrayList<>();

				for (int i = 0; i < posts.length(); i++) {
					JSONObject post = posts.optJSONObject(i);
					Rescue rescue = new Rescue();
					rescue.setTitle(post.optString("title"));
					rescue.setDescription(post.optString("thumbnail"));

					rescuesList.add(rescue);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}



}
