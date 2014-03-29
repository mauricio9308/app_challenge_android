package com.app.appchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	private final String mUsername;
	private final String mPassword;

	public User(String username, String password) {
		mUsername = username;
		mPassword = password;
	}

	public User(Parcel in) {
		mUsername = in.readString(); 
		mPassword = in.readString(); 
	}

	public String getUsername() {
		return mUsername;
	}

	public String getPassword() {
		return mPassword;
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
    	dest.writeString(mUsername); 
    	dest.writeString(mPassword); 
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
