package com.example.iqbalhajat.chucknorris3.models;


public class Response<T> {

    private String type;
    private T value;

    public Response() {
    }

    public Response(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
