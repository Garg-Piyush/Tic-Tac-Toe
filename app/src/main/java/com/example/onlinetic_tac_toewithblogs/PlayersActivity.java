package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.PriorityGoalRow;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity implements View.OnClickListener, PlayerAdapter.OnPlayerListener {

    List<String> playerList;
    RecyclerView recyclerView;
    PlayerAdapter adapter;
    DatabaseReference myReff, myReff1, myReff2;
    ProgressBar progressBar;
    String currentUserEmail, currentUserUID, requestReceiverUID;
    ValueEventListener vel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        findViewById(R.id.request_textview).setOnClickListener(this);
        findViewById(R.id.players_textview).setOnClickListener(this);
        findViewById(R.id.blogs_textview).setOnClickListener(this);

        currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        playerList = new ArrayList<String>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlayerAdapter.OnPlayerListener onPlayerListener = this;

        progressBar = (ProgressBar) findViewById(R.id.playersProgressBar);

        myReff = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        myReff1 = FirebaseDatabase.getInstance().getReference().child("UID and mail").child(currentUserUID);
        myReff1.setValue(currentUserEmail);

        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if(!string.equals(currentUserEmail)){
                        playerList.add(string);
                    }
                }
                adapter = new PlayerAdapter(PlayersActivity.this,playerList,onPlayerListener);
                recyclerView.setAdapter(adapter);
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
                            Intent intent = new Intent(PlayersActivity.this,GameScreenActivity.class);
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
    public void OnPlayerClick(int position) {
        myReff2 = FirebaseDatabase.getInstance().getReference();
        String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String requestReceiverMail = playerList.get(position);
        DatabaseReference myReff3 = FirebaseDatabase.getInstance().getReference().child("UID and mail");
          vel  = myReff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    String string = (String) child.getValue();
                    if (requestReceiverMail.equals(string)){
                        requestReceiverUID = child.getKey();
                        myReff2 = myReff2.child("Requests").child(currentUserUID);
                        myReff2.setValue(requestReceiverUID);
                        myReff3.removeEventListener(vel);

                        DatabaseReference myReff4 = FirebaseDatabase.getInstance().getReference().child("Play Game");
                        myReff4 = myReff4.push();
                        DatabaseReference myReff5 = myReff4;
                        myReff4 = myReff4.child(currentUserUID);
                        myReff4.setValue(true);
                        myReff5 = myReff5.child(requestReceiverUID);
                        myReff5.setValue(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Toast.makeText(this,"Request sent to "+requestReceiverMail,Toast.LENGTH_SHORT).show();
    }
}