package com.example.myapp;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<PhoneInfo>lists;
    private Context context;
  //  private LinearLayout layout;

    public MyAdapter(List<PhoneInfo> lists, Context context){
        this.lists=lists;
        this.context=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


      //     LayoutInflater inflater=LayoutInflater.from(context);
     //  layout = (LinearLayout) inflater.from(context).inflate(R.layout.adapter_view,null);

     //   TextView NameTextView=(TextView) layout.findViewById(R.id.name);
     //   TextView NumberTextView=(TextView)layout.findViewById(R.id.number);

     //   NameTextView.setText(lists.get(position).getName());
     //   NumberTextView.setText(lists.get(position).getNumber());
      final  ViwHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_view,null);

            holder = new ViwHolder();

            holder.nametextview =(TextView) convertView .findViewById(R.id.name);
            holder.nametextview.setText(lists.get(position).getName());


            convertView.setTag(holder);

        }else {
            holder = (ViwHolder) convertView.getTag();
            holder.nametextview.setText(lists.get(position).getName());

        }

        return convertView;

    }

    private  static  class ViwHolder{
        TextView nametextview;

    }


}
