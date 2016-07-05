package com.magomtz.android.magochat.contactlist.ui.adapters;

import com.magomtz.android.magochat.entities.User;

/**
 * Created by Mago on 02/07/2016.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
