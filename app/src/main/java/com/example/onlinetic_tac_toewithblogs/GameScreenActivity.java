package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;

public class GameScreenActivity extends AppCompatActivity implements View.OnClickListener {

    TextView player1Txt,player2Txt,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,plr1ScrTxt,plr2ScrTxt;
    int[][] a = new int[3][3];
    int plr1scr = 0;
    int plr2scr = 0;
    String player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();
        player1 = intent.getStringExtra("Request from");
        player2 = intent.getStringExtra("Request to");

        player1Txt = (TextView) findViewById(R.id.player1);
        player2Txt = (TextView) findViewById(R.id.player2);
        plr1ScrTxt = (TextView) findViewById(R.id.player1Score);
        plr2ScrTxt = (TextView) findViewById(R.id.player2Score);
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
        plr1ScrTxt.setText(plr1scr);
        plr2ScrTxt.setText(plr2scr);

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

    }

    public int checkResult(){
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
        if (a[1][1]==1 && a[2][2]==1 && a[3][3]==1) return 1;
        if (a[1][1]==2 && a[2][2]==2 && a[3][3]==2) return 2;
        if (a[0][2]==1 && a[1][1]==1 && a[2][2]==1) return 1;
        if (a[0][2]==2 && a[1][1]==2 && a[2][2]==2) return 2;
        return 0;
    }

    public void restart(){
        txt1.setBackgroundResource(R.color.brown);
        txt2.setBackgroundResource(R.color.brown);
        txt3.setBackgroundResource(R.color.brown);
        txt4.setBackgroundResource(R.color.brown);
        txt5.setBackgroundResource(R.color.brown);
        txt6.setBackgroundResource(R.color.brown);
        txt7.setBackgroundResource(R.color.brown);
        txt8.setBackgroundResource(R.color.brown);
        txt9.setBackgroundResource(R.color.brown);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt1:
                String crntPlr = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr.equals(player1)){
                    txt1.setBackgroundResource(R.color.white);
                    a[0][0]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt1.setBackgroundResource(R.color.black);
                    a[0][0]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt2:
                String crntPlr1 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr1.equals(player1)){
                    txt2.setBackgroundResource(R.color.white);
                    a[0][1]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt2.setBackgroundResource(R.color.black);
                    a[0][1]=2;
                    int i = checkResult();
                    if (i == 2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt3:
                String crntPlr2 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr2.equals(player1)){
                    txt3.setBackgroundResource(R.color.white);
                    a[0][2]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt3.setBackgroundResource(R.color.black);
                    a[0][2]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt4:
                String crntPlr3 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr3.equals(player1)){
                    txt4.setBackgroundResource(R.color.white);
                    a[1][0]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt4.setBackgroundResource(R.color.black);
                    a[1][0]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt5:
                String crntPlr4 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr4.equals(player1)){
                    txt5.setBackgroundResource(R.color.white);
                    a[1][1]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt5.setBackgroundResource(R.color.black);
                    a[1][1]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt6:
                String crntPlr5 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr5.equals(player1)){
                    txt6.setBackgroundResource(R.color.white);
                    a[1][2]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt6.setBackgroundResource(R.color.black);
                    a[1][2]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt7:
                String crntPlr6 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr6.equals(player1)){
                    txt7.setBackgroundResource(R.color.white);
                    a[2][0]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt7.setBackgroundResource(R.color.black);
                    a[2][0]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt8:
                String crntPlr7 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr7.equals(player1)){
                    txt8.setBackgroundResource(R.color.white);
                    a[2][1]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt8.setBackgroundResource(R.color.black);
                    a[2][1]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
            case R.id.txt9:
                String crntPlr8 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if (crntPlr8.equals(player1)){
                    txt9.setBackgroundResource(R.color.white);
                    a[2][2]=1;
                    int i = checkResult();
                    if (i==1){
                        Toast.makeText(this,player1+" Won",Toast.LENGTH_SHORT).show();
                        plr1scr++;
                        plr1ScrTxt.setText(plr1scr);
                        restart();
                    }
                }else{
                    txt9.setBackgroundResource(R.color.black);
                    a[2][2]=2;
                    int i = checkResult();
                    if (i==2){
                        Toast.makeText(this,player2+" Won",Toast.LENGTH_SHORT).show();
                        plr2scr++;
                        plr2ScrTxt.setText(plr2scr);
                        restart();
                    }
                }
                break;
        }
    }
}