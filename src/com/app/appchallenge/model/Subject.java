package com.app.appchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Representation of a subject in the system
 * 
 * @author Heisenbugs
 * */
public class Subject implements Parcelable {
	@SerializedName("nombre")
	private String mName;

	@SerializedName("profesor")
	private String mProfessor;

	@SerializedName("horarios")
	private Schedule[] mSchedules;

	@SerializedName("situacion")
	private String mSituation; 
	
	public Subject(String name, String professor, Schedule[] schedules, String situacion) {
		mName = name;
		mProfessor = professor;
		mSchedules = schedules;
		mSituation = situacion;
	}

	public Subject(Parcel in) {
		mName = in.readString();
		mProfessor = in.readString();
		mSchedules = (Schedule[]) in.createTypedArray( Schedule.CREATOR ); 
		mSituation = in.readString(); 
	}

	/*
	 * 
	 * Part of the Parcelable interface
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	public String getName() {
		return mName;
	}

	public String getProfessor() {
		return mProfessor;
	}

	public Schedule[] getSchedules() {
		return mSchedules;
	}
	
	public String getSituation() {
		return mSituation;
	}

	public static Parcelable.Creator<Subject> getCreator() {
		return CREATOR;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mProfessor);
		dest.writeTypedArray(mSchedules, 0);
		dest.writeString(mSituation); 
	}

	public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>() {

		@Override
		public Subject createFromParcel(Parcel source) {
			return new Subject(source);
		}

		@Override
		public Subject[] newArray(int size) {
			return new Subject[size];
		}
	};

}