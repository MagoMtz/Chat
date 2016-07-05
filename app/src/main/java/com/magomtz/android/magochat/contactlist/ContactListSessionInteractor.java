package com.magomtz.android.magochat.contactlist;

/**
 * Created by Mago on 02/07/2016.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
