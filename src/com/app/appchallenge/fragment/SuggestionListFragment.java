package com.app.appchallenge.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.app.appchallenge.R;
import com.app.appchallenge.model.Subject;
import com.app.appchallenge.model.Suggestion;

public class SuggestionListFragment extends SherlockFragment implements
		OnItemClickListener {

	private static final String FRAG_ARG_SUGGESTION = "frag_arg_suggestion";

	private Suggestion mSuggestion;
	private SuggestionListCallbacks mCallback;

	public interface SuggestionListCallbacks {
		public void onSubjectClick(int subjectPosition);
	}

	public SuggestionListFragment() {
		/* Default fragment */
	}

	public static SuggestionListFragment newInstance(Suggestion suggestion) {
		Bundle args = new Bundle();
		args.putParcelable(FRAG_ARG_SUGGESTION, suggestion);

		SuggestionListFragment frag = new SuggestionListFragment();
		frag.setArguments(args);

		return frag;
	}

	private Suggestion getSuggestionFromArgs() {
		return getArguments().getParcelable(FRAG_ARG_SUGGESTION);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mCallback = (SuggestionListCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Class " + getClass().getSimpleName()
					+ " must implement "
					+ SuggestionListCallbacks.class.getSimpleName());
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();

		mCallback = null;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate(savedInstanceState); 
		
		getSherlockActivity().getSupportActionBar().setTitle( getActivity().getString( R.string.suggestion_fragment_title ) );
	} 
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_suggestion, container,
				false/* attachToRoot */);

		ListView suggestionList = (ListView) view
				.findViewById(android.R.id.list);

		mSuggestion = getSuggestionFromArgs();
		SuggestionListAdapter adapter = new SuggestionListAdapter(
				mSuggestion.getSubjects());

		suggestionList.setAdapter(adapter);
		suggestionList.setOnItemClickListener(SuggestionListFragment.this);

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mCallback.onSubjectClick(position); 
	}

	public class SuggestionListAdapter extends BaseAdapter {

		private final Subject[] mSubjects;

		public SuggestionListAdapter(Subject[] subjects) {
			mSubjects = subjects;
		}

		@Override
		public int getCount() {
			return mSubjects.length;
		}

		@Override
		public Object getItem(int position) {
			return mSubjects[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater
						.from(getActivity())
						.inflate(R.layout.list_item_subject, parent, false /* attachToRoot */);
				holder = new ViewHolder();

				holder.mName = (TextView) convertView
						.findViewById(R.id.txtView_subject_name);
				holder.mProfessor = (TextView) convertView
						.findViewById(R.id.txtView_subject_professor);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final Subject positionSubject = mSubjects[position];
			holder.mName.setText(positionSubject.getName());
			holder.mProfessor.setText(positionSubject.getProfessor());

			return convertView;
		}

		class ViewHolder {
			public TextView mName;
			public TextView mProfessor;
		}
	}

}
