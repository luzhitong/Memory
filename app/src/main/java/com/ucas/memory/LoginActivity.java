package com.ucas.memory;


/**
 * Created by w on 2014/12/30.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class LoginActivity extends Activity {

    EditText userName;
    EditText password;
    Button btn_signin;
   TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userName=(EditText)findViewById(R.id.username_edit);
        password=(EditText)findViewById(R.id.password_edit);
        btn_signin= (Button) findViewById(R.id.signin_button);
        register=(TextView)findViewById(R.id.register);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userName.getText().toString();
                String pwd = password.getText().toString();
                try {
                    if(DataUtility.login(ConnServer.login(email,pwd))){
                        Intent i=new Intent();
                        i.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "用户名或密码错误",
                                Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
   /*     register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent();
                j.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(j);
            }
        });*/


    }
}
