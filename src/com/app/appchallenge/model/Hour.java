package com.app.appchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hour implements Parcelable{
	
	private int mHour; 
	private int mMinute; 
	
	public Hour( int hour, int minute ){
		mHour = hour; 
		mMinute = minute; 
	}
	
	public Hour( Parcel in ){
		mHour = in.readInt(); 
		mMinute = in.readInt();
	}

	public int getHour() {
		return mHour;
	}

	public void setHour(int mHour) {
		this.mHour = mHour;
	}

	public int getMinute() {
		return mMinute;
	}

	public void setMinute(int mMinute) {
		this.mMinute = mMinute;
	}
	
	@Override
	public String toString(){
		return Integer.toString(mHour) + ":" + Integer.toString(mMinute); 
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
    	
    }

    public static final Parcelable.Creator<Hour> CREATOR = new Parcelable.Creator<Hour>() {

        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };
	
	
	
}
