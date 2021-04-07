package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

public class GameScreenActivity extends AppCompatActivity implements View.OnClickListener {

    TextView player1Txt,player2Txt,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;
    int[][] a = new int[3][3];
    String player1, player2, player1Uid, player2Uid;
    ValueEventListener vel;
    long touch = 0;
    LinearLayout wait, game;
    DatabaseReference myReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        myReff = FirebaseDatabase.getInstance().getReference().child("Touch");
        myReff.setValue(0);

        Intent intent = getIntent();
        player1Uid = intent.getStringExtra("1");
        player2Uid = intent.getStringExtra("2");

        String currentUserUid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

        wait = (LinearLayout) findViewById(R.id.waitPlayer);
        game = (LinearLayout) findViewById(R.id.gameUi);
        /*if (currentUserUid2.equals(player1Uid)){
            wait.setVisibility(View.VISIBLE);
            game.setVisibility(View.GONE);
        }else{
            Toast.makeText(this,"First move is yours",Toast.LENGTH_LONG).show();
        }*/

        player1Txt = (TextView) findViewById(R.id.player1);
        player2Txt = (TextView) findViewById(R.id.player2);

        DatabaseReference myReff = FirebaseDatabase.getInstance().getReference().child("UID and mail");
        vel = myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    String string = child.getKey();
                    if (string.equals(player1Uid)){
                        player1 = (String) child.getValue();
                        player1Txt.setText(player1);

                    }else if (string.equals(player2Uid)){
                        player2 = (String) child.getValue();
                        player2Txt.setText(player2);
                    }
                }
                myReff.removeEventListener(vel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);
        txt7 = (TextView) findViewById(R.id.txt7);
        txt8 = (TextView) findViewById(R.id.txt8);
        txt9 = (TextView) findViewById(R.id.txt9);

        player1Txt.setText(player1);
        player2Txt.setText(player2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                a[i][j] = 0;
            }
        }

        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        txt4.setOnClickListener(this);
        txt5.setOnClickListener(this);
        txt6.setOnClickListener(this);
        txt7.setOnClickListener(this);
        txt8.setOnClickListener(this);
        txt9.setOnClickListener(this);

        DatabaseReference myReff1 = FirebaseDatabase.getInstance().getReference().child(player1Uid+" and "+player2Uid);
        for (int i=1;i<10;i++){
            DatabaseReference myReff2 = myReff1;
            myReff2 = myReff2.child("txt"+String.valueOf(i));
            myReff2.setValue(0);
        }

        myReff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    long i = (long) child.getValue();
                    if (i==1){
                        String str = child.getKey();
                        got1(str);
                    }else if (i==2){
                        String str = child.getKey();
                        got2(str);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void got1(String str){
        int i;
        switch(str){
            case "txt1" :
                a[0][0]=1;
                txt1.setBackgroundResource(R.color.white);
                txt1.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt2" :
                a[0][1]=1;
                txt2.setBackgroundResource(R.color.white);
                txt2.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt3" :
                a[0][2]=1;
                txt3.setBackgroundResource(R.color.white);
                txt3.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt4" :
                a[1][0]=1;
                txt4.setBackgroundResource(R.color.white);
                txt4.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt5" :
                a[1][1]=1;
                txt5.setBackgroundResource(R.color.white);
                txt5.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt6" :
                a[1][2]=1;
                txt6.setBackgroundResource(R.color.white);
                txt6.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt7" :
                a[2][0]=1;
                txt7.setBackgroundResource(R.color.white);
                txt7.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt8" :
                a[2][1]=1;
                txt8.setBackgroundResource(R.color.white);
                txt8.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt9" :
                a[2][2]=1;
                txt9.setBackgroundResource(R.color.white);
                txt9.setOnClickListener(null);
                i = checkResult();
                if (i==1){
                    Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void got2(String str) {
        int i;
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        switch (str) {
            case "txt1":
                a[0][0] = 2;
                txt1.setBackgroundResource(R.color.black);
                txt1.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt2":
                a[0][1] = 2;
                txt2.setBackgroundResource(R.color.black);
                txt2.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt3":
                a[0][2] = 2;
                txt3.setBackgroundResource(R.color.black);
                txt3.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt4":
                a[1][0] = 2;
                txt4.setBackgroundResource(R.color.black);
                txt4.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt5":
                a[1][1] = 2;
                txt5.setBackgroundResource(R.color.black);
                txt5.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt6":
                a[1][2] = 2;
                txt6.setBackgroundResource(R.color.black);
                txt6.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt7":
                a[2][0] = 2;
                txt7.setBackgroundResource(R.color.black);
                txt7.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt8":
                a[2][1] = 2;
                txt8.setBackgroundResource(R.color.black);
                txt8.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
            case "txt9":
                a[2][2] = 2;
                txt9.setBackgroundResource(R.color.black);
                txt9.setOnClickListener(null);
                i = checkResult();
                if (i == 2) {
                    Toast.makeText(this, player2 + " Won", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                }else if (i==3){
                    startActivity(new Intent(GameScreenActivity.this, PlayersActivity.class));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private int checkResult(){
        for (int i=0;i<3;i++){
            int k=0;
            for (int j=0;j<3;j++){
                if(a[i][j]==1)
                    k++;
            }
            if(k==3) return 1;
        }
        for (int i=0;i<3;i++){
            int k=0;
            for (int j=0;j<3;j++){
                if(a[i][j]==2)
                    k++;
            }
            if(k==3) return 2;
        }
        for (int i=0;i<3;i++){
            int k=0;
            for (int j=0;j<3;j++){
                if(a[j][i]==1)
                    k++;
            }
            if(k==3) return 1;
        }
        for (int i=0;i<3;i++){
            int k=0;
            for (int j=0;j<3;j++){
                if(a[j][i]==2)
                    k++;
            }
            if(k==3) return 2;
        }
        if (a[0][0]==1 && a[1][1]==1 && a[2][2]==1) return 1;
        if (a[0][0]==2 && a[2][2]==2 && a[1][1]==2) return 2;
        if (a[0][2]==1 && a[1][1]==1 && a[2][0]==1) return 1;
        if (a[0][2]==2 && a[1][1]==2 && a[2][0]==2) return 2;

        int k=0;
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if(a[i][j]!=0) k++;
            }
        }
        if(k==9) return 3;
        return 0;
    }


    @Override
    public void onClick(View v) {
        myReff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long num = (long) snapshot.getValue();
                setTouch(num);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        touch++;
        myReff.setValue(touch);
        /*String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();*/
        DatabaseReference myReff = FirebaseDatabase.getInstance().getReference().child(player1Uid+" and "+player2Uid);
        DatabaseReference myReff2;
        switch (v.getId()){
            case R.id.txt1:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt1");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                    }*/
                    myReff2.setValue(2);

                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt2:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt2");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt3:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt3");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt4:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt4");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt5:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt5");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt6:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt6");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else {
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt7:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt7");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt8:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt8");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;
            case R.id.txt9:
                myReff2 = myReff;
                myReff2 = myReff2.child("txt9");
                if(touch%2!=0){
                    /*if(currentUserUid.equals(player2Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(2);
                }else{
                    /*if(currentUserUid.equals(player1Uid)){
                        wait.setVisibility(View.VISIBLE);
                        game.setVisibility(View.GONE);
                    }else{
                        wait.setVisibility(View.GONE);
                        game.setVisibility(View.VISIBLE);
                        Toast.makeText(this,"It's your turn",Toast.LENGTH_SHORT).show();
                    }*/
                    myReff2.setValue(1);
                }
                break;

        }
    }
    private void setTouch(long num){
        touch = num;
    }
}