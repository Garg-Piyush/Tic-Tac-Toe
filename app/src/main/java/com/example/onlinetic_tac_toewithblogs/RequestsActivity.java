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

    String currentUserUID, requestByMail, requestToMail;
    DatabaseReference myReff, myReff1;
    List<String> requestList;
    String requestByUID, requestToUID;
    RequestAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ValueEventListener vel,vel1, vel2;
    RequestAdapter.OnRequestListener onRequestListener;
    String requesterOfAcceptedRequestUId;

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

        currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        myReff1 = FirebaseDatabase.getInstance().getReference().child("UID and mail");
        myReff = FirebaseDatabase.getInstance().getReference().child("Requests");
        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if (string.equals(currentUserUID)){
                        requestByUID = child.getKey();
                        requestToUID = string;
                        requestToMail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
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
                    }
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                            Intent intent = new Intent(RequestsActivity.this,GameScreenActivity.class);
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
        String currentUserMailID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String currentUserUID2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String requesterOfAcceptedRequestMail = requestList.get(position);

        /* if(currentUserMailID.equals(requestToUID)){
                Intent intent = new Intent(RequestsActivity.this, GameScreenActivity.class);
                intent.putExtra("Request from", requestByMail);
                intent.putExtra("Request to", requestToMail);
                startActivity(intent);
        }else{
            String currentUserID3 = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        if (currentUserMailID.equals(requestByUID)){
            Intent intent = new Intent(RequestsActivity.this, GameScreenActivity.class);
            intent.putExtra("Request from", requestByMail);
            intent.putExtra("Request to", requestToMail);
            startActivity(intent);
        }*/

        DatabaseReference myReff3 = FirebaseDatabase.getInstance().getReference().child("UID and mail");
        vel2 = myReff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if(string.equals(requesterOfAcceptedRequestMail)){
                        requesterOfAcceptedRequestUId = child.getKey();
                        myReff3.removeEventListener(vel2);

                        DatabaseReference myReff2 = FirebaseDatabase.getInstance().getReference().child("Play Game");
                        vel1 = myReff2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot child : snapshot.getChildren()){
                                    if(child.hasChild(requesterOfAcceptedRequestUId) && child.hasChild(currentUserUID2)){
                                        DatabaseReference myReff4 = child.getRef();
                                        myReff4.child(currentUserUID2).setValue(true);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void OnDeclineClick(int position) {
        myReff = myReff.child(requestByUID);
        myReff.removeValue();
        startActivity(new Intent(RequestsActivity.this,RequestsActivity.class));
    }
}