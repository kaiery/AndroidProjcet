package com.fanzhang.shadowdemo;

import android.graphics.Outline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        //设置outLine（大纲阴影）
        //注意，此阴影效果不能向下兼容，只支持5.0以上
        iv.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //设置一个圆形
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
    }
}
