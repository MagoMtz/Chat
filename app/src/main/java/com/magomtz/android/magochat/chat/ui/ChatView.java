package com.magomtz.android.magochat.chat.ui;

import com.magomtz.android.magochat.entities.ChatMessage;

/**
 * Created by Mago on 03/07/2016.
 */
public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
