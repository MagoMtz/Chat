package com.magomtz.android.magochat.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.magomtz.android.magochat.chat.events.ChatEvent;
import com.magomtz.android.magochat.domain.FirebaseHelper;
import com.magomtz.android.magochat.entities.ChatMessage;
import com.magomtz.android.magochat.lib.EventBus;
import com.magomtz.android.magochat.lib.GreenRobotEventBus;

/**
 * Created by Mago on 04/07/2016.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String receiver;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;

    public ChatRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver=receiver;
    }

    @Override
    public void subscribeForChatUpdates() {
        if(chatEventListener==null){
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();
                    msgSender = msgSender.replace("_",".");

                    String currentUserEmail = helper.getAuthUserEmail();
                    chatMessage.setSentByMe(msgSender.equals(currentUserEmail));

                    ChatEvent chatEvent = new ChatEvent(chatMessage);
                    EventBus eventBus = GreenRobotEventBus.getInstance();
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            helper.getChatsReference(receiver).addChildEventListener(chatEventListener);
        }
    }

    @Override
    public void unsubscribeForChatUpdates() {
        if (chatEventListener != null) {
            helper.getChatsReference(receiver).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyChatListener() {
        chatEventListener = null;
    }

    @Override
    public void sendMessage(String msg) {
        String keySender = helper.getAuthUserEmail().replace(".","_");
        ChatMessage chatMessage = new ChatMessage(msg, keySender);
        DatabaseReference chatsReference = helper.getChatsReference(receiver);
        chatsReference.push().setValue(chatMessage);
    }


    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
