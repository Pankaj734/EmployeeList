package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movielist.DBHelper.EmployeeDB;
import com.example.movielist.Login.Login;
import com.example.movielist.Session.Session;

import java.util.ArrayList;

public class RetrieveSingle extends AppCompatActivity {

    private static final String TAG = "RetrieveSingle";
    private Button btn, main, Logout;
    private EditText et;
    private TextView tv;
    private EmployeeDB edb;
    private Session session;
    private ProgressBar spinner;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> salary = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_single);

        session = new Session(this);

        edb = new EmployeeDB(this);

        btn = (Button)findViewById(R.id.show);
        main = (Button)findViewById(R.id.home);
        Logout = (Button)findViewById(R.id.logout);
        et = (EditText)findViewById(R.id.Id);
        tv = (TextView)findViewById(R.id.result);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        name = edb.getName();
        age = edb.getAge();
        salary = edb.getSalary();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);

                try {
                    String str = et.getText().toString();
                    if(str.matches("")){
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(RetrieveSingle.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int id = Integer.parseInt(str);

                    tv.append("id : "+ id + "\n"
                            +"Name : "+ name.get(id-1) + "\n"
                            +"Age : "+ age.get(id-1)+ "\n"
                            +"Salary : "+ salary.get(id-1)+ "\n");
                }catch (Exception e){
                    Log.e(TAG, "Error : "+ e.getMessage());
                }

                spinner.setVisibility(View.GONE);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RetrieveSingle.this, MainActivity.class));
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout(){
        session.setLoggedin(false);
        startActivity(new Intent(RetrieveSingle.this, Login.class));
        finish();
    }
}