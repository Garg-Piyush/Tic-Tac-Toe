package com.example.onlinetic_tac_toewithblogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.w3c.dom.Text;

public class Sign_Up_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail , editTextPassword ;
    ProgressBar progressBar;
    TextView instructions;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        findViewById(R.id.textViewLogin).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.signUpEmail);
        editTextPassword = (EditText) findViewById(R.id.signUpPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        instructions = (TextView) findViewById(R.id.instruction);

        mAuth = FirebaseAuth.getInstance();
    }



    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Please enter your Email ID");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid Email ID");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Please set a password");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            instructions.setVisibility(View.VISIBLE);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Remember your Password:"+password,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Sign_Up_Activity.this,PlayersActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"Email ID already registered",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewLogin:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.buttonSignUp:
                registerUser();
                break;
        }
    }
}