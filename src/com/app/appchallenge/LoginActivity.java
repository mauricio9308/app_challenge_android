package com.app.appchallenge;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class LoginActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login );
	}
	
	private void goToProfileActivity(){
		Intent mainActivity = new Intent( LoginActivity.this, LoginActivity.class); 
		startActivity(mainActivity); 
		finish(); 
	}
	
	

	

	

}
