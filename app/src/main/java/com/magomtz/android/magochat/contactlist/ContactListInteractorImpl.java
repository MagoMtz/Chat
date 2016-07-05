package com.magomtz.android.magochat.contactlist;

/**
 * Created by Mago on 03/07/2016.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepository repository;

    public ContactListInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvent();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvent();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
