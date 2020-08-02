package com.example.myapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private EditText et_name;
    private MyAdapter adapter;
    private ImageButton input_people,add_people;
    private  ContactManage cManage;
    private DBhelp dBhelp;
    private List<PhoneInfo> phoneInfosList;
    private List<String> nameInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_work_index);

       // *加载页面时同时调用函数
       // *调用加载权限的函数来获取权限。
       checjPermission();

       // *获取一个可读SQLite数据库
        DBhelp dBhelp=new DBhelp(Home_Activity.this);
        SQLiteDatabase sql_read=dBhelp.getReadableDatabase();


        // *注册监听器
        input_people=(ImageButton)findViewById(R.id.input);
        add_people=(ImageButton)findViewById(R.id.add);
        et_name = (EditText)findViewById(R.id.et_search);

        input_people.setOnClickListener(this);
        add_people.setOnClickListener(this);


        cManage = new ContactManage(getApplicationContext());

        // *刷新页面
        show_list();

        // *listviewdian点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int a = phoneInfosList.get(position).getId();//7.06
                String item_name = phoneInfosList.get(position).getName();
                String item_number=phoneInfosList.get(position).getNumber();
                /*String item_number_2=phoneInfosList.get(position).getNumber_2();*/



                // *传递点击的item到people_layout中显示
                Intent i =new Intent(Home_Activity.this,People_layout_Activity.class);
                Bundle bundle_1 = new Bundle();
                bundle_1.putString("item_id", String.valueOf(a));
                bundle_1.putString("item_name",item_name);
                bundle_1.putString("item_number",item_number);
                /*bundle_1.putString("item_number_2",item_number_2);*/
                i.putExtras(bundle_1);
                startActivity(i);
            }
        });

        // *listview长按事件，用来执行删除的功能
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final int a = phoneInfosList.get(position).getId();

                AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);

                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   cManage.delete(a);
                   adapter.notifyDataSetChanged();
                   show_list();
                    }
                }).show();

                return true;
            }
        });

        // *实时搜索，监听EditText
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              /*  String select_input =s.toString();
                phoneInfosList = cManage.getAll_2(select_input);
                show_list_2((PhoneInfo) phoneInfosList);
                replay();*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                String select_input =s.toString();
                phoneInfosList = cManage.getAll_2(select_input);
                //*此时返回来的参数是List类型，所以在传递给下一个方法时注意方法中的参数也要调整为List类型
                show_list_2(phoneInfosList);
                }else {
                    show_list();
                }
            }
        });


       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int a = phoneInfosList.get(position).getId();
                final AlertDialog.Builder edit1 = new AlertDialog.Builder(Home_Activity.this);
                edit1.setTitle("编辑联系人");
                edit1.setItems(new String[]{"删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0: AlertDialog.Builder edit3 = new AlertDialog.Builder(Home_Activity.this);
                            edit3.setTitle("删除联系人");
                            edit3.setMessage("确定删除么？");
                            edit3.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    cManage.delete(a);
                                    phoneInfosList = cManage.getAll();
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            edit3.setNeutralButton("取消",null);
                            edit3.create().show();
                            break;
                        }
                    }
                });

                return true;
            }
        });
*/
   /*     GetNumber.gerNumber(this);
        listView=(ListView) findViewById(R.id.list1);
        adapter=new MyAdapter(GetNumber.list,this);
        listView.setAdapter(adapter);
*/
     //   sqLiteButton_addDatabase=(Button)findViewById(R.id.button);
     //   sqLiteButton_addDatabase.setOnClickListener(this);

    }

    //按钮点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.input:
           /*     inpuPeople();*/
                /*inpuPeople_two();*/

                input_click();
                break;
            case R.id.add:
                 add_Layout();
                 break;
                default:
                    break;
        }
    }
    //*ListView点击事件
    //*已被上方的另一个点击事件替代
/*
    public void onItemClick(AdapterView<?>adapterView,View view, int position,long id){
        String item_name = phoneInfosList.get(position).getName();
        String item_number=phoneInfosList.get(position).getNumber();

        //传递点击的item到people_layout中显示
        Intent i =new Intent(Home_Activity.this,People_layout_Activity.class);
        i.putExtra("item_name",item_name);
        i.putExtra("item_number",item_number);
        startActivity(i);
    }
*/

/*
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
          String select_input = et_name.getText().toString();
          phoneInfosList = cManage.getAll_2(select_input);

        }
    };*/




//刷新页面方法
/*private  void  replay(){
    finish();
    Intent i = new Intent(Home_Activity.this,Home_Activity.class);
    startActivity(i);
}*/
// *刷新页面方法
private  void show_list(){
        try {
            phoneInfosList = cManage.getAll();
            listView=(ListView) findViewById(R.id.list1);
            adapter = new MyAdapter(phoneInfosList, this);
            listView.setAdapter(adapter);
           /* Toast.makeText(this,"成功导入数据库联系人",Toast.LENGTH_SHORT).show();*/
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(this,"导入SQLite联系人失败",Toast.LENGTH_SHORT).show();
        }
}
    private  void show_list_2(List<PhoneInfo> phoneInfo){
        try {
            phoneInfosList = phoneInfo;
            listView=(ListView) findViewById(R.id.list1);
            adapter = new MyAdapter(phoneInfosList,  this);
            listView.setAdapter(adapter);
             /*Toast.makeText(this,"成功导入数据库联系人",Toast.LENGTH_SHORT).show();*/
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(this,"导入SQLite联系人失败",Toast.LENGTH_SHORT).show();
        }
    }


