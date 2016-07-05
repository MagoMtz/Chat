package com.magomtz.android.magochat.login;

/**
 * Created by Mago on 29/06/2016.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticaded();
}
