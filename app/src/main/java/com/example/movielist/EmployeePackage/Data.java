package com.example.movielist.EmployeePackage;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    private String id;
    @SerializedName("employee_name")
    private String employee_name;
    @SerializedName("employee_age")
    private String employee_age;
    @SerializedName("employee_salary")
    private String employee_salary;

    public Data(String id, String employee_name, String employee_age, String employee_salary){
        this.id = id;
        this.employee_name = employee_name;
        this.employee_age = employee_age;
        this.employee_salary = employee_salary;
    }

    public String getId() {
        return id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }
}
