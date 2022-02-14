package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginLab extends AppCompatActivity {

    EditText uname,pswd;
    Button login,register;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_lab);

        uname= findViewById(R.id.et1);
        pswd=  findViewById(R.id.et2);
        login= findViewById(R.id.buttonLoginLab);
        register = findViewById(R.id.buttonRegister);

        db=new DbHandler(LoginLab.this);
        db.addUser(new User("Km", "12345"));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=uname.getText().toString();
                String p=pswd.getText().toString();
                int id= checkUser(new User(n,p));
                if(id==-1)
                {
                    Toast.makeText(LoginLab.this,"User Does Not Exist",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginLab.this,"Username "+n+ " logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginLab.this, GraphicActivity.class);
                    startActivity(i);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=uname.getText().toString();
                String password=pswd.getText().toString();
                int id= checkUser(new User(name,password));

                if(id ==-1)
                {
                    db.addUser(new User(name,password));
                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "User already exists..Please Login", Toast.LENGTH_SHORT).show();

                    /*OPTIONAL----->
                    register.setEnabled(false);

                    pswd.clearFocus();
                    uname.clearFocus();

                    uname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            register.setEnabled(true);
                        }
                    });
                    pswd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            register.setEnabled(true);
                        }
                    });

                    ------------ */
                }
            }
        });

    }
    public int checkUser(User user)
    {
        return db.checkUser(user);
    }
}