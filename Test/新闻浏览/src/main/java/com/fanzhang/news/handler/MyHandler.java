package com.fanzhang.news.handler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;


import com.fanzhang.news.MainActivity;
import com.fanzhang.news.adapter.NewsAdapter;
import com.fanzhang.news.bean.NewsBean;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 自定义handler类
 */
public class MyHandler extends Handler {
    public static final int GET_SUCCESS = 1;
    public static final int GET_FAILURE = 2;
    public static final int GET_ERROR  = 3;
    public static final int GET_BITMAP = 4;
    public static final int GET_HTMLSOURCE = 5;
    public static final int GET_NEWSLIST = 6;
    /**
     * 主程序的弱引用
     */
    private WeakReference<MainActivity> mActivity;

    /**
     * 构造方法，传递主程序
     * @param mainActivity
     */
    public MyHandler(MainActivity mainActivity) {
        mActivity = new WeakReference<MainActivity>(mainActivity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        MainActivity activity = mActivity.get();
        if(activity == null){
            return;
        }
        switch( msg.what )
        {
            case 1: {
                Toast.makeText(activity.getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
                Toast.makeText(activity.getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                break;
            }
            case 3: {
                Toast.makeText(activity.getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                break;
            }
            case 4: {
                //activity.iv.setImageBitmap((Bitmap) msg.obj);
                break;
            }
            case 5: {
                //activity.tv.setText(msg.obj.toString());
                break;
            }
            case 6: {
                //关闭加载进度框
                activity.ll_loading.setVisibility(View.INVISIBLE);
                activity.lv.setAdapter(  new NewsAdapter((List<NewsBean>) msg.obj,activity.getApplicationContext())  );
                break;
            }
            default: {

                break;
            }

        }
    }

}
