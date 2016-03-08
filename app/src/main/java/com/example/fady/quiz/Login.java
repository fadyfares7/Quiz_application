package com.example.fady.quiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Login extends Activity {
    public static User_Data user;
    public static String Token;
    Error error_msg;
    public static int chktime=0;

    EditText email,pass;
    Button login;
    public static final String ENDPOINT ="http://192.168.1.5:3000";
    private Login_ReqData data;
    private String studentEmail="student@asu.edu.eg";
    private String studentPass="123456";
    String instEmail="teacher@asu.edu.eg";
    String instPass="654321";
    public static String Email;
    public static String Password;
    private FragmentManager supportFragmentManager;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button);
        error_msg=new Error();
        data=new Login_ReqData();




    }
    public void login(View view)
    {
        Email=email.getText().toString();
        Password=pass.getText().toString();
        if (email.getText().toString().equals("") || pass.getText().toString().equals(""))
        {
        Toast.makeText(Login.this, "pls enter your Username and password", Toast.LENGTH_SHORT).show();
    }
        else
        {

            data.setEmail(Email);
            data.setPassword(Password);
            RestAdapter adapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(ENDPOINT)
                    .build();
            RequestAPI api = adapter.create(RequestAPI.class);
            api.post(data, new Callback<Error>() {
                @Override
                public void success(Error error, Response response) {
                    if(error.getError().toString().equals("No"))
                    {
                        sign_in();
                    }
                    else {
                        Toast.makeText(Login.this, "Incorrect email or password ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(Login.this, "Couldn't connect to the server ", Toast.LENGTH_SHORT).show();
                }
            });



            if (email.getText().toString().equals(studentEmail) && pass.getText().toString().equals(studentPass)) {
                Intent in1 = new Intent(getApplicationContext(), student.class);
                startActivity(in1);

            } else if (email.getText().toString().equals(instEmail) && pass.getText().toString().equals(instPass)) {


                Intent in2 = new Intent(getApplicationContext(), instructor.class);
                startActivity(in2);
            }
        }


    }
    public void sign_in()
    {

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();
        RequestAPI api2 = adapter.create(RequestAPI.class);
        api2.sign_in(data, new Callback<User_Data>() {
            @Override
            public void success(User_Data user_data, Response response) {
                if (user_data.getRole().toString().equals("student")) {
                    user = user_data;
                    Token=user.getAuthentication_token();
                    Intent in1 = new Intent(getApplicationContext(), student.class);

                    startActivity(in1);
                } else if (user_data.getRole().toString().equals("instructor")) {
                    user = user_data;
                    instructor.instr=user_data;
                    Token=user.getAuthentication_token();
                    Intent in2 = new Intent(getApplicationContext(), instructor.class);
                    startActivity(in2);
                } else {
                    Toast.makeText(Login.this, "Incorrect email or password ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Login.this, "Couldn't connect to the server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();


        email.setText("");
        pass.setText("");

    }

    @Override
    public void onBackPressed() {

        out();
    }
    public void out() {
        DialogFragment newFragment = new Out();
        newFragment.show(getFragmentManager(), "Exit");
    }

}


