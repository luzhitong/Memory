package com.ucas.memory;

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

/**
 * Created by w on 2015/1/7.
 */
public class RegisterActivity extends Activity{
    Button btn_register;
    TextView tv_login;
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btn_register=(Button)findViewById(R.id.signup_button11);
        tv_login=(TextView)findViewById(R.id.login);
        userName=(EditText)findViewById(R.id.username_edit);
        password=(EditText)findViewById(R.id.password_edit);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
/*        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userName.getText().toString();
                String pwd = password.getText().toString();
                try {
                    if(DataUtility.register(ConnServer.register(email, "", pwd))){
                        Intent i=new Intent();
                        i.setClass(RegisterActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"注册失败，稍后再试" ,
                                Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/

    }
}
