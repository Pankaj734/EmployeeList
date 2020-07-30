//package com.example.movielist.DataUpload;
//
//import android.util.Log;
//
//import com.example.movielist.DBHelper.EmployeeDB;
//import com.example.movielist.EmployeePackage.Data;
//import com.example.movielist.EmployeePackage.Employee;
//import com.example.movielist.EmployeePackage.EmployeeApi;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//public class Upload  {
//    private static final String TAG = "Upload";
//    private static final String id = "";
//    private static final String name = "";
//    private static final String salary = "";
//    private static final String age = "";
//
////    private EmployeeDB edb = new EmployeeDB();
//
//    public void SavaToDB() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://dummy.restapiexample.com/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
//
//        Call<Employee> call = employeeApi.getEmployees();
//        call.enqueue(new Callback<Employee>() {
//            @Override
//            public void onResponse(Call<Employee> call, Response<Employee> response) {
//                Log.d(TAG, "onResponse : Server Response: " + response.toString());
//                Log.d(TAG, "onResponse : Recieved Information: " + response.body().toString());
//
//                ArrayList<Data> data = response.body().getData();
//                for (int i = 0; i < data.size(); i++) {
//
//                    edb.AddEmployee(data.get(i).getId(), data.get(i).getEmployee_name(), data.get(i).getEmployee_salary(), data.get(i).getEmployee_age());
//
////                    Result.append("id : " + data.get(i).getId() + "\n" +
////                            "Name : " + data.get(i).getEmployee_name() + "\n" +
////                            "Age : " + data.get(i).getEmployee_age() + "\n" +
////                            "Salary : " + data.get(i).getEmployee_salary() + "\n"
////                    );
//
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<Employee> call, Throwable t) {
//                Log.e(TAG, "on failure: something went wrong : " + t.getMessage());
////                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//}
