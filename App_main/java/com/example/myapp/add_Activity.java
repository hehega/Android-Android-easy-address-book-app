package com.example.myapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class add_Activity extends AppCompatActivity implements View.OnClickListener {
    //*定义
    private ImageButton add_yes,add_no;
    private EditText add_layout_name,add_layout_number;
    private String AddName,AddNumber;
    //*数据库操作类
    private  ContactManage cManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);


        add_yes=(ImageButton)findViewById(R.id.add_yes_button);
        add_no=(ImageButton)findViewById(R.id.add_no_button);
        add_layout_name=(EditText)findViewById(R.id.add_name);
        add_layout_number=(EditText)findViewById(R.id.add_phoneNmbure);


        add_yes.setOnClickListener(this);
        add_no.setOnClickListener(this);

        cManage = new ContactManage(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //*当点击添加按钮
            case R.id.add_yes_button:
                  yes_add();
                break;
            //*当点击取消按钮
            case R.id.add_no_button:
                 no_add();
                break;
            default:
                break;
        }
    }

  /*  protected void arry_phone(){
        Spinner spinner=(Spinner)findViewById(R.id.spinner_phone);
        String[] phone_type =new String[]{"手机","住宅","单位","总机","单位传真","住宅传真","寻呼机"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,phone_type);
        spinner.setAdapter(adapter);*/

    //    Spinner spinner2=(Spinner)findViewById(R.id.spinner_email);
    //    String[] email_type =new String[]{"私人","单位","其他"};
    //    ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,email_type);
    //    spinner2.setAdapter(adapter2);

  /*  }*/

      //*点击X后返回主页
    protected void  no_add(){
      finish();
    }

    //*点击添加进行的函数
    protected void yes_add(){
        //*此处进行了空值判断，所以在添加联系人时姓名和电话号码的都不能为空。
        if(add_layout_name.length()== 0 ){
            Toast.makeText(this,"请填写姓名",Toast.LENGTH_SHORT).show();
        }else {
            AddName=add_layout_name.getText().toString();//*姓名
            if (add_layout_number.length()== 0 ){
                Toast.makeText(this,"请填写电话号码",Toast.LENGTH_SHORT).show();
            }else {
                AddNumber = add_layout_number.getText().toString();//*电话号码
                try {
                    PhoneInfo phoneInfo = new PhoneInfo(AddName, AddNumber/*,AddNumber2*/);
                    if (cManage.insert(phoneInfo)) {
                        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(add_Activity.this, Home_Activity.class);
                        startActivity(i);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }







}
