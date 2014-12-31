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

public class LoginActivity extends Activity {

    EditText userName;
    EditText password;
    Button btn_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userName=(EditText)findViewById(R.id.username_edit);
        password=(EditText)findViewById(R.id.password_edit);
        btn_signin= (Button) findViewById(R.id.signin_button);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
