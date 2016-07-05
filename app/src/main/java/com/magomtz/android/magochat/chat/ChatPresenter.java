package com.magomtz.android.magochat.chat;

import com.magomtz.android.magochat.chat.events.ChatEvent;

/**
 * Created by Mago on 03/07/2016.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
