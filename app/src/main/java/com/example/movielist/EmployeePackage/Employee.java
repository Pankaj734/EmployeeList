package com.example.movielist.EmployeePackage;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Employee {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private ArrayList<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
