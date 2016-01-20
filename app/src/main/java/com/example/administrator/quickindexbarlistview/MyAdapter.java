package com.example.administrator.quickindexbarlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class MyAdapter extends BaseAdapter {
    private List<Person> persons;
    private Context context;

    public MyAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
        if (persons!=null){
            Collections.sort(persons);
        }
    }

    @Override
    public int getCount() {
        return persons.size()==0?0:persons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item,null);
        }
        viewHolder= getHolder(convertView);
        viewHolder.name.setText(persons.get(position).getName());//内容

        String indexWord = persons.get(position).getPinYin().charAt(0)+"";

        if (position>0&&indexWord.equals(persons.get(position-1).getPinYin().charAt(0)+"")){//如果不是第一个并且这个的第一个拼音和上一个相等，则将拼音提示去掉
            viewHolder.index.setVisibility(View.GONE);
        }else{          //如果不是第一个并且这个的第一个拼音和上一个不相等  则加入拼音提示
            viewHolder.index.setVisibility(View.VISIBLE);
            viewHolder.index.setText(indexWord);

        }
        return convertView;

    }
    private ViewHolder getHolder(View convertView){
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder==null){
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        return holder;

    }

    static class ViewHolder{
        TextView index;
        TextView name;
        public ViewHolder(View convertView) {
            index = (TextView) convertView.findViewById(R.id.index);
            name = (TextView) convertView.findViewById(R.id.name);
        }
    }
}
