package com.magomtz.android.magochat.addcontact;

import com.magomtz.android.magochat.addcontact.events.AddContactEvent;

/**
 * Created by Mago on 03/07/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
