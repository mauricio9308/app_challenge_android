package com.app.appchallenge.net;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

import com.app.appchallenge.model.User;
import com.squareup.okhttp.OkHttpClient;

public final class AppChallengeApiSingleton {

	private static AppChallengeApiInterface sAppChallengeApiInterface;

	private AppChallengeApiSingleton() {
		/* Do nothing */
	}

	public synchronized static final AppChallengeApiInterface getApiInterfaceInstance( User user ) {
		if (sAppChallengeApiInterface == null) {
			ApiUserRequestInterceptor requestInterceptor = new ApiUserRequestInterceptor( user ); 
			
			final RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(AppChallengeApiBaseUrl.URL_API_BASE)
					.setRequestInterceptor(requestInterceptor)
					.setClient(new OkClient(new OkHttpClient())).build();
			sAppChallengeApiInterface = restAdapter
					.create(AppChallengeApiInterface.class);
		}
		return sAppChallengeApiInterface;
	}

	

}
