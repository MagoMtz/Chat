package com.magomtz.android.magochat.chat.events;

import com.magomtz.android.magochat.entities.ChatMessage;

/**
 * Created by Mago on 03/07/2016.
 */
public class ChatEvent {
    ChatMessage message;

    public ChatEvent(ChatMessage message) {
        this.message = message;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
