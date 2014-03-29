package com.app.appchallenge.utils;

import com.app.appchallenge.net.AppChallengeApiBaseUrl;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Class that manages the shared preferences
 * 
 * @author Heisenbugs
 * */
public class SharedPreferencesUtils {
	
	private static final String PREF = "PREF"; 
	private static final String SERVER_ADDRESS = "server_address";
	
	
	private static SharedPreferences getPreferences(Context context){
		return context.getSharedPreferences(PREF, Context.MODE_PRIVATE); 
	}
	
	public static String getAddress(Context context){
		SharedPreferences prefs = getPreferences(context); 
		return prefs.getString(SERVER_ADDRESS, AppChallengeApiBaseUrl.URL_API_BASE); 
	}
	
	public static void setAddress(Context context, String address){
		SharedPreferences prefs = getPreferences(context); 
		SharedPreferences.Editor editor = prefs.edit(); 
		editor.putString(SERVER_ADDRESS, address);
		editor.commit(); 
	}
}

