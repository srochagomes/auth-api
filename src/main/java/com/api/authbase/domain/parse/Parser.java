package com.api.authbase.domain.parse;

public abstract class Parser <T,E>{
    protected T dto;
    protected E entity;


    public T asDTO(){
        return dto;
    }
    public E asEntity(){
        return entity;
    }

    protected abstract E transformDTO(T t);

    protected abstract T transformEntity(E e);


}
