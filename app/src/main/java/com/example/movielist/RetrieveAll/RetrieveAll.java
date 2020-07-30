package com.example.movielist.RetrieveAll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movielist.DBHelper.EmployeeDB;
import com.example.movielist.Login.Login;
import com.example.movielist.MainActivity;
import com.example.movielist.R;
import com.example.movielist.Session.Session;

import java.util.ArrayList;

public class RetrieveAll extends AppCompatActivity {

    private Button btn, main, Logout;
    private TextView  tv;
    private EmployeeDB edb;
    private int length;
    private String j;
    private Session session;
    private ProgressBar spinner;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> salary = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_all);

        session = new Session(this);
        edb = new EmployeeDB(this);

        btn = (Button)findViewById(R.id.show);
        main = (Button)findViewById(R.id.home);
        Logout = (Button)findViewById(R.id.logout);
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
                length = edb.getLength();
                tv.setText("");
                for (int i = 0; i < length; i++) {
                    j = Integer.toString(i + 1);
                    tv.append("id : " + j + "\n"
                            + "Name : " + name.get(i) + "\n"
                            + "Age : " + age.get(i) + "\n"
                            + "Salary : " + salary.get(i) + "\n"
                            + "-------------------------------" + "\n");
                }
                spinner.setVisibility(View.GONE);
            }

        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RetrieveAll.this, MainActivity.class));
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
        startActivity(new Intent(RetrieveAll.this, Login.class));
        finish();
    }
}