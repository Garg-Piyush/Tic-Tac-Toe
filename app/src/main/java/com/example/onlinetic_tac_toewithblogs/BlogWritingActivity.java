package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BlogWritingActivity extends AppCompatActivity {

    EditText topicEditText , blogEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_writing);

        topicEditText = (EditText) findViewById(R.id.topicEditText);
        blogEditText = (EditText) findViewById(R.id.blogEditText);
        saveButton = (Button) findViewById(R.id.saveButton);

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

    }
}