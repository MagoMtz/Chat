package com.magomtz.android.magochat.contactlist;

import com.magomtz.android.magochat.contactlist.events.ContactListEvent;

/**
 * Created by Mago on 02/07/2016.
 */
public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
