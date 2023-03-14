package com.example.practisingeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupactivity extends AppCompatActivity {
EditText signupName,signupEmail,signupUsername,signupPassword;
Button signup_btn;
TextView loginredirectrext;
FirebaseDatabase database;
DatabaseReference reference;
HelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
        signupName=findViewById(R.id.signupname);
        signupEmail=findViewById(R.id.signup_email);
        signupUsername=findViewById(R.id.signup_username);
        signupPassword=findViewById(R.id.signup_password);
        signup_btn=findViewById(R.id.signup_btn);
        loginredirectrext=findViewById(R.id.loginRedirecttext);



        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");
                String name=signupName.getText().toString();
                String email=signupEmail.getText().toString();
                String username=signupUsername.getText().toString();
                String password=signupPassword.getText().toString();
             helperClass=new HelperClass(name,email,username,password);

              reference.child(name).setValue(helperClass);

                Toast.makeText(signupactivity.this, "you have signup successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(signupactivity.this,loginactivity.class));
              
            }
        });
        loginredirectrext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupactivity.this,loginactivity.class));
            }
        });



    }
}