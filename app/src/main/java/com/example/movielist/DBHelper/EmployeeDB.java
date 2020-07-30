package com.example.movielist.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class EmployeeDB extends SQLiteOpenHelper {

    public static final String TAG = EmployeeDB.class.getSimpleName();
    public static final String DB_NAME = "Employee.db";
    public static final String TABLE = "Employees";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SALARY = "salary";
    public static final String AGE = "age";

    public static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE " + TABLE + "("
            + ID + " TEXT UNIQUE,"
            + NAME + " TEXT,"
            + SALARY + " TEXT,"
            + AGE + " TEXT);";

    public EmployeeDB(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(sqLiteDatabase);
    }

    public void AddEmployee(String Id, String Name, String Salary, String Age) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, Id);
        values.put(NAME, Name);
        values.put(SALARY, Salary);
        values.put(AGE, Age);

        long id = db.insert(TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }


    public int getLength(){
        String selectQuery = "select * from  " + TABLE  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.getCount();
    }

    public ArrayList<String> getName(){

        ArrayList<String> array_list = new ArrayList<String>();

        String selectQuery = "select * from  " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(NAME)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAge(){

        ArrayList<String> array_list = new ArrayList<String>();

        String selectQuery = "select * from  " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(AGE)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getSalary(){

        ArrayList<String> array_list = new ArrayList<String>();

        String selectQuery = "select * from  " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(SALARY)));
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return array_list;
    }

    public void deleteRecords(){
        String query = "delete from "+TABLE;
        SQLiteDatabase db =this.getReadableDatabase();
        db.execSQL(query);
    }

}
