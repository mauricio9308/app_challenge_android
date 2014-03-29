package com.app.appchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Suggestion implements Parcelable{

	@SerializedName("periodo")
	private String mSemester;

	@SerializedName("iam")
	private double mIam;

	@SerializedName("materias")
	private Subject[] mSubjects;

	public Suggestion( String semester, double iam, Subject[] subjects ){
		mSemester = semester; 
		mIam = iam; 
		mSubjects = subjects; 
	}
	
	public Suggestion(Parcel in) {
		mSemester = in.readString(); 
		mIam = in.readDouble(); 
		mSubjects = (Subject[]) in.createTypedArray( Subject.CREATOR ); 
	}

	
	public String getSemester() {
		return mSemester;
	}

	public double getIAM() {
		return mIam;
	}

	public Subject[] getSubjects() {
		return mSubjects;
	}
	
	/*
	 * 
	 * Part of the Parcelable interface
	 * 
	 * */
	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeString(mSemester); 
    	dest.writeDouble(mIam);
    	dest.writeTypedArray(mSubjects, 0); 
    }

    public static final Parcelable.Creator<Suggestion> CREATOR = new Parcelable.Creator<Suggestion>() {

        @Override
        public Suggestion createFromParcel(Parcel source) {
            return new Suggestion(source);
        }

        @Override
        public Suggestion[] newArray(int size) {
            return new Suggestion[size];
        }
    };
	
}	
