package com.app.appchallenge.fragment;

import retrofit.RetrofitError;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.app.appchallenge.model.Hour;
import com.app.appchallenge.model.Suggestion;
import com.app.appchallenge.model.User;
import com.app.appchallenge.net.AppChallengeApiInterface;
import com.app.appchallenge.net.AppChallengeApiSingleton;

/**
 * Retain Fragment class that we use to encapsulate the petition of suggestion a new Suggestion made by an AsyncTask
 * from configuration changes 
 * 
 * @author Heisenbugs 
 * */
public class SubmitRetainFragment extends Fragment {

	private static final String TAG = SubmitRetainFragment.class.getSimpleName(); 
	
	private static final String FRAG_ARG_USER = "frag_arg_user"; 
	private static final String FRAG_ARG_BEGIN_HOUR = "frag_arg_begin_hour"; 
	private static final String FRAG_ARG_END_HOUR = "frag_arg_end_hour"; 
	
	private SubmitCallbacks mCallbacks;
	
	public interface SubmitCallbacks{
		public void onSubmitSuccess( Suggestion suggestion ); 
		public void onSubmitFailure(); 
	}
	
	public SubmitRetainFragment(){
		/*Default constructor*/
	}
	
	public static SubmitRetainFragment newInstance( User user, Hour beginHour, Hour endHour ){
		Bundle args = new Bundle(); 
		args.putParcelable(FRAG_ARG_USER, user ); 
		args.putParcelable(FRAG_ARG_BEGIN_HOUR, beginHour); 
		args.putParcelable(FRAG_ARG_END_HOUR, endHour); 
		
		SubmitRetainFragment frag = new SubmitRetainFragment(); 
		frag.setArguments(args); 
		
		return frag; 
	}
	
	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate(savedInstanceState); 
		
		setRetainInstance( true );
		
		if( savedInstanceState == null ){
			new SubmitTask( ).execute( new Object[]{ getUserFromArgs(), getBeginHourFromArgs(), getEndHourFromArgs() } ); 
		} 
	}
	
	@Override
	public void onAttach( Activity activity ){
		super.onAttach(activity); 
		
		try{
			mCallbacks = ( SubmitCallbacks ) activity;
		} catch ( ClassCastException e ){
			throw new ClassCastException("Class "+ getClass().getSimpleName() + " must implement " + SubmitCallbacks.class.getSimpleName() );
		}
	}
	
	private User getUserFromArgs(){
		return getArguments().getParcelable(FRAG_ARG_USER); 
	}
	
	private Hour getBeginHourFromArgs(){
		return getArguments().getParcelable(FRAG_ARG_BEGIN_HOUR);
	} 
	
	private Hour getEndHourFromArgs(){
		return getArguments().getParcelable(FRAG_ARG_END_HOUR); 
	}
	
	/**
	 * AsyncTask that realizes the petition to get the proper suggestion via the user credentials. 
	 * */
	public class SubmitTask extends AsyncTask<Object, Void, Suggestion>{
		
		@Override
		protected Suggestion doInBackground(Object... params) {
			try{
				AppChallengeApiInterface apiInterface = AppChallengeApiSingleton.getApiInterfaceInstance( (User)params[0], getActivity() ); 
				
				Hour beginHour = ( Hour ) params[1]; 
				Hour endHour = ( Hour ) params[2]; 
				
				return apiInterface.getSuggestion( beginHour.toString(), endHour.toString() ); 
			} catch ( RetrofitError e ){
				return null;
			}
		}
		
		protected void onPostExecute( Suggestion suggestion  ){
			if( suggestion != null ){
				mCallbacks.onSubmitSuccess(suggestion); 
			}else{
				mCallbacks.onSubmitFailure(); 
			}
		}
	}
	
}
