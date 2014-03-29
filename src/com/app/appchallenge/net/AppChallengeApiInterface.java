package com.app.appchallenge.net;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import com.app.appchallenge.model.Message;
import com.app.appchallenge.model.Suggestion;

/**
 * Interface part of the the Retrofit that maps the given resources and their response
 * 
 * @author Heisenbugs
 * */
public interface AppChallengeApiInterface {
	
	@FormUrlEncoded
	@POST("/suggest")
	Suggestion getSuggestion(@Field("hora_inicio") String mBeginHour,
			@Field("hora_final") String mEndHour);
	
	@POST("/user/login")
	void login( Callback<Message> cb );
}
