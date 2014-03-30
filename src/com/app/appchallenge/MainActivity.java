package com.app.appchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.app.appchallenge.fragment.SubmitRetainFragment;
import com.app.appchallenge.fragment.SubmitRetainFragment.SubmitCallbacks;
import com.app.appchallenge.fragment.dialog.LoadingDialogFragment;
import com.app.appchallenge.model.Hour;
import com.app.appchallenge.model.Schedule;
import com.app.appchallenge.model.Subject;
import com.app.appchallenge.model.Suggestion;
import com.app.appchallenge.model.User;

/**
 * Main activity where the user introduces his daily schedule for the
 * application service.
 * 
 * 
 * @author Heisenbugs
 * */
public class MainActivity extends SherlockFragmentActivity implements
		SubmitCallbacks {

	public static final String INTENT_USER = "intent_user";

	private static final String FRAG_TAG_SUBMIT_FRAGMENT = "frag_tag_submit_fragment";
	private static final String FRAG_TAG_SUBMIT_LOADING_DIALOG = "frag_tag_submit_loading_dialog";

	private TimePicker mBeginPicker;
	private TimePicker mEndPicker;
	private User mUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mBeginPicker = (TimePicker) findViewById(R.id.begin_time);
		mEndPicker = (TimePicker) findViewById(R.id.end_time);
		mUser = getIntent().getParcelableExtra(INTENT_USER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_submit:
			submitAction();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Method that invokes the {@link SubmitRetainFragment} for the suggestion
	 * petition to the Web service
	 * */
	private void submitAction() {
		Hour beginHour = new Hour(mBeginPicker.getCurrentHour(),
				mBeginPicker.getCurrentMinute());
		Hour endHour = new Hour(mEndPicker.getCurrentHour(),
				mEndPicker.getCurrentMinute());

		if (isFirstHourAfterSecond(beginHour, endHour)) {
			Toast.makeText(MainActivity.this, R.string.verify_the_hours,
					Toast.LENGTH_SHORT).show();
			return;
		}

		 Schedule schedule = new Schedule(1, "11:30", "11:50");
		 Schedule schedule1 = new Schedule(2, "11:30", "11:50");
		 Schedule schedule4 = new Schedule(4, "11:30", "11:50");
		
		
		 Subject s0 = new Subject("dedeededr", "Profesor a",
		 new Schedule[] { schedule, schedule1, schedule4 }, "LOL");
		
		 Subject s2 = new Subject("LOL", "Profesor a",
		 new Schedule[] { schedule, schedule1}, "LOL");
		
		 Subject s3 = new Subject("dedeededr", "Profesor a",
		 new Schedule[] { schedule, schedule1, schedule4 }, "LOL");
		
		 Subject s34 = new Subject("dedeededr", "Profesor a",
		 new Schedule[] { schedule, schedule1, schedule4 }, "LOL");
		
		 Suggestion suggestion = new Suggestion(" Semestre Enero-Junio 2014",
		 2.5, new Subject[] { s2, s3, s0, s34 });
		
		 Intent suggestionIntent = new Intent(MainActivity.this,
		 SuggestionActivity.class);
		 suggestionIntent.putExtra(SuggestionActivity.INTENT_SUGGESTION,
		 suggestion);
		 startActivity(suggestionIntent);

//		SubmitRetainFragment submit = SubmitRetainFragment.newInstance(mUser,
//				beginHour, endHour);
//		FragmentManager man = getSupportFragmentManager();
//		man.beginTransaction().add(submit, FRAG_TAG_SUBMIT_FRAGMENT).commit();
//
//		showFetchingDialog();
	}

	private void showFetchingDialog() {

		DialogFragment loadingDialog = LoadingDialogFragment
				.newInstance(getString(R.string.obtaining_suggestions));
		loadingDialog.show(getSupportFragmentManager(),
				FRAG_TAG_SUBMIT_LOADING_DIALOG);
	}

	private void deleteFetchingDialog() {
		DialogFragment dialog = (DialogFragment) getSupportFragmentManager()
				.findFragmentByTag(FRAG_TAG_SUBMIT_LOADING_DIALOG);
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	/**
	 * Method part of the {@link SubmitCallbacks} interface that communicates
	 * the MainAcitivity with the {@link SubmitRetainFragment}
	 * */
	@Override
	public void onSubmitSuccess(Suggestion suggestion) {
		Intent suggestionIntent = new Intent(MainActivity.this,
				SuggestionActivity.class);
		suggestionIntent.putExtra(SuggestionActivity.INTENT_SUGGESTION,
				suggestion);
		startActivity(suggestionIntent);

		deleteFetchingDialog();
	}

	@Override
	public void onSubmitFailure() {
		Toast.makeText(MainActivity.this, R.string.error_obtaining_suggestion,
				Toast.LENGTH_SHORT).show();

		deleteFetchingDialog();
	}

	/**
	 * Method that verifies if the first {@link Hour} given is later than the
	 * second one
	 * 
	 * @return isFirstHourAfterSecond
	 * */
	private boolean isFirstHourAfterSecond(Hour beginHour, Hour endHour) {
		if (beginHour.getHour() > endHour.getHour()) {
			return true;
		} else {
			if (beginHour.getHour() == endHour.getHour()
					&& beginHour.getMinute() > endHour.getMinute()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
