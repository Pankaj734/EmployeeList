package com.example.movielist.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movielist.DBHelper.userDB;
import com.example.movielist.MainActivity;
import com.example.movielist.R;
import com.example.movielist.Register.Register;
import com.example.movielist.Session.Session;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button login, register;
    private EditText etEmail, etPass;
    private TextView signup;
    private userDB db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new userDB(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        signup = (TextView)findViewById(R.id.SignUp);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.SignUp:
                startActivity(new Intent(Login.this, Register.class));
                break;
            default:

        }
    }

    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(email,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }
}