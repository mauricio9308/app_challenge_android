package com.app.appchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.app.appchallenge.R;
import com.app.appchallenge.model.Schedule;
import com.app.appchallenge.model.Subject;

/**
 * {@link Fragment} that lists the schedule of a given subject
 * 
 * @author Heisenbugs
 * */
public class SubjectFragment extends SherlockFragment {

	private static final String FRAG_ARG_SUBJECT = "frag_arg_subject";

	public SubjectFragment() {
		/* Default fragment */
	}

	public static SubjectFragment newInstance(Subject subject) {
		Bundle args = new Bundle();
		args.putParcelable(FRAG_ARG_SUBJECT, subject);

		SubjectFragment frag = new SubjectFragment();
		frag.setArguments(args);

		return frag;
	}
	
	private Subject getSubjectFromArguments(){
		return getArguments().getParcelable(FRAG_ARG_SUBJECT); 
	}
	
	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate(savedInstanceState); 
		
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		actionBar.setTitle( getSubjectFromArguments().getName() );
	} 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_subject, container,
				false/* attachToRoot */);

		
		ListView suggestionList = (ListView) view
				.findViewById(android.R.id.list);

		Subject subject = getSubjectFromArguments(); 
		
		SchedulesAdapter adapter = new SchedulesAdapter( subject.getSchedules() );
		suggestionList.setAdapter(adapter);

		TextView professor = (TextView) view.findViewById(R.id.txtView_subject_professor); 
		professor.setText( subject.getProfessor() );
		
		return view;
	}
	

	/**
	 * Base adapter for the schedule list of the SubjectFragment
	 * 
	 * @author Heisenbugs
	 * */
	public class SchedulesAdapter extends BaseAdapter {

		private final Schedule[] mSchedule;

		public SchedulesAdapter(Schedule[] schedule) {
			mSchedule = schedule;
		}

		@Override
		public int getCount() {
			return mSchedule.length;
		}

		@Override
		public Object getItem(int position) {
			return mSchedule[position];
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
						.inflate(R.layout.list_item_schedule, parent, false /* attachToRoot */);
				holder = new ViewHolder();

				holder.mScheduleDay = (TextView) convertView
						.findViewById(R.id.txtView_schedule_day);
				holder.mScheduleText = (TextView) convertView
						.findViewById(R.id.txtView_schedule_text);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final Schedule positionSchedule = mSchedule[position];
			holder.mScheduleDay.setText( positionSchedule.getDayOfTheWeek() );
			holder.mScheduleText.setText( positionSchedule.getBeginHour() + " - " + positionSchedule.getEndHour() );

			return convertView;
		}

		class ViewHolder {
			public TextView mScheduleDay;
			public TextView mScheduleText;
		}
	}


}
