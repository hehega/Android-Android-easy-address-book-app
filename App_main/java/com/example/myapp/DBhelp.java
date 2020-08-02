package com.example.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// *创建SQLite数据库
public class DBhelp extends SQLiteOpenHelper {
    public DBhelp(Context context){
        // *创建数据库，此处数据库为“contact.db”
        super(context,"contact.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //*定义sql语句来创建表。
        String sql="create table contacts(id integer primary key autoincrement,name vachar,number vachar)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){

    }
}
