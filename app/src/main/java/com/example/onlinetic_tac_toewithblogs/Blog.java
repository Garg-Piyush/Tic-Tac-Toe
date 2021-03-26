package com.example.onlinetic_tac_toewithblogs;

public class Blog {

    private int id;
    private String blog;

    public Blog(int id,String blog) {
        this.id = id;
        this.blog = blog;
    }

    public int getId() { return id; }
    public String getBlog() {
        return blog;
    }
}
