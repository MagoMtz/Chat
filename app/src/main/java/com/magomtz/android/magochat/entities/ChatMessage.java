package com.magomtz.android.magochat.entities;

import com.google.firebase.database.Exclude;

/**
 * Created by Mago on 03/07/2016.
 */
public class ChatMessage {
    String msg;
    String sender;
    @Exclude
    boolean sentByMe;

    public ChatMessage(){}

    public ChatMessage(String msg, String sender) {
        this.msg = msg;
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }

    public boolean equals(Object obj){
        boolean equal = false;
        if(obj instanceof ChatMessage){
            ChatMessage msg = (ChatMessage) obj;
            equal = this.sender.equals(msg.getSender()) && this.msg.equals(msg.getMsg()) && this.sentByMe==msg.sentByMe;
        }
        return equal;
    }
}
