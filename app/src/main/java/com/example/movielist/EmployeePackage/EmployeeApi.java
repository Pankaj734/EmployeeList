package com.example.movielist.EmployeePackage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

    String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<Employee> getEmployees();
}
