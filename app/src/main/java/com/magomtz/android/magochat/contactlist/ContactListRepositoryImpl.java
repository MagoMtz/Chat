package com.magomtz.android.magochat.contactlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.magomtz.android.magochat.contactlist.events.ContactListEvent;
import com.magomtz.android.magochat.domain.FirebaseHelper;
import com.magomtz.android.magochat.entities.User;
import com.magomtz.android.magochat.lib.EventBus;
import com.magomtz.android.magochat.lib.GreenRobotEventBus;

/**
 * Created by Mago on 03/07/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeToContactListEvent() {
        if(contactEventListener==null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
        }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type, user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEvenType(type);
        event.setUser(user);
        eventBus.post(event);
    }

    @Override
    public void unsubscribeToContactListEvent() {
        if(contactEventListener!=null){
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
