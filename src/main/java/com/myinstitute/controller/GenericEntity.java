package com.myinstitute.controller;

public interface GenericEntity<T extends GenericEntity> {

    // update current instance with provided data
    void update(T source);

    Long getId();

    // based on current data create new instance with new id
    T createNewInstance();
}
