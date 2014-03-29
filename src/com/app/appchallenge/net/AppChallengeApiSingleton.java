package com.app.appchallenge.net;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import android.content.Context;
import android.util.Log;

import com.app.appchallenge.model.User;
import com.app.appchallenge.utils.SharedPreferencesUtils;
import com.squareup.okhttp.OkHttpClient;

/**
 * Singleton that encapsulates the web service interactions of the application
 * 
 * @author Heisenbugs
 * */
public final class AppChallengeApiSingleton {

	private static AppChallengeApiInterface sAppChallengeApiInterface;

	private AppChallengeApiSingleton() {
		/* Do nothing */
	}

	/**
	 * Retrieves the instance of the web service connection class
	 * */
	public synchronized static final AppChallengeApiInterface getApiInterfaceInstance(
			User user, Context context) {
		if (sAppChallengeApiInterface == null) {
			ApiUserRequestInterceptor requestInterceptor = new ApiUserRequestInterceptor(
					user);

			// We retrieve the saved url
			// String api_url = AppChallengeApiBaseUrl.URL_API_BASE; //
			String api_url = SharedPreferencesUtils.getAddress(context);
			Log.d("TAG", api_url);

			final RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(api_url)
					.setRequestInterceptor(requestInterceptor)
					.setClient(new OkClient(new OkHttpClient())).build();
			sAppChallengeApiInterface = restAdapter
					.create(AppChallengeApiInterface.class);
		}
		return sAppChallengeApiInterface;
	}

}
