package com.example.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
TextView verifyMsg;
Button verifyEmailBtn;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();


Button signout=findViewById(R.id.signoutbtn);
verifyMsg = findViewById(R.id.verifyemailMsg);
verifyEmailBtn = findViewById(R.id.verifyemailBtn);

if(!auth.getCurrentUser().isEmailVerified()){
    verifyEmailBtn.setVisibility(View.VISIBLE);
    verifyMsg.setVisibility(View.VISIBLE);
}

verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
    @Override
//    sending verification email
    public void onClick(View v) {
        auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this,"Verification Email Sent",Toast.LENGTH_SHORT).show();
                verifyEmailBtn.setVisibility(View.GONE);
            verifyMsg.setVisibility(View.GONE);
            }
        });
    }
});
signout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    finish();
    }
});
    }

}
