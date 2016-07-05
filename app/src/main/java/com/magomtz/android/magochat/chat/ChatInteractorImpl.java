package com.magomtz.android.magochat.chat;

/**
 * Created by Mago on 04/07/2016.
 */
public class ChatInteractorImpl implements ChatInteractor {
    ChatRepository repository;

    public ChatInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessage(String msg) {
        repository.sendMessage(msg);
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setReceiver(recipient);
    }

    @Override
    public void subscribe() {
        repository.subscribeForChatUpdates();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeForChatUpdates();
    }

    @Override
    public void destroyListener() {
        repository.destroyChatListener();
    }
}
