package com.fanzhang.toolbardemo;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //定义一个toolbar，代替actionbar使用
    private Toolbar toolbar;

    //定义一个drawerLayout
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //设置actionbar
        /***
         * 设置工具栏作为这个活动的ActionBar窗口。
         * 当设置为一个非空值getActionBar()方法将返回一个ActionBar对象,
         * 可用于控制给定的工具栏就好像它是一个传统的窗口装饰操作栏。
         * 工具栏的菜单将填充活动的选项菜单和导航按钮将通过标准的有线家庭菜单选择行动。　　
         * 为了使用工具栏内活动的窗口内容FEATURE_SUPPORT_ACTION_BAR不得请求窗口的应用程序功能
         */
        setSupportActionBar(toolbar);


        //初始化抽屉的开关
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        //设置监听
        drawerLayout.setDrawerListener(mDrawerToggle);
        //开启同步
        mDrawerToggle.syncState();



    }


}
