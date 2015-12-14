package com.fanzhang.imageshow.handler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.fanzhang.imageshow.MainActivity;

import java.lang.ref.WeakReference;

/**
 * 自定义handler类
 */
public class MyHandler extends Handler {
    public static final int GET_SUCCESS = 1;
    public static final int GET_FAILURE = 2;
    public static final int GET_ERROR  = 3;
    public static final int GET_BITMAP = 4;
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
                activity.iv.setImageBitmap((Bitmap) msg.obj);
                break;
            }
            default: {

                break;
            }

        }
    }

}
