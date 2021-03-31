package com.example.onlinetic_tac_toewithblogs;

public class Blog {

    private int id;
    private String blog;
    private String blogTopic;

    public Blog(int id,String blogTopic,String blog) {
        this.id = id;
        this.blogTopic = blogTopic;
        this.blog = blog;
    }

    public int getId() { return id; }
    public String getBlog() {
        return blog;
    }
    public String getBlogTopic(){return blogTopic;}
}
