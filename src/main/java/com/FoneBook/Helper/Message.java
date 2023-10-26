package com.FoneBook.Helper;

public class Message {
    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String content;
    private String type;

    public String getContent() {
        return content;
    }
}
