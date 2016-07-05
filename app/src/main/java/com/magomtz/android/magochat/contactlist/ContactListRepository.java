package com.magomtz.android.magochat.contactlist;

/**
 * Created by Mago on 02/07/2016.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvent();
    void unsubscribeToContactListEvent();
    void changeConnectionStatus(boolean online);
}
