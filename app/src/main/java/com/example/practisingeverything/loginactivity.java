package com.example.practisingeverything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class loginactivity extends AppCompatActivity {
EditText loginusername,loginpassword;
Button login_btn;
TextView signupredirecttext;
FirebaseDatabase database;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        loginusername=findViewById(R.id.login_username);
        loginpassword=findViewById(R.id.login_password);
        login_btn=findViewById(R.id.login_btn);
        signupredirecttext=findViewById(R.id.signupRedirecttext);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateuser() | !validatepassword()){
                    Toast.makeText(loginactivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }else {
                    check();
                }
            }
        });

signupredirecttext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(loginactivity.this,signupactivity.class));
    }
});





    }
    public Boolean validateuser(){
        String val=loginusername.getText().toString();
       if(val.isEmpty()){
           loginusername.setError("Username cannot be Empty");
           return  false;
       }else{
           loginusername.setError(null);
           return true;
       }

      }
    public Boolean validatepassword(){
        String val=loginusername.getText().toString();
        if(val.isEmpty()){
            loginpassword.setError("password cannot be Empty");
            return  false;
        }else{
            loginpassword.setError(null);
            return true;
        }

    }

    public  void check(){
        String userusername=loginusername.getText().toString();
        String userpassword=loginpassword.getText().toString();

        reference=FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdatabase=reference.orderByChild("username").equalTo(userusername);
        checkuserdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginusername.setError(null);
                    String passwordfromDB=snapshot.child(userusername).child("password").getValue(String.class);
                      if(!Objects.equals(passwordfromDB,userpassword)){
                          loginusername.setError(null);
                          startActivity(new Intent(loginactivity.this,MainActivity.class));
                      }else{
                          loginpassword.setError("Invalid credentials");
                          loginpassword.requestFocus();
                      }
                }else {
                    loginusername.setError("Invalid credentials");
                    loginusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}