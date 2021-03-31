package com.example.onlinetic_tac_toewithblogs;

public class BlogTopic {

    private int id;
    private String topic;

    public BlogTopic(int id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }
}
