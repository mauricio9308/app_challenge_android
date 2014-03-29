package com.app.appchallenge.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.app.appchallenge.R;
import com.app.appchallenge.model.Subject;
import com.app.appchallenge.model.Suggestion;

/**
 * {@link Fragment} that list the given subjects by the service as recommendations 
 * 
 * @author Heisenbugs
 * */
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setTitle(
				getActivity().getString(R.string.suggestion_fragment_title));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		
		View view = inflater.inflate(R.layout.fragment_suggestion, container,
				false/* attachToRoot */);

		StickyListHeadersListView suggestionList = (StickyListHeadersListView) view
				.findViewById(android.R.id.list);

		mSuggestion = getSuggestionFromArgs();
		
		
//		container.fin
		SuggestionListAdapter adapter = new SuggestionListAdapter(
				mSuggestion.getSubjects(), getActivity());

		suggestionList.setAdapter(adapter);
		suggestionList.setOnItemClickListener(SuggestionListFragment.this);

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mCallback.onSubjectClick(position);
	}

	/**
	 * Base adapter for the suggestion list of the SuggestionListFragment
	 * 
	 * @author Heisenbugs
	 * */
	public class SuggestionListAdapter extends BaseAdapter implements
			StickyListHeadersAdapter {

		private final LayoutInflater mInflater;
		private final Subject[] mSubjects;

		public SuggestionListAdapter(Subject[] subjects, Context context) {
			mSubjects = subjects;
			mInflater = LayoutInflater.from(context);
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

				holder.name = (TextView) convertView
						.findViewById(R.id.txtView_subject_name);
				holder.professor = (TextView) convertView
						.findViewById(R.id.txtView_subject_professor);
				holder.situation = (TextView) convertView.findViewById(R.id.txtView_subject_situation); 
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final Subject positionSubject = mSubjects[position];
			holder.name.setText(positionSubject.getName());
			holder.professor.setText(positionSubject.getProfessor());

			return convertView;
		}

		/**
		 * Part of the view holder pattern, holds the reference for the list given view
		 * */
		class ViewHolder {
			public TextView name;
			public TextView situation; 
			public TextView professor;
		}

		@Override
		public View getHeaderView(int position, View convertView,
				ViewGroup parent) {

			final HeaderHolder headerHolder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.header_events, parent,
						false /* attachToRoot */);

				headerHolder = new HeaderHolder();
				headerHolder.text = (TextView) convertView
						.findViewById(android.R.id.text1);

				convertView.setTag(headerHolder);

			} else {
				headerHolder = (HeaderHolder) convertView.getTag();
			}

			Subject positionSubject = mSubjects[position];
			headerHolder.text.setText(positionSubject.getSituation() );

			return convertView;
		}


		/**
		 * Part of the view holder pattern, holds the reference for the given header view
		 * */
		class HeaderHolder {
			public TextView text;
		}

		/**
		 * Gives an identifier for the given header section
		 * */
		@Override
		public long getHeaderId(int position) {
			Subject positionSubject = mSubjects[position];
			return 1; 
		}
	}

}
