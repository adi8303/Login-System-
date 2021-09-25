
package com.example.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class Register extends AppCompatActivity {
EditText registerFullName,registerEmail,registerPassword,registerconfPass;
Button registerUserbtn,gotoLogin;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName=findViewById(R.id.registerFullname);
        registerEmail=findViewById(R.id.registerEmail);
    registerPassword=findViewById(R.id.registerPassword);

    registerconfPass=findViewById(R.id.confPassword);
    registerUserbtn=findViewById(R.id.register_button);
        gotoLogin=findViewById(R.id.gotoLogin);

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        FirebaseApp.initializeApp(this);
        fAuth=FirebaseAuth.getInstance();


    registerUserbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String fullname=registerFullName.getText().toString();
            String email=registerEmail.getText().toString();
            String password=registerPassword.getText().toString();
            String confPass=registerconfPass.getText().toString();


            if(fullname.isEmpty()){
                registerFullName.setError("Full name Is Required");
                return;
            } if(email.isEmpty()){
                registerEmail.setError("Email Is Required");
                return;
            } if(password.isEmpty()){
                registerPassword.setError("Password Is Required");
                return;
            } if(confPass.isEmpty()){
                registerFullName.setError("Confirmation Is Required");
                return;
            }
            if(!password.equals(confPass)){
                registerconfPass.setError(("Password should be Same"));
            }

            // Check if email id is valid or not
//            important
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                registerEmail.setError("Enter Valid Email Address");
                registerEmail.requestFocus();
            }

//            Toast.makeText(Register.this, "Access Granted:You are Welcome :)", Toast.LENGTH_SHORT).show();
           fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
               @Override
               public void onSuccess(AuthResult authResult) {
startActivity(new Intent(getApplicationContext(),MainActivity.class));
finish();

               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(Register.this,e.getMessage(), Toast.LENGTH_SHORT).show();
               }
           });
            }

    });
   }
}