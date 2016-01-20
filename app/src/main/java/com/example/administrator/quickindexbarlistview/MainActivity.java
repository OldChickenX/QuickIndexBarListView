package com.example.administrator.quickindexbarlistview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧A-Z通过自定义View实现，
 * 点击索引 通过listview的listview.setselection(position)来到达listview的顶部
 *
 * 使用pinyin第三方的时候需要在build.gradle内加入以下代码
 *       packagingOptions {
     exclude 'META-INF/maven/com.belerweb/pinyin4j/pom.xml'
     exclude 'META-INF/maven/com.belerweb/pinyin4j/pom.properties'
     }
 */
public class MainActivity extends AppCompatActivity implements IndexBar.OnIndexSelectedListener {

    private IndexBar indexBar;
    private ListView listView;
    private List<Person> persons;
    private MyAdapter myAdapter;
    private TextView suoyin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //索引提示
        suoyin = (TextView) findViewById(R.id.suoyin);

        //右侧索引控件
        indexBar = ((IndexBar) findViewById(R.id.indexBar));
        indexBar.setOnIndexSelectedListener(this);

        //左侧listview
        listView = ((ListView) findViewById(R.id.listView));
        persons=new ArrayList<>();

        persons.add(new Person("飞洒"));
        persons.add(new Person("干撒高"));
        persons.add(new Person("啊是伽师瓜"));
        persons.add(new Person("第三方和"));
        persons.add(new Person("瑞特"));
        persons.add(new Person("的份上"));
        persons.add(new Person("额也让"));
        persons.add(new Person("适度腐败啥地方"));
        persons.add(new Person("啥地方洒"));
        persons.add(new Person("很多事"));
        persons.add(new Person("好的大幅度是"));
        persons.add(new Person("活生生的"));
        persons.add(new Person("额外人"));
        persons.add(new Person("符合"));
        persons.add(new Person("和人"));
        persons.add(new Person("金额图"));
        persons.add(new Person("而为台湾"));
        persons.add(new Person("的双方各"));
        persons.add(new Person("的份上"));
        persons.add(new Person("人工费是"));
        persons.add(new Person("而我国高"));
        persons.add(new Person("发的水电费"));
        persons.add(new Person("二五热管"));
        persons.add(new Person("的双方各"));
        persons.add(new Person("特让我听"));
        persons.add(new Person("发的鬼地方是"));
        persons.add(new Person("瓦特"));
        persons.add(new Person("和二恶"));
        persons.add(new Person("没买过"));
        persons.add(new Person("来衡量"));
        persons.add(new Person("联合国了"));
        persons.add(new Person("妈妈"));
        persons.add(new Person("那你还"));
        persons.add(new Person("你的飞"));
        persons.add(new Person("哦经济法"));
        persons.add(new Person("偶偶i"));
        persons.add(new Person("璞玉普用品"));
        persons.add(new Person("朋友退款"));
        persons.add(new Person("全国各水电费"));
        persons.add(new Person("奇怪的风格"));
        persons.add(new Person("让让让"));
        persons.add(new Person("如果恢复到"));
        persons.add(new Person("退换货好"));
        persons.add(new Person("为我国的风格"));
        persons.add(new Person("我热土上"));
        persons.add(new Person("幸福好得很"));
        persons.add(new Person("新股申购"));
        persons.add(new Person("这个地方是固定"));

        myAdapter = new MyAdapter(persons,this);
        listView.setAdapter(myAdapter);

    }

    @Override
    public void onIndexSelected(String word) {
        //遍历数据源的所有  判断是否和点击的字母索引一致  一致就设置listview到那个条目，并且跳出循环
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (word.equals(person.getPinYin().charAt(0)+"")){
                listView.setSelection(i);
                showSuoyin(word);
                return;
            }
        }
    }


    //handler控制索引提示关闭
    private Handler handler = new Handler();
    //展示索引提示
    private void showSuoyin(String word) {
        suoyin.setText(word);
        suoyin.setVisibility(View.VISIBLE);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                closeSuoyin();
            }
        },2000);

    }

    //关闭索引提示
    private void closeSuoyin() {
        suoyin.setVisibility(View.GONE);
    }
}
