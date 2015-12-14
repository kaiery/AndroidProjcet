package com.fanzhang.guolu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seek_pro;
    private TextView tv_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        seek_pro = (SeekBar) findViewById(R.id.seek_jindu);
        seek_pro.setMax(99);
        tv_value = (TextView) findViewById(R.id.tv_viewyali);
    }


    /**
     * 静态调用so库文件
     */
    static{
        System.loadLibrary("guolu");
    }
    /**
     * 调用jni获取压力值
     * @return
     */
    public native void startMonitorFromC();

    /**
     * 停止监控
     */
    public native void stopMonitorFromC();


    /**
     * 给c调用
     * @param value
     *    压力值
     */
    public void showYaliValue(final int value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seek_pro.setProgress(value);
                tv_value.setText("当前压力值：" + value);
            }
        });

    }

    /**
     * 开始监控按钮
     * @param v
     */
    public void startMonitor(View v){
        new Thread(){
            public void run(){
                startMonitorFromC();
            }
        }.start();
    }

    /**
     * 停止监控按钮
     * @param v
     */
    public void stopMonitor(View v){
        stopMonitorFromC();
    }
}
