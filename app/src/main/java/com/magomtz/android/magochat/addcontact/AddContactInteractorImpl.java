package com.magomtz.android.magochat.addcontact;

/**
 * Created by Mago on 03/07/2016.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository repository;

    public AddContactInteractorImpl() {
        this.repository = new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
