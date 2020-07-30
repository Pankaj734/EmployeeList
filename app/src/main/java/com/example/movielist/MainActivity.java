package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movielist.DBHelper.EmployeeDB;
//import com.example.movielist.DataUpload.Upload;
import com.example.movielist.EmployeePackage.Data;
import com.example.movielist.EmployeePackage.Employee;
import com.example.movielist.EmployeePackage.EmployeeApi;
import com.example.movielist.Login.Login;
import com.example.movielist.RetrieveAll.RetrieveAll;
import com.example.movielist.Session.Session;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnLogout, single, all, savedb, delete;
    private Session session;
    private ProgressBar spinner;

    private static final String TAG = "Upload";
    private EmployeeDB edb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edb = new EmployeeDB(this);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }

        btnLogout = (Button)findViewById(R.id.btnLogout);
        single = (Button)findViewById(R.id.ShowSingle);
        all = (Button)findViewById(R.id.showAll);
        savedb = (Button)findViewById(R.id.upload);
        delete = (Button)findViewById(R.id.delete);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        savedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://dummy.restapiexample.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);

                Call<Employee> call = employeeApi.getEmployees();
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Log.d(TAG, "onResponse : Server Response: " + response.toString());
                        Log.d(TAG, "onResponse : Recieved Information: " + response.body().toString());

                        ArrayList<Data> data = response.body().getData();
                        for (int i = 0; i < data.size(); i++) {

                            edb.AddEmployee(data.get(i).getId(), data.get(i).getEmployee_name(), data.get(i).getEmployee_salary(), data.get(i).getEmployee_age());

                        }

                        spinner.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Records Inserted", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Log.e(TAG, "on failure: something went wrong : " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                edb.deleteRecords();
                spinner.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "All Records Deleted", Toast.LENGTH_SHORT).show();
            }
        });



        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrieveSingle.class));
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrieveAll.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout(){
        session.setLoggedin(false);
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }
}