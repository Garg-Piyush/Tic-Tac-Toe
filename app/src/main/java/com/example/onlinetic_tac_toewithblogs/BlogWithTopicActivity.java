package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class BlogWithTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_with_topic);

        TextView blogTopicTextView = (TextView) findViewById(R.id.blogTopicTextView);
        TextView blogTextView = (TextView) findViewById(R.id.blogTextView);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        String blog = intent.getStringExtra("blog");

        blogTopicTextView.setText(topic);
        blogTextView.setText(blog);
    }
}