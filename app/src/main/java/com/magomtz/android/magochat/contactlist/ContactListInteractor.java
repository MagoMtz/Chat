package com.magomtz.android.magochat.contactlist;

/**
 * Created by Mago on 02/07/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
