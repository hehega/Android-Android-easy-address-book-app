package com.example.myapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class GetNumber {
    private        ContactManage     cManage;
    public  static List<PhoneInfo>   inputLists   =new ArrayList<PhoneInfo>();
    public  static  String gerNumber(Context context) {
        //*使用接口来获取本地电话薄
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        //*创建对象来储存姓名和电话号码
        String phoneNumber;
        String phoneName;
            //*遍历获取联系人
            while (cursor.moveToNext()) {

                //*获取本地电话薄的号码和联系人姓名
                phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //*封装进phonInfo类中
                PhoneInfo phoneInfo = new PhoneInfo(phoneName, phoneNumber);
                //*从phoneInfo中把封装好的数据添加进数组
                inputLists.add(phoneInfo);

            }
     return null;
    }
}
