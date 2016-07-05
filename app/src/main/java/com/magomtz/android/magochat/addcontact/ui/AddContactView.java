package com.magomtz.android.magochat.addcontact.ui;

/**
 * Created by Mago on 03/07/2016.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
