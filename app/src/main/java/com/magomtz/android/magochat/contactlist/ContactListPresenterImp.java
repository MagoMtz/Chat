package com.magomtz.android.magochat.contactlist;

import com.magomtz.android.magochat.contactlist.events.ContactListEvent;
import com.magomtz.android.magochat.contactlist.ui.ContactListView;
import com.magomtz.android.magochat.entities.User;
import com.magomtz.android.magochat.lib.EventBus;
import com.magomtz.android.magochat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Mago on 02/07/2016.
 */
public class ContactListPresenterImp implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView view;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImp(ContactListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.sessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch(event.getEvenType()){
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user){
        if(view!=null){
            view.onContactAdded(user);
        }
    }

    private void onContactChanged(User user){
        if(view!=null){
            view.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user){
        if(view!=null){
            view.onContactRemoved(user);
        }
    }
}
