package com.example.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.loginsystem.R.id.create_account_button;
import static com.example.loginsystem.R.id.fade;

public class Login extends AppCompatActivity implements Login1 {
    Button createaccountBtn;
    EditText username,password;
    Button Loginbtn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        initialising firebaseauth
        firebaseAuth =FirebaseAuth.getInstance();

        createaccountBtn = findViewById(R.id.create_account_button);
        createaccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        username=findViewById(R.id.Username);
        password=findViewById(R.id.password);
        Loginbtn=findViewById(R.id.Login_button);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()){
                    username.setError("Email is Missing");
//                    Toast.makeText(Login.this,"Email not Filled",Toast.LENGTH_SHORT).show();
                }
                if(password.getText().toString().isEmpty()){
                    username.setError("Password is Missing");
//                    Toast.makeText(Login.this,"Email not Filled",Toast.LENGTH_SHORT).show();
                }
//login user
// using firebaseauth instance

                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
startActivity(new Intent (getApplicationContext(),MainActivity.class));
finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
   finish();
        }

    }

}