package com.magomtz.android.magochat.login;

/**
 * Created by Mago on 29/06/2016.
 */
public interface LoginInteractor {
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
    void checkAlreadyAuthenticated();
}
