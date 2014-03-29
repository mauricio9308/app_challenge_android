package com.app.appchallenge.fragment;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.app.appchallenge.BuildConfig;
import com.app.appchallenge.model.User;
import com.app.appchallenge.net.AppChallengeApiInterface;
import com.app.appchallenge.net.AppChallengeApiSingleton;
import com.app.appchallenge.utils.AsyncTaskUtils;

/**
 * Simple retaint fragment for protecting the asynctask from configuration changes
 * */
public class LoginRetainFragment extends Fragment {

	private static final String TAG = LoginRetainFragment.class.getSimpleName(); 
	
	private static final String FRAG_ARG_USER = "frag_arg_user"; 
	
	private LoginCallbacks mCallbacks;
	
	public interface LoginCallbacks{
		public void onLoginRespose( int resultCode ); 
	}
	
	public LoginRetainFragment(){
		/*Default constructor*/
	}
	
	public static LoginRetainFragment newInstance( User user ){
		Bundle args = new Bundle(); 
		args.putParcelable(FRAG_ARG_USER, user ); 
		
		LoginRetainFragment frag = new LoginRetainFragment(); 
		frag.setArguments(args); 
		
		return frag; 
	}
	
	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate(savedInstanceState); 
		
		setRetainInstance( true );
		
		if( savedInstanceState == null ){
			AsyncTaskUtils.executeAsyncTask(new LoginTask( ), new User[]{ getUsernameFromArgs() }); 
		} 
	}
	
	@Override
	public void onAttach( Activity activity ){
		super.onAttach(activity); 
		
		try{
			mCallbacks = ( LoginCallbacks ) activity;
		} catch ( ClassCastException e ){
			throw new ClassCastException("Class "+ getClass().getSimpleName() + " must implement " + LoginCallbacks.class.getSimpleName() );
		}
	}
	
	@Override
	public void onDetach(){
		super.onDetach(); 
		
		mCallbacks = null; 
	}
	
	private User getUsernameFromArgs(){
		return getArguments().getParcelable(FRAG_ARG_USER); 
	}
	
	public class LoginTask extends AsyncTask<User, Void, Integer>{
		
		@Override
		protected Integer doInBackground(User... params) {
			AppChallengeApiInterface apiInterface = AppChallengeApiSingleton.getApiInterfaceInstance( params[0] ); 
			try{
				Response response = apiInterface.login(); 
				return response.getStatus(); 
			} catch ( RetrofitError e ){
				if( BuildConfig.DEBUG ){
					Log.d( TAG, "Error doing login " + Integer.toString( e.getResponse().getStatus() )); 
				}
				return e.getResponse().getStatus(); 
			}
		}
		
		@Override
		protected void onPostExecute( Integer resultCode ){
			mCallbacks.onLoginRespose( resultCode ); 
		}
	}

	
}
