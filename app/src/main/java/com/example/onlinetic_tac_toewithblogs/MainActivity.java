package com.example.onlinetic_tac_toewithblogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.textViewSignUp).setOnClickListener(MainActivity.this);
        findViewById(R.id.buttonLogin).setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignUp:
                startActivity(new Intent(this,Sign_Up_Activity.class));
                break;
        }
    }
}