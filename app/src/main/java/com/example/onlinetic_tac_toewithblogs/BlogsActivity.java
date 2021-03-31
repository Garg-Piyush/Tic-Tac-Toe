package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class BlogsActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    BlogTopicAdapter adapter;
    List<BlogTopic> blogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        findViewById(R.id.request_textview).setOnClickListener(this);
        findViewById(R.id.players_textview).setOnClickListener(this);
        findViewById(R.id.blogs_textview).setOnClickListener(this);

        blogList = new ArrayList<BlogTopic>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button blogButton = (Button) findViewById(R.id.blogButton);
        blogButton.setOnClickListener(v -> {
            startActivity(new Intent(this,BlogWritingActivity.class));
        });

        Intent intent = getIntent();
        if(intent.hasExtra("from")) {
            String topic = intent.getStringExtra("topic");
            addTopic(topic);
            String blog = intent.getStringExtra("blog");
        }
    }

    private void addTopic(String topic){
        blogList.add(new BlogTopic(1,topic));
        adapter = new BlogTopicAdapter(this, blogList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.request_textview :
                startActivity(new Intent(this,RequestsActivity.class));
                break;

            case R.id.players_textview :
                startActivity(new Intent(this,PlayersActivity.class));
                break;

            case R.id.blogs_textview :
                startActivity(new Intent(this, BlogsActivity.class));
        }
    }
}