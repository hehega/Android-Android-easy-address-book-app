package com.example.myapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class People_layout_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton add_imagebutton, call_imagebutton,sm_imagebutton;
    private TextView textView_name, textView_number/*,textView_number_2*/;
    private ContactManage cManage;
    private String input_id;

    private List<PhoneInfo_input> phoneInfosList_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_layout);

        textView_name = (TextView) findViewById(R.id.textView_people_name);
        textView_number = (TextView) findViewById(R.id.textView_people_number);
        call_imagebutton = (ImageButton) findViewById(R.id.imageButton_call);
        add_imagebutton = (ImageButton) findViewById(R.id.imageButton_add);
        sm_imagebutton = (ImageButton)findViewById(R.id.imageButton_sm);

        call_imagebutton.setOnClickListener(this);
        add_imagebutton.setOnClickListener(this);
        sm_imagebutton.setOnClickListener(this);


        setText();

        /*Toast.makeText(this,intent_number,Toast.LENGTH_SHORT).show();*/

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // *调用下方更新信息方法*
            case R.id.imageButton_add:
                Update_layout();
                break;
            // *调用下方拨打电话方法*
            case R.id.imageButton_call:
                call_phone();
                break;
            // *调用下方发送信息方法*
            case R.id.imageButton_sm:
                sm();
                break;

            default:
                break;
        }
    }
    //*以下为方法
    private void setText() {
        // *存入传递过来的参数（姓名，号码）*
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String intent_id = bundle.getString("item_id");
        String intent_name = bundle.getString("item_name");
        String intent_number = bundle.getString("item_number");


        textView_name.setText(intent_name);
        textView_number.setText(intent_number);

        input_id = intent_id;


    }


    private void Update_layout() {
    // *此方法为获取传递过来的参数并显示*
        String people_name, people_number;

        people_name = textView_name.getText().toString();
        people_number = textView_number.getText().toString();


        Intent i = new Intent(People_layout_Activity.this, UpDate_layout_Activity.class);

        Bundle bundle_2 = new Bundle();
        bundle_2.putString("the_id", input_id);
        bundle_2.putString("the_name", people_name);
        bundle_2.putString("the_number", people_number);

        i.putExtras(bundle_2);
        i.putExtra("people_name", people_name);
        i.putExtra("people_number", people_number);

        startActivity(i);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void call_phone() {
        // *此方法为获取显示的电话号码并调用手机本身的电话api接口来实现拨打电话*
        // *先判断是否授权拨打电话的权限，没有则获取*
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getPackageName(),null);
                intent.setData(uri);
                startActivity(intent);
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
            }
        }else {
            // *若权限已取得，则获取当前显示的电话号码字符*
            String phoneNumber = textView_number.getText().toString();
           Intent intent = new Intent();
           intent.setAction(Intent.ACTION_CALL);
           // *调用获取到的接口来直接拨打电话*
           intent.setData(Uri.parse("tel:"+phoneNumber));
           startActivity(intent);
        }

/*
        String phoneNumber = textView_number.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(intent);*/
}

private void sm(){
        // *此方法为向此页面显示的电话号码发送短信*
        // *首先判断是否获取发送短信的权限*
    if (ContextCompat.checkSelfPermission(People_layout_Activity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
        /*if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
            Uri smsToUri = Uri.parse("smsto:"+contactList.get(position).get("phone"));
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        }*/
        ActivityCompat.requestPermissions(People_layout_Activity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
    }else {
    // *调用手机本身的短信api接口，跳转到发送短信的页面*
    String phoneNumber = textView_number.getText().toString();
    Uri uri = Uri.parse("smsto:"+phoneNumber);
    Intent i = new Intent(Intent.ACTION_SENDTO,uri);
    startActivity(i);
    }
}
}
