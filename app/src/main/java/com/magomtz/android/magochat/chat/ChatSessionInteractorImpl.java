package com.magomtz.android.magochat.chat;

/**
 * Created by Mago on 04/07/2016.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository repository;

    public ChatSessionInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