/*    private  void show_list(){
        try {
            phoneInfosList = cManage.getAll();
            listView=(ListView) findViewById(R.id.list1);
            adapter = new MyAdapter(phoneInfosList,this);
            listView.setAdapter(adapter);
            */
/* Toast.makeText(this,"成功导入数据库联系人",Toast.LENGTH_SHORT).show();*//*
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            Toast.makeText(this,"导入SQLite联系人失败",Toast.LENGTH_SHORT).show();
        }
    }*/

// *跳转带增加联系人页面
private  void add_Layout(){
    Intent i =new Intent(Home_Activity.this,add_Activity.class);
    startActivity(i);

}
// *跳转到详细页面
private void people_layout(){
    Intent i =new Intent(Home_Activity.this,People_layout_Activity.class);
    startActivity(i);
}



/*private  final Runnable task = new Runnable() {
    @Override
    public void run() {
        if (run){
            handler.postDelayed(this,1000);
        }
    }
}
*/
//数据库创建
/*private  void  dbhelp(){
    try{
        DBhelp dBhelp=new DBhelp(Home_Activity.this);
        SQLiteDatabase sqLiteDatabase=dBhelp.getReadableDatabase();
        Toast.makeText(this,"SQLite数据库创建成功",Toast.LENGTH_SHORT).show();
    }catch (Exception e){
        Toast.makeText(this,"SQLite数据库创建失败",Toast.LENGTH_SHORT).show();
    }
}
*/

//导入本地联系人
/*
private   void  inpuPeople(){
    try {

        GetNumber.gerNumber(this);
        listView=(ListView) findViewById(R.id.list1);
        adapter=new MyAdapter(GetNumber.list,this);
        listView.setAdapter(adapter);
        Toast.makeText(this,"成功导入本地联系人",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

    }catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(this,"导入本地联系人失败",Toast.LENGTH_SHORT).show();
    }
}
*/

/*private  void  inpuPeople_two(){
    try {
   //导入联系人此处有问题

        GetNumber.gerNumber(this);
        GetNumber_2.gerNumber(this);

        String input_name,input_number;
        String input_name_2,input_number_2;

        *//*for (int x = 0; x < GetNumber.inputLists.size();x++){

            input_name=GetNumber.inputLists.get(x).getName();
            input_number=GetNumber.inputLists.get(x).getNumber();

              input_name_2 = GetNumber_2.inputLists_2.get(x).getName_2();
              input_number_2 = GetNumber_2.inputLists_2.get(x).getNumber_2();

            if (input_name_2 == input_name  ){
                int number_id = 0;
                SQLiteDatabase db=dBhelp.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name",input_name);
                values.put("number",input_number);
                values.put("number_2",input_number_2);
                db.insert("contacts",null,values);
                db.close();
            }else {
                input_number_2 = "   ";
                PhoneInfo_input phoneInfo_input = new PhoneInfo_input(input_name,input_number,input_number_2);
                cManage.insert(phoneInfo_input);
            }


            //测试用

           *//*




        Toast.makeText(this,"成功导入本地联系人",Toast.LENGTH_SHORT).show();

        show_list();
    }catch (Exception e){
        Toast.makeText(this,"导入本地联系人失败",Toast.LENGTH_SHORT).show();
    }
}*/

// * 导入本地联系人
    private  void  input_click(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);

        builder.setMessage("确认导入本地联系人吗？");
        builder.setTitle("提示");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              inpuPeople_two();
            }
        }).show();
    }


    private  void  inpuPeople_two(){
        try {
            GetNumber.gerNumber(this); // *调用GetNumber函数的方法
            String input_name,input_number;
            for (int x = 0; x < GetNumber.inputLists.size();x++){
                input_name=GetNumber.inputLists.get(x).getName();
                input_number=GetNumber.inputLists.get(x).getNumber();
                PhoneInfo phoneInfo = new PhoneInfo(input_name,input_number);
                cManage.insert(phoneInfo);
            }
            Toast.makeText(this,"成功导入本地联系人",Toast.LENGTH_SHORT).show();
            show_list();
        }catch (Exception e){
            Toast.makeText(this,"导入本地联系人失败",Toast.LENGTH_SHORT).show();
        }
    }


// *获取权限
    private  void  checjPermission(){

        //* 读取权限
      if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Home_Activity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            Toast.makeText(this,"已获得读取联系人授权。",Toast.LENGTH_SHORT).show();
        }

        //* 写入权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this,"请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }else {
            Toast.makeText(this,"授权成功！",Toast.LENGTH_SHORT).show();
        }
    }
}
