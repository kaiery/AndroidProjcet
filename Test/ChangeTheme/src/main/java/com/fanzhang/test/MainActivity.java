package com.fanzhang.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //获取主题id
        int theme = getIntent().getIntExtra("theme", -1);
        if (theme != -1) {
            //改变主题
            setTheme(theme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
    }

    /***
     * 重启activity，改变主题
     *
     * @param theme
     */
    private void changeTheme(int theme) {
        finish();
        //Activity的切换动画指的是从一个activity跳转到另外一个activity时的动画
        //一个参数是第一个activity进入时的动画，另外一个参数则是第二个activity退出时的动画
        //要点：1、它必需紧挨着startActivity()或者finish()函数之后调用
        //要点：2、它只在android2.0以及以上版本上适用
        //要点：3、在ActivityGroup等的嵌入式Activity中，这个比较容易解决，用如下方法就可以了：this.getParent().overridePendingTransition 就可以解决
        overridePendingTransition(0,0);
        //必须在activity的setContentView之前调用
        //activity必须重启
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("theme", theme);
        startActivity(it);
    }

    public void changAppTheme(View v) {
        switch (v.getId()) {
            case R.id.bt1: {
                //这个地方第一个参数,传进去的是一个按钮，但是实际上你无论传进去什么值 snackbar都一定是从屏幕的最底端出现的
                Snackbar sb = Snackbar.make(bt1, "需要更改主题吗？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeTheme(R.style.AppTheme);
                    }
                });
                //设置背景色
                //sb.getView().setBackgroundColor(0xFFC5CAE9);
                sb.show();
                break;
            }
            case R.id.bt2: {
                //这个地方第一个参数,传进去的是一个按钮，但是实际上你无论传进去什么值 snackbar都一定是从屏幕的最底端出现的
                Snackbar sb = Snackbar.make(bt2, "需要更改主题吗？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeTheme(R.style.AppTheme2);
                    }
                });
                //设置背景色
                //sb.getView().setBackgroundColor(0xFFE1BEE7);
                sb.show();
                break;
            }
            case R.id.bt3: {
                //这个地方第一个参数,传进去的是一个按钮，但是实际上你无论传进去什么值 snackbar都一定是从屏幕的最底端出现的
                Snackbar sb = Snackbar.make(bt3, "需要更改主题吗？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeTheme(R.style.AppTheme3);
                    }
                });
                //设置背景色
                //sb.getView().setBackgroundColor(0xFFF8BBD0);
                sb.show();
                break;
            }
        }
    }


}
