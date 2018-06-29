package com.padc.charleskeith.events;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class ApiErrorEvent {

    private String errorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
