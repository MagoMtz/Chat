package com.magomtz.android.magochat.chat;

import com.magomtz.android.magochat.chat.events.ChatEvent;
import com.magomtz.android.magochat.chat.ui.ChatView;
import com.magomtz.android.magochat.entities.ChatMessage;
import com.magomtz.android.magochat.entities.User;
import com.magomtz.android.magochat.lib.EventBus;
import com.magomtz.android.magochat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Mago on 03/07/2016.
 */
public class ChatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view=view;
        this.eventBus= GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);

    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view=null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null){
            ChatMessage msg = event.getMessage();
            view.onMessageReceived(msg);
        }
    }


}
