package com.magomtz.android.magochat.contactlist.ui;

import com.magomtz.android.magochat.entities.User;

/**
 * Created by Mago on 02/07/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
