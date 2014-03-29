package com.app.appchallenge.net;

import com.google.gson.annotations.SerializedName;

public class RestError {
	
    @SerializedName("error")
    public String errorDetails;
    
    public String getErrorDetails() {
		return errorDetails;
	}
    
    public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
    
    @Override
    public String toString() {
    	return errorDetails;
    }
    
    
}
