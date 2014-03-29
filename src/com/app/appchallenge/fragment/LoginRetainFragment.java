package com.app.appchallenge.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.app.appchallenge.BuildConfig;
import com.app.appchallenge.model.Message;
import com.app.appchallenge.model.User;
import com.app.appchallenge.net.AppChallengeApiInterface;
import com.app.appchallenge.net.AppChallengeApiSingleton;
import com.app.appchallenge.net.RestError;

/**
 * Retain {@link Fragment} class that we use to encapsulate the login petition
 * made by an AsyncTask from configuration changes
 * 
 * @author Heisenbugs
 * */
public class LoginRetainFragment extends Fragment {

	private static final String TAG = LoginRetainFragment.class.getSimpleName();

	private static final String FRAG_ARG_USER = "frag_arg_user";

	private LoginCallbacks mCallbacks;

	public interface LoginCallbacks {
		public void onLoginRespose(String message, int status );
	}

	public LoginRetainFragment() {
		/* Default constructor */
	}

	public static LoginRetainFragment newInstance(User user) {
		Bundle args = new Bundle();
		args.putParcelable(FRAG_ARG_USER, user);

		LoginRetainFragment frag = new LoginRetainFragment();
		frag.setArguments(args);

		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);

		if (savedInstanceState == null) {
			new LoginTask().execute(new User[] { getUsernameFromArgs() });
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mCallbacks = (LoginCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Class " + getClass().getSimpleName()
					+ " must implement " + LoginCallbacks.class.getSimpleName());
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();

		mCallbacks = null;
	}

	private User getUsernameFromArgs() {
		return getArguments().getParcelable(FRAG_ARG_USER);
	}

	/**
	 * AsycnTask that makes the login petition via the application RestModule
	 * 
	 * @Param User The object that carries the user data
	 * @return Simple response code that it's going to be processed by the
	 *         proper Activity
	 * */
	public class LoginTask extends AsyncTask<User, Void, Void> {

		@Override
		protected Void doInBackground(User... params) {
			AppChallengeApiInterface apiInterface = AppChallengeApiSingleton
					.getApiInterfaceInstance(params[0], getActivity());
			try {
				Log.d("TAG", "username: " + params[0].getUsername());
				Log.d("TAG", "password: " + params[0].getPassword());
				
				apiInterface.login(new Callback<Message>() {

					@Override
					public void success(Message message, Response arg1) {
						Log.d("TAG", message.getMessage() );
						mCallbacks.onLoginRespose( message.getMessage(), arg1.getStatus() );
					}

					@Override
					public void failure(RetrofitError error) {
						if (error.getResponse() != null) {
							RestError body = (RestError) error
									.getBodyAs(RestError.class);
							error.printStackTrace();
							
							mCallbacks.onLoginRespose( body.toString(), 402 );
						} else {
							mCallbacks.onLoginRespose("Error conectando a servicio", 400);
						}
					}
				});

			} catch (RetrofitError e) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG,
							"Error doing login "
									+ Integer.toString(e.getResponse()
											.getStatus()));
				}
				e.printStackTrace();
				mCallbacks.onLoginRespose( "Error conectando a servicio", 400 );
			}

			return null;
		}

	}

}
