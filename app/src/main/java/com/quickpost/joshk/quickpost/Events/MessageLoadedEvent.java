package com.quickpost.joshk.quickpost.Events;

/**
 * Created by GIGABYTE-U on 10/1/2017.
 *
 * Event for eventbus when the messages have been loaded from the server
 */

public class MessageLoadedEvent {

    public final String message;

    public MessageLoadedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
