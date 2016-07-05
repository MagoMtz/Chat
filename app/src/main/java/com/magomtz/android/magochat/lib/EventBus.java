package com.magomtz.android.magochat.lib;

/**
 * Created by Mago on 29/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
