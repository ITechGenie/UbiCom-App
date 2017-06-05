package com.itechgenie.apps.ubicomadmin.utils;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

public class ITGException extends Exception {

    public ITGException(String message, Throwable e) {
        super(message, e);
    }

    public ITGException(String message) {
        super(message);
    }

    public ITGException(Throwable e) {
        super(e);
    }

}
