package com.example.onlinetic_tac_toewithblogs;

public class BlogTopic {

    private int id;
    private String topic,blog;

    public BlogTopic(int id, String topic,String blog) {
        this.id = id;
        this.topic = topic;
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public  String getBlog() { return blog;}
}
