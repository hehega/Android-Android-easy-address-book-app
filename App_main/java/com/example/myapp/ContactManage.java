package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//*此类为数据库操作的封装
public class ContactManage {
    private DBhelp dBhelp;
    public final  static String Table_name="contacts";
    public final  static String Name="name";
    public final static String Number="number";



    public ContactManage(Context context){
        dBhelp=new DBhelp(context);
    }


    //*插入
    public Boolean insert(PhoneInfo phoneInfo){
        SQLiteDatabase db=dBhelp.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Name,phoneInfo.getName());
        values.put(Number,phoneInfo.getNumber());
        /*values.put(Number_2,phoneInfo.getNumber_2());*/
        long a=db.insert(Table_name,null,values);
        db.close();

        if (a!=0){
            return true;
        }
        else  return  false;
    }
    //*删除
    public void delete(int id){
        SQLiteDatabase db=dBhelp.getWritableDatabase();


        db.delete(Table_name,"id=?", new String[]{id+""});
        db.close();

    }

    //*更新
    public void update(String id,PhoneInfo phoneInfo){
        SQLiteDatabase db=dBhelp.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(Name,phoneInfo.getName());
        values.put(Number,phoneInfo.getNumber());

        db.update(Table_name,values,"id=?",new String[]{id+"" });
        db.close();
    }
//*查询
   public List<PhoneInfo> getAll(){
        //2020.07.07  准备加入实时搜索    //*已完成添加*
       List<PhoneInfo> phoneInfos = new ArrayList<PhoneInfo>();
       SQLiteDatabase db=dBhelp.getWritableDatabase();


       Cursor cursor = db.query(Table_name, new String[]{}, null, null, null,null,"id");

       while (cursor.moveToNext()){
           String nameString=cursor.getString(cursor.getColumnIndex(Name));
           String numberString=cursor.getString(cursor.getColumnIndex(Number));


           PhoneInfo phoneInfo=new PhoneInfo(Name,Number);
           phoneInfo.setName(nameString);
           phoneInfo.setNumber(numberString);

           phoneInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
           phoneInfos.add(phoneInfo);

       }
       db.close();
       return phoneInfos;
   }

    //*获取传递过来的参数
    public List<PhoneInfo> getAll_2(String info_name){
        //*传递过来的参数赋值
         String info_cM_name = info_name;
        //*做传递参数判断是否为空值
        if (info_cM_name != null && info_cM_name !="" ){

            //*两句正常的实例化
            List<PhoneInfo> phoneInfos = new ArrayList<PhoneInfo>();
            SQLiteDatabase db=dBhelp.getWritableDatabase();

            //* 定义占位符的语句，中括号内为内容，要用百分号把传递过来的字符包裹。
            //* 此语句是数组类型[]
            String select_mohu[] = new String[]{"%"+ info_cM_name + "%"};

            //* 在第三个参数selection中写入 [列名] (空格) like (空格) ?
            // *  selection参数相当于SQL语句中的where，问号相当于占位符
            // * 然后紧跟着的第四个参数就是占位符的内容，填上刚刚自定义好的占位符语句.
            Cursor cursor = db.query(
                    Table_name,    //*表名
                    new String[]{},
                    "name like ?",
                    select_mohu,
                    null,
                    null,
                    "id");

            //*相当于把GetNumber的封装操作再进行一次。
            //*遍历后封装。
            while (cursor.moveToNext()){
                String nameString=cursor.getString(cursor.getColumnIndex(Name));
                String numberString=cursor.getString(cursor.getColumnIndex(Number));
                PhoneInfo phoneInfo=new PhoneInfo(Name,Number);
                phoneInfo.setName(nameString);
                phoneInfo.setNumber(numberString);
                phoneInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                phoneInfos.add(phoneInfo);
            }
            db.close();
            return phoneInfos;
        }else {
            //*此处同样使用的是PhonInfo类，只不过List的名字变成了phonInfos_2以便于和上方区分。
            List<PhoneInfo> phoneInfos_2 = new ArrayList<PhoneInfo>();
            SQLiteDatabase db=dBhelp.getWritableDatabase();

            //*此处的语句是在判断搜索框没有字符时显示所有联系人
            Cursor cursor = db.query(Table_name, new String[]{}, null, null, null,null,"id");
            //*和上方一模一样
            while (cursor.moveToNext()){
                String nameString=cursor.getString(cursor.getColumnIndex(Name));
                String numberString=cursor.getString(cursor.getColumnIndex(Number));
                PhoneInfo phoneInfo=new PhoneInfo(Name,Number);
                phoneInfo.setName(nameString);
                phoneInfo.setNumber(numberString);
                phoneInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                phoneInfos_2.add(phoneInfo);
            }
            db.close();
            return phoneInfos_2;
        }
    }











/*
   //获取姓名来进行显示
    public List<String> getName(){

        //2020.07.07  准备加入实时搜索
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db=dBhelp.getWritableDatabase();


        Cursor cursor = db.query(Table_name, new String[]{}, null, null, null,null,"id");

        while (cursor.moveToNext()){
            String nameString=cursor.getString(cursor.getColumnIndex(Name));


            ContentValues values=new ContentValues();
            values.put(Name,phoneInfo.getName());
            values.put(Number,phoneInfo.getNumber());




            PhoneInfo phoneInfo=new PhoneInfo(Name,Number);
            phoneInfo.setName(nameString);
            phoneInfo.setNumber(numberString);
            phoneInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
            phoneInfos.add(phoneInfo);

        }
        db.close();
        return phoneInfos;



    }
*/





}
