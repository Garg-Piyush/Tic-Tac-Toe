package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BlogWritingActivity extends AppCompatActivity {

    EditText topicEditText , blogEditText;
    Button saveButton;
    Long i = Long.valueOf(0);
    DatabaseReference reff;
    BlogData blogData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_writing);

        topicEditText = (EditText) findViewById(R.id.topicEditText);
        blogEditText = (EditText) findViewById(R.id.blogEditText);
        saveButton = (Button) findViewById(R.id.saveButton);

        reff = FirebaseDatabase.getInstance().getReference().child("Blog");
        blogData = new BlogData();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBlog();
            }
        });
    }

    private void saveBlog(){
        String topic = topicEditText.getText().toString();
        String blog = blogEditText.getText().toString();

        Intent intent = new Intent(this,BlogsActivity.class);
        intent.putExtra("topic",topic);
        intent.putExtra("blog",blog);
        intent.putExtra("from","BlogWritingActivity");
        startActivity(intent);

        blogData.setTopic(topic);
        blogData.setBlog(blog);
        blogData.setID(i);

        reff.push().setValue(blogData);
    }
}