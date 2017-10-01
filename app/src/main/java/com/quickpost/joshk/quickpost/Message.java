package com.quickpost.joshk.quickpost;

/**
 * Created by GIGABYTE-U on 9/30/2017.
 */

public class Message {

    private int id;

    private String msg;

    private String user;

    private String sentTimeStamp;

    private String receivedTimeStamp;

    public Message(){

    }

    public Message(String msg, String user, String timeStamp){
        this.id = 0;
        this.msg = msg;
        this.user = user;
        this.sentTimeStamp = timeStamp;
        //todo this doesn't seem to work
        //this.receivedTimeStamp = LocalDateTime.now().toString();
    }

    /**
     * GETTERS
     **/

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public String getUser() {
        return user;
    }

    public String getSentTimeStamp() {
        return sentTimeStamp;
    }

    public String getReceivedTimeStamp() {
        return receivedTimeStamp;
    }

    /**
     * SETTERS
     **/

    public void setId(int id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSentTimeStamp(String sentTimeStamp) {
        this.sentTimeStamp = sentTimeStamp;
    }

    public void setReceivedTimeStamp(String receivedTimeStamp) {
        this.receivedTimeStamp = receivedTimeStamp;
    }
}