package com.example.websocketexample.model;

public class HelloMessage {
    private String value;

    public HelloMessage() {

    }

    public HelloMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
