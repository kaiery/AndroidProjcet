package com.fanzhang.listviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fanzhang.listviewdemo.bean.UserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<UserBean> list = new ArrayList<UserBean>();
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //数据模型
        initData();
        //视图
        lv = (ListView) findViewById(R.id.lv);
        //控制器
        lv.setAdapter(new BaseAdapter() {
            /**
             *  获取列表里的记录数量
             */
            @Override
            public int getCount() {
                return list.size();
            }

            /**
             * 返回一个view对象，这个view对象显示在指定的位置
             * @param position
             * @param convertView
             * @param parent
             * @return
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv;
                if(convertView==null){
                    tv = new TextView(MainActivity.this);
                }else {
                    System.err.println("--------"+position);
                    tv = (TextView) convertView;
                }
                tv.setText(list.get(position).getName()+"_"+list.get(position).getPhone());
                tv.setTextColor(0xffdd0000);
                tv.setTextSize(24);
                return tv;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }
        });
    }

    /**
     * 初始化数据模型
     */
    private void initData() {
        for (int i = 0; i < 13 ; i++) {
            UserBean ub = new UserBean();
            ub.setId(i);
            ub.setName("文本" + i);
            ub.setPhone( new Random().nextInt(100000)+"");
            list.add(ub);
        }
    }


}