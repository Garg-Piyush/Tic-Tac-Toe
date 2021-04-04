package com.example.onlinetic_tac_toewithblogs;

public class BlogData {
    private String topic;
    private String blog;
    private Long i;

    public BlogData() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Long getID() { return i;}

    public void setID(Long i) { this.i = i;}
}
