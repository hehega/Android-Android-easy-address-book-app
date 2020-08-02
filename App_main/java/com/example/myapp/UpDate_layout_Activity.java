package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpDate_layout_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_name,editText_number;
    private String UpdateName,UpdateNumber;
    private ImageButton update_yes_button,update_no_button;
    private ContactManage cManage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

  /*      setText();*/


        editText_name=(EditText)findViewById(R.id.update_name);
        editText_number=(EditText)findViewById(R.id.update_phoneNmbure);

        update_yes_button=(ImageButton) findViewById(R.id.update_yes_button);
        update_no_button=(ImageButton)findViewById(R.id.update_no_button);

        update_yes_button.setOnClickListener(this);
        update_no_button.setOnClickListener(this);


        cManage = new ContactManage(getApplicationContext());

        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.update_yes_button:
               /* yes_add();*/
                update_yes();
                break;
            case R.id.update_no_button:
                no_add();
                break;
            default:
                break;
        }
    }

    private void setText(){
    /*    Intent intent = getIntent();

        String people_name = intent.getStringExtra("people_name");
        String people_number = intent.getStringExtra("people_number");

        editText_name.setHint(people_name);
        editText_number.setHint(people_number);*/
    }

    private void  update_yes(){
        // *在写更新用户信息的时候遇到了自己结构上或者自己技术上的瓶颈*
        // *这里是对两个输入栏（EditText）进行了空值判断，若姓名栏为空则更新号码，反之亦然*
        // *本来向把上一个页面的号码和姓名数据传递过来显示，但不知怎的没发获取传递过来的参数。希望有大佬指点XD*
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String intent_id = bundle.getString("the_id");
        String intent_name = bundle.getString("the_name");
        String intent_number = bundle.getString("the_number");

        if(editText_name.length()== 0 ){
           /* Toast.makeText(this,"请填写姓名",Toast.LENGTH_SHORT).show();*/
            UpdateName = intent_name;
        }else {
            UpdateName=editText_name.getText().toString();

        }
        if (editText_number.length()== 0 ){
            /*Toast.makeText(this,"请填写电话号码",Toast.LENGTH_SHORT).show();*/
            UpdateNumber = intent_number;
        }else {
            UpdateNumber=editText_number.getText().toString();
        }

        try {

            PhoneInfo phoneInfo = new PhoneInfo(UpdateName,UpdateNumber);

            cManage.update(intent_id,phoneInfo);

            Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
            Intent i =new Intent(UpDate_layout_Activity.this,Home_Activity.class);
            startActivity(i);
           finish();
        }catch (Exception e){
            Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }

    private void no_add(){
        finish();
    }
   /* private  void  Update_layout(){
        String people_name,people_number;
        people_name=textView_name.getText().toString();
        people_number=textView_number.getText().toString();
        Intent i =new Intent(People_layout_Activity.this,UpDate_layout_Activity.class);
        i.putExtra("people_name",people_name);
        i.putExtra("people_number",people_number);
        startActivity(i);
    } */
    }
