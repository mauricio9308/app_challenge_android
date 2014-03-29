package com.app.appchallenge.net;

public final class AppChallengeApiBaseUrl {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN_NAME = "server.touchtastic.mx:85/pacifico_calendar_dev";
    
    public static final String URL_API_BASE = PROTOCOL + "://" + DOMAIN_NAME + "/";
    
    private AppChallengeApiBaseUrl() {
        /* Do nothing */
    }

}
