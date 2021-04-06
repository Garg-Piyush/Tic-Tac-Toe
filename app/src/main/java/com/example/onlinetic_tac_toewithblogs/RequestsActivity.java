package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity implements View.OnClickListener, RequestAdapter.OnRequestListener{

    String currentUserMail, requestByMail, requestToMail;
    DatabaseReference myReff, myReff1;
    List<String> requestList;
    String requestByUID, requestToUID;
    RequestAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ValueEventListener vel,vel1;
    RequestAdapter.OnRequestListener onRequestListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        findViewById(R.id.request_textview).setOnClickListener(this);
        findViewById(R.id.players_textview).setOnClickListener(this);
        findViewById(R.id.blogs_textview).setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.requestProgressBar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestList = new ArrayList<String>();

        onRequestListener = this;

        currentUserMail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        myReff1 = FirebaseDatabase.getInstance().getReference().child("UID and mail");
        myReff = FirebaseDatabase.getInstance().getReference().child("Requests");
        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if (string.equals(currentUserMail)){
                        requestByUID = child.getKey();
                        requestToMail = string;
                        vel = myReff1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot child1 : dataSnapshot.getChildren()) {
                                    String string1 = child1.getKey();
                                    if (requestByUID.equals(string1)) {
                                        requestByMail = (String) child1.getValue();
                                    }
                                }
                                requestList.add(requestByMail);
                                adapter = new RequestAdapter(RequestsActivity.this, requestList, onRequestListener);
                                recyclerView.setAdapter(adapter);
                                myReff1.removeEventListener(vel);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    @Override
    public void OnAcceptClick(int position) {
        myReff = myReff.child(requestByUID);
        myReff.removeValue();
        String currentUserMailID = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String currentUserUID2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference myReff4 = FirebaseDatabase.getInstance().getReference().child("UID and mail");
        vel1 = myReff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if(string.equals(requestToMail)){
                        requestToUID = child.getKey();
                        if (currentUserUID2.equals(requestToUID)){
                            DatabaseReference myReff3 = FirebaseDatabase.getInstance().getReference().child("Requests");
                            myReff3 = myReff3.child(currentUserUID2);
                            myReff3.setValue(requestByMail);
                        }
                        myReff4.removeEventListener(vel1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(currentUserMailID.equals(requestToMail) || currentUserMailID.equals(requestByMail)){
                Intent intent = new Intent(RequestsActivity.this, GameScreenActivity.class);
                intent.putExtra("Request from", requestByMail);
                intent.putExtra("Request to", requestToMail);
                startActivity(intent);
        }
    }

    @Override
    public void OnDeclineClick(int position) {
        myReff = myReff.child(requestByUID);
        myReff.removeValue();
        startActivity(new Intent(RequestsActivity.this,RequestsActivity.class));
    }
}