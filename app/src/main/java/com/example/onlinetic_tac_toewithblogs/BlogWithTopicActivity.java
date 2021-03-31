package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BlogWithTopicActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BlogAdapter adapter;
    List<Blog> blogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_with_topic);

        blogList = new ArrayList<Blog>();

        recyclerView = (RecyclerView) findViewById(R.id.blogRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addBlog(String topic,String blog){
        blogList.add(new Blog(1,topic,blog));
        adapter = new BlogAdapter(this,blogList);
        recyclerView.setAdapter(adapter);
    }
}