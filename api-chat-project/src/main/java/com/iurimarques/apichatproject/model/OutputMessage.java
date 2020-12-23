package com.iurimarques.apichatproject.model;

public class OutputMessage {
    
    private String from;
    private String message;
    private String time;

    public OutputMessage(String from, String message, String time) {
        this.from = from;
        this.message = message;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
