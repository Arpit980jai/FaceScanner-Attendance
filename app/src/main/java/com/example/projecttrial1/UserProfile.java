package com.example.projecttrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullName,email,phoneNo,password;
    DatabaseReference reference;
    Button update;
    String user_username,user_name,user_email,user_phoneNo,user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        update=findViewById(R.id.update);
        reference= FirebaseDatabase.getInstance().getReference("users");


       showAllUserData();

    }
   private void showAllUserData() {
        Intent intent = getIntent();
         user_username=intent.getStringExtra("username");
         user_name = intent.getStringExtra("name");
         user_email = intent.getStringExtra("email");
         user_phoneNo = intent.getStringExtra("phoneNo");
         user_password = intent.getStringExtra("password");
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);



    }
    public void update(View view){
        if (isNameChanged() || isPasswordChanged()){
            Toast.makeText(this,"Data has been Changed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Data has not been Changed",Toast.LENGTH_SHORT).show();
        }

    }
    private boolean isPasswordChanged(){
        if(!user_password.equals(password.getEditText().getText().toString()))
        {
            reference.child(user_username).child("password").setValue(password.getEditText().getText().toString());
            user_password=password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isNameChanged(){
        if(!user_name.equals(fullName.getEditText().getText().toString())){
            reference.child(user_username).child("name").setValue(fullName.getEditText().getText().toString());
            user_name=fullName.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }

    }

}