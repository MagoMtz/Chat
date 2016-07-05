package com.magomtz.android.magochat.chat;

/**
 * Created by Mago on 03/07/2016.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setReceiver(String receiver);

    void destroyChatListener();
    void subscribeForChatUpdates();
    void unsubscribeForChatUpdates();
    void changeConnectionStatus(boolean online);
}
