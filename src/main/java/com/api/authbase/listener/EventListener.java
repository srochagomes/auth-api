package com.api.authbase.listener;

public interface EventListener<T> {

    void processEvent(T t);

}
