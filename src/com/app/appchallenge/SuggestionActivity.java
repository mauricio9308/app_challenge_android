package com.app.appchallenge;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.app.appchallenge.fragment.SubjectFragment;
import com.app.appchallenge.fragment.SuggestionListFragment;
import com.app.appchallenge.fragment.SuggestionListFragment.SuggestionListCallbacks;
import com.app.appchallenge.model.Subject;
import com.app.appchallenge.model.Suggestion;

public class SuggestionActivity extends SherlockFragmentActivity implements SuggestionListCallbacks{
	
	public static final String INTENT_SUGGESTION = "suggestion"; 
	
	private static final String FRAG_TAG_SUGGESTION_LIST = "frag_tag_suggestion_list"; 
	private static final String FRAG_TAG_SUBJECT = "frag_tag_subject"; 
	
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState ); 

		getSupportActionBar().setTitle("Sugerencia de Carga");
		
		if( savedInstanceState == null ){
			Suggestion suggestion = ( Suggestion ) getIntent().getParcelableExtra(INTENT_SUGGESTION); 
			SuggestionListFragment listFragment = SuggestionListFragment.newInstance(suggestion); 
			
			FragmentManager man = getSupportFragmentManager(); 
			man.beginTransaction().replace(android.R.id.content, listFragment, FRAG_TAG_SUGGESTION_LIST).commit(); 
		}
	}

	@Override
	public void onSubjectClick(int subjectPosition) {
		Subject selectedSubject = getSelectedSubject( subjectPosition ); 
		SubjectFragment subjectFragment = SubjectFragment.newInstance( selectedSubject ); 
		
		FragmentManager man = getSupportFragmentManager(); 
		man.beginTransaction().add(android.R.id.content, subjectFragment, FRAG_TAG_SUBJECT).commit(); 
	}
	
	private Subject getSelectedSubject( int selectedPosition ){
		Suggestion suggestion = ( Suggestion ) getIntent().getParcelableExtra(INTENT_SUGGESTION); 
		Subject[] subjects = suggestion.getSubjects();
		
		return subjects[ selectedPosition ];
	}
	
	
	
	
	

}
