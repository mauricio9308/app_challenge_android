package com.app.appchallenge.net;

/**
 * Representation of the web service urls
 * 
 * @author Heisenbugs
 * */
public final class AppChallengeApiBaseUrl {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN_NAME = "safe-reaches-1149.herokuapp.com/";
    
    public static final String URL_API_BASE = PROTOCOL + "://" + DOMAIN_NAME ;
    
    private AppChallengeApiBaseUrl() {
        /* Do nothing */
    }

}
