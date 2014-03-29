package com.app.appchallenge.net;

import retrofit.RequestInterceptor;
import org.apache.commons.codec.binary.Base64;

import com.app.appchallenge.model.User;

/**
 * Request interceptor part of the Retrofit API, that adds a header with a basic authorization
 * 
 * @author Heisenbugs
 * */
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
		final String userAndPassword = mUser.getUsername() + ":" + mUser.getPassword();
		byte[] byteArray = Base64.encodeBase64(userAndPassword.getBytes());
		return "Basic " + new String(byteArray);
	}

}