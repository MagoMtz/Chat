package com.magomtz.android.magochat.chat;

/**
 * Created by Mago on 03/07/2016.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
