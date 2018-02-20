package com.example.iqbalhajat.chucknorris3.models;

public class CardJoke {

    private String joke;
    private String title;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CardJoke(String title, String joke) {
        this.title = title;
        this.joke = joke;
    }
}