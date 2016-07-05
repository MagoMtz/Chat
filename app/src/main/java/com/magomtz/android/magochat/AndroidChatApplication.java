package com.magomtz.android.magochat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Mago on 29/06/2016.
 */
public class AndroidChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
