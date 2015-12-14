package com.fanzhang.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Fragment管理器
     */
    private FragmentManager manager;
    private FragMent1 fragMent1 ;
    private FragMent2 fragMent2 ;
    private FragMent3 fragMent3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化所有需要使用的Fragment
        initFragMent();
        manager = getSupportFragmentManager();
        showFragMent1(null);
    }

    /**
     * 初始化所有需要使用的Fragment
     */
    private void initFragMent() {
        fragMent1 = new FragMent1();
        fragMent2 = new FragMent2();
        fragMent3 = new FragMent3();
    }


    public void showFragMent1(View view){
        //Fragment事务管理
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl, fragMent1);
        transaction.commit();
    }
    public void showFragMent2(View view){
        //Fragment事务管理
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl, fragMent2);
        transaction.commit();
    }

    public void showFragMent3(View view){
        //Fragment事务管理
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl,fragMent3);
        transaction.commit();
    }

}
