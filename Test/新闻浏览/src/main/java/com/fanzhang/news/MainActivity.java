package com.fanzhang.news;

import android.app.ProgressDialog;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fanzhang.news.bean.NewsBean;
import com.fanzhang.news.handler.MyHandler;
import com.fanzhang.news.utils.JsonParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * 憨豆
     */
    private MyHandler myHandler = new MyHandler(this);
    public ListView lv;
    public LinearLayout ll_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        ll_loading = (LinearLayout) findViewById(R.id.ll_loading);
        //连接服务器获取新闻信息
        loadNewInfo();
    }

    /**
     * 获取新闻信息
     */
    private void loadNewInfo() {
        //提示加载
        ll_loading.setVisibility(View.VISIBLE);
        //联网
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message message = new Message();
                try {
                    URL url = new URL("http://192.168.0.112:7001/Test/queryNewsList");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(20000);
                    int code = conn.getResponseCode();
                    if(code==200){
                        //请求成功
                        InputStream is = conn.getInputStream();
                        List<NewsBean> newsList = JsonParser.getNewsList(is);

                        message.what = MyHandler.GET_NEWSLIST;
                        message.obj = newsList;
                        myHandler.sendMessage(message);
                    }else{
                        //请求失败
                        message.what = MyHandler.GET_ERROR;
                        message.obj = "请求失败";
                        myHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    message.what = MyHandler.GET_ERROR;
                    message.obj = "请求失败";
                    myHandler.sendMessage(message);
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
