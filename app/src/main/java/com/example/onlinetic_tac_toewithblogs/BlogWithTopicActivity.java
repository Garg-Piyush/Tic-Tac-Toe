package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        DatabaseReference myReff = FirebaseDatabase.getInstance().getReference().child("Play Game");
        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    int k=0;
                    for (DataSnapshot child1 : child.getChildren()){
                        if(child1.getValue().equals(true)) k++;
                        }
                    if (k==2){
                        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String[] array = new String[2];
                        int i=0;
                        for (DataSnapshot child2 : child.getChildren()){
                            array[i]=child2.getKey();
                            i++;
                        }
                        if (currentUserUid.equals(array[0]) || currentUserUid.equals(array[1])){
                            child.getRef().removeValue();
                            Intent intent = new Intent(BlogWithTopicActivity.this,GameScreenActivity.class);
                            intent.putExtra("1",array[0]);
                            intent.putExtra("2",array[1]);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}