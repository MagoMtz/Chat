package com.magomtz.android.magochat.addcontact;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.magomtz.android.magochat.addcontact.events.AddContactEvent;
import com.magomtz.android.magochat.domain.FirebaseHelper;
import com.magomtz.android.magochat.entities.User;
import com.magomtz.android.magochat.lib.EventBus;
import com.magomtz.android.magochat.lib.GreenRobotEventBus;

/**
 * Created by Mago on 03/07/2016.
 */
public class AddContactRepositoryImpl implements AddContactRepository {

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");
        FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                AddContactEvent event = new AddContactEvent();
                if(user!=null){
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();

                    DatabaseReference userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".", "_");
                    DatabaseReference reverseUserContactsReference = helper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(true);
                }else{
                    event.setError(true);
                }
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
