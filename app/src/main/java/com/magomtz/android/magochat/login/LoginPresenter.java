package com.magomtz.android.magochat.login;

import com.magomtz.android.magochat.login.events.LoginEvent;

/**
 * Created by Mago on 29/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);

}
