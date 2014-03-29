package com.app.appchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.app.appchallenge.fragment.LoginRetainFragment;
import com.app.appchallenge.fragment.LoginRetainFragment.LoginCallbacks;
import com.app.appchallenge.fragment.dialog.LoadingDialogFragment;
import com.app.appchallenge.model.User;

public class LoginActivity extends SherlockFragmentActivity implements
		View.OnClickListener, LoginCallbacks {

	private static final String FRAG_TAG_LOGIN = "frag_tag_login"; 
	private static final String FRAG_TAG_LOGIN_DIALOG = "frag_tag_loading_dialog";  
	
	private EditText mEdTxtUsername;
	private EditText mEdTxtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		getSupportActionBar().setTitle("Sugerencia de Carga");

		mEdTxtPassword = (EditText) findViewById(R.id.editTxt_password);
		mEdTxtUsername = (EditText) findViewById(R.id.editTxt_username);

		findViewById(R.id.bttn_login).setOnClickListener( LoginActivity.this ); 
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bttn_login:
			goToMainActivity(); 
//			tryLogin(mEdTxtUsername.getText().toString(), mEdTxtPassword.getText().toString());
			break;
		default:
			throw new IllegalArgumentException("Invalid id");
		}
	}

	private void tryLogin(String username, String password) {
		LoginRetainFragment loginFragment = LoginRetainFragment.newInstance( new User( username, password ));
		getSupportFragmentManager().beginTransaction().add(loginFragment, FRAG_TAG_LOGIN).commit(); 
		
		DialogFragment loadingDialog = LoadingDialogFragment.newInstance( getString(R.string.loging_in )); 
		loadingDialog.show(getSupportFragmentManager(), FRAG_TAG_LOGIN_DIALOG); 
	}

	private void goToMainActivity() {
		Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
		mainActivity.putExtra(MainActivity.INTENT_USER, new User( mEdTxtUsername.getText().toString(), mEdTxtPassword.getText().toString() ) ); 		
		startActivity(mainActivity);
		finish();
	}
	

	private final int LOGIN_ERROR = 402;
	private final int LOGIN_SUCCESS = 200; 
	private final int CONNECTION_ERROR = 400; 

	@Override
	public void onLoginRespose(int resultCode) {
		switch( resultCode ){
		case LOGIN_ERROR: 
			Toast.makeText( LoginActivity.this, R.string.error_credentials, Toast.LENGTH_SHORT).show(); 
			break; 
		case LOGIN_SUCCESS: 
			goToMainActivity(); 
			break; 
		case CONNECTION_ERROR:
			Toast.makeText( LoginActivity.this, R.string.error_no_connection, Toast.LENGTH_SHORT).show(); 
			break; 
		default:
			break;
		}
	}

}
