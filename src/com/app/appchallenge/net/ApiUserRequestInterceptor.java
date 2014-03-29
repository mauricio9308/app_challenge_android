package com.app.appchallenge.net;

import retrofit.RequestInterceptor;
import android.util.Base64;

import com.app.appchallenge.model.User;

public class ApiUserRequestInterceptor implements RequestInterceptor {

	private User mUser;

	public ApiUserRequestInterceptor(User user) {
		mUser = user;
	}

	@Override
	public void intercept(RequestFacade requestFacade) {
		if (mUser != null) {
			final String authorizationValue = encodeCredentialsForBasicAuthorization();
			requestFacade.addHeader("Authorization", authorizationValue);
		}
	}

	private String encodeCredentialsForBasicAuthorization() {
		final String userAndPassword = mUser.getUsername() + ":"
				+ mUser.getPassword();
		final int flags = 0;
		return "Basic "
				+ Base64.encodeToString(userAndPassword.getBytes(), flags);
	}

}