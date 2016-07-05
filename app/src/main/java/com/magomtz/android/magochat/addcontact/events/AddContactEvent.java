package com.magomtz.android.magochat.addcontact.events;

/**
 * Created by Mago on 03/07/2016.
 */
public class AddContactEvent {
    boolean error=false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
