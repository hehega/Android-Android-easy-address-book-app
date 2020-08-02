package com.example.myapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class GetNumber_2 {
  /*  private        ContactManage     cManage;
*//*    private DBhelp dBhelp;
    SQLiteDatabase db =dBhelp.getWritableDatabase();*//*


    public  static List<PhoneInfo_2> inputLists_2 =new ArrayList<PhoneInfo_2>();

    public  static  String gerNumber(Context context) {
        //                                                                        使用接口来获取本地电话薄
        Cursor cursor_get = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
       *//* Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);*//*
              //cr
        //创建对象来储存姓名和电话号码
        *//*String phoneNumber;
        String phoneName;*//*

        String phoneNumber_2;
        String phoneName_2;

        *//*if (cursor.getCount() > 0){*//*

            cursor_get.moveToNext();


            while (cursor_get.moveToNext()) {

                //获取本地电话薄的号码和联系人姓名
               *//* phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));*//*

                phoneName_2 = cursor_get.getString(cursor_get.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber_2 = cursor_get.getString(cursor_get.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

               *//* if (phoneName == phoneName_2 ){
                    ContentValues values=new ContentValues();
                    values.put("name",phoneName);
                    values.put("number",phoneNumber);
                    values.put("number_2",phoneNumber_2);
                    db.insert("contacts",null,values);
                }*//*
                //封装进phonInfo类中
              *//*  PhoneInfo phoneInfo = new PhoneInfo(phoneName, phoneNumber);*//*
                PhoneInfo_2 phoneInfo_2 = new PhoneInfo_2(phoneName_2, phoneNumber_2);

               *//* inputLists.add(phoneInfo);*//*
                inputLists_2.add(phoneInfo_2);

             *//*   cursor_get.moveToNext();
*//*
            }
        *//*}*//*
     *//*   if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                //获取本地电话薄的号码和联系人姓名
                phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));



                //封装进phonInfo类中
                PhoneInfo phoneInfo = new PhoneInfo(phoneName, phoneNumber);

                inputLists.add(phoneInfo);
            }
    }*//*

     return null;
    }*/
}
