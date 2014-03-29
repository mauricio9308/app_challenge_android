package com.app.appchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Representation of a given schedule in the system 
 * 
 * @author Heisenbugs
 * */
public class Schedule implements Parcelable{

	@SerializedName("id")
	private int mId;

	@SerializedName("hora_inicio")
	private String mBeginHour;

	@SerializedName("hora_final")
	private String mEndHour;
	
	public Schedule( int id, String beginHour, String endHour ){
		mId = id; 
		mBeginHour = beginHour;
		mEndHour = endHour; 
	}
	
	public Schedule( Parcel in ){
		mId = in.readInt(); 
		mBeginHour = in.readString(); 
		mEndHour = in.readString(); 
	} 

	public int getId() {
		return mId;
	}

	public String getBeginHour() {
		return mBeginHour;
	}

	public String getEndHour() {
		return mEndHour;
	}
	
	public String getDayOfTheWeek(){
		switch( mId ){
		case 0:
			return "Lunes"; 
		case 1:
			return "Martes"; 
		case 2:
			return "Miercoles";
		case 3:
			return "Jueves"; 
		case 4:
			return "Viernes";
		case 5:
			return "Sabado"; 
		case 6:
			return "Domingo";
		default:
			throw new IllegalArgumentException("Error incompatible day of the week");
		}
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
    	dest.writeInt(mId); 
    	dest.writeString(mBeginHour); 
    	dest.writeString(mEndHour); 
    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {

        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
	
	
}