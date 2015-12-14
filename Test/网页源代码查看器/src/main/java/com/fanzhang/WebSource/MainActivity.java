package com.fanzhang.WebSource;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fanzhang.WebSource.handler.MyHandler;
import com.fanzhang.WebSource.utils.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    public TextView tv;
    private MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);

    }

    /**
     * 查看源代码
     * @param view
     */
    public void viewhtml(View view){
        final String path = et.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(getApplicationContext(),"网址不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message message = Message.obtain();
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    System.out.println("-------------------------------------------:" + conn.getResponseMessage());
                    if(responseCode==200){
                        InputStream is = conn.getInputStream();
                        String htmlSource = StreamUtils.readStreamToString(is);

                        message.obj = htmlSource;
                        message.what = MyHandler.GET_HTMLSOURCE;
                        myHandler.sendMessage(message);
                    }else{
                        message.obj = "加载失败";
                        message.what = MyHandler.GET_FAILURE;
                        myHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    message.obj = "加载失败";
                    message.what = MyHandler.GET_FAILURE;
                    myHandler.sendMessage(message);
                    e.printStackTrace();
                } finally {
                }

            }
        }.start();
    }
}
