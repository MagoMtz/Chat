package com.magomtz.android.magochat.contactlist.events;

import com.magomtz.android.magochat.entities.User;

/**
 * Created by Mago on 02/07/2016.
 */
public class ContactListEvent {
    public final static int onContactAdded=0;
    public final static int onContactChanged=1;
    public final static int onContactRemoved=2;

    private User user;
    private int evenType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEvenType() {
        return evenType;
    }

    public void setEvenType(int evenType) {
        this.evenType = evenType;
    }
}
