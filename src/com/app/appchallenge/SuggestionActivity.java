package com.app.appchallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.app.appchallenge.fragment.SubjectFragment;
import com.app.appchallenge.fragment.SuggestionListFragment;
import com.app.appchallenge.fragment.SuggestionListFragment.SuggestionListCallbacks;
import com.app.appchallenge.model.Subject;
import com.app.appchallenge.model.Suggestion;

/**
 * {@link Activity} that contains the fragments {@link SuggestionListFragment} and {@link SubjectFragment} interactions. 
 * 
 * @author Heisenbugs
 * */
public class SuggestionActivity extends SherlockFragmentActivity implements
		SuggestionListCallbacks, OnBackStackChangedListener {

	public static final String INTENT_SUGGESTION = "suggestion";

	private static final String FRAG_TAG_SUGGESTION_LIST = "frag_tag_suggestion_list";
	private static final String FRAG_TAG_SUBJECT = "frag_tag_subject";

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ); 

		if( savedInstanceState == null ){
			Suggestion suggestion = ( Suggestion ) getIntent().getParcelableExtra(INTENT_SUGGESTION); 
			SuggestionListFragment listFragment = SuggestionListFragment.newInstance(suggestion); 
			
			FragmentManager man = getSupportFragmentManager(); 
			man.beginTransaction().replace(android.R.id.content, listFragment, FRAG_TAG_SUGGESTION_LIST).commit(); 
			man.addOnBackStackChangedListener( SuggestionActivity.this ); 
		}
		
	}
	
	@Override
	public void onBackStackChanged() {
		FragmentManager manager = getSupportFragmentManager();
		if(manager.getBackStackEntryCount() == 0){
			getSupportActionBar().setTitle( getString(R.string.suggestion_activity) ); 
        }else{
        }
    }

	
	/**
	 * Part of the {@link SuggestionListCallbacks} it sends the user to the detailed information of the given subject
	 * 
	 * @param position of the subject in the list
	 * */
	@Override
	public void onSubjectClick(int subjectPosition) {
		Subject selectedSubject = getSelectedSubject(subjectPosition);
		SubjectFragment subjectFragment = SubjectFragment
				.newInstance(selectedSubject);

		FragmentManager man = getSupportFragmentManager();
		man.beginTransaction()
				.add(android.R.id.content, subjectFragment, FRAG_TAG_SUBJECT)
				.addToBackStack(FRAG_TAG_SUBJECT).commit();
	}
	
	/**
	 * Method that retrieves the {@link Subject} from the given position of the suggestions array
	 * 
	 * @param The subject at the given position
	 * */
	private Subject getSelectedSubject(int selectedPosition) {
		Suggestion suggestion = (Suggestion) getIntent().getParcelableExtra(
				INTENT_SUGGESTION);
		Subject[] subjects = suggestion.getSubjects();

		return subjects[selectedPosition];
	}

}
