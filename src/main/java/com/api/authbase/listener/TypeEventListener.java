package com.api.authbase.listener;

public interface TypeEventListener<T> {

    void processEvent(T t);

}
