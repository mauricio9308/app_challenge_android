package com.app.appchallenge.net;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import com.app.appchallenge.model.Suggestion;

public interface AppChallengeApiInterface {
	
	@FormUrlEncoded
	@POST("/sugerencia")
	Suggestion getSuggestion(@Field("hora_inicio") String mBeginHour,
			@Field("hora_final") String mEndHour);
	
	@POST("/user/login")
	Response login();
}
