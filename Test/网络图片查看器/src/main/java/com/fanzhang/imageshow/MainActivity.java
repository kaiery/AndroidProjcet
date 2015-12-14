package com.fanzhang.imageshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanzhang.imageshow.handler.MyHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> pathlist;
    /**
     * 线程结束标记
     */
    private Boolean threadFlag = false;
    /**
     * 图片位置
     */
    private int postion = 0;
    public ImageView iv;
    public Button bt_pre;
    public Button bt_next;

    /**
     * 创建一个消息处理器
     */
    private  Handler mHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        bt_pre = (Button) findViewById(R.id.bt_pre);
        bt_next = (Button) findViewById(R.id.bt_next);

        loadAllImagePath();
        while (true) {
            //这里判断子线程是否运行完了
            if (threadFlag) {
                threadFlag = false;
                System.out.println("主线程结束");
                loadImageByPath(pathlist.get(0));
                break;
            }
        }
    }

    /**
     * 通过路径加载图片
     */
    private void loadImageByPath(final String path) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                //发送消息到消息队列，触发主线程执行
                Message msg = Message.obtain();
                try {
                    //定义缓存文件
                    String filename = path.substring(path.lastIndexOf("/")+1);
                    File file = new File(getCacheDir(),filename);
                    System.out.println("file.getAbsoluteFile():::::::::::"+file.getAbsoluteFile());
                    System.out.println("path::::::::::::::::::"+filename);
                    if(file.exists() && file.length()>0){
                        System.out.println("有缓存文件");
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        //iv.setImageBitmap(bitmap);
                        msg.what = MyHandler.GET_BITMAP;
                        msg.obj = bitmap;
                        mHandler.sendMessage(msg);
                    }else{
                        System.out.println("没有缓存文件");
                        //请求一个url
                        URL url = new URL("http://192.168.0.112:7001/Test"+path);
                        System.out.println("url::::::::::::::"+url.toString());
                        //打开连接
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //设置请求大写
                        conn.setRequestMethod("GET");
                        //获取服务器返回的状态码:200[ok];404[没有]；503[服务内部错误];302[重定向]
                        int responseCode = conn.getResponseCode();
                        if(responseCode==200){
                            //获取服务器返回的流
                            InputStream is = conn.getInputStream();
                            //图片工厂
                            //>解码一个输入流到一个位图。如果输入流为空,或者不能用于解码位图,函数返回null。
                            //>流的位置将是编码后的数据在哪里阅读。
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            //先缓存
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap.CompressFormat fileType = getFileType(path);
                            bitmap.compress(fileType,100,fos);
                            fos.close();
                            is.close();
                            msg.what = MyHandler.GET_BITMAP;
                            msg.obj = bitmap;
                            mHandler.sendMessage(msg);
                        }else if(responseCode==404){
                            //发送消息到消息队列，触发主线程执行
                            msg.what = MyHandler.GET_FAILURE;
                            mHandler.sendMessage(msg);
                        }else{
                            //发送消息到消息队列，触发主线程执行
                            msg.what = MyHandler.GET_ERROR;
                            msg.obj = "获取失败，资源没找到";
                            mHandler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    //发送消息到消息队列，触发主线程执行
                    msg.what = MyHandler.GET_ERROR;
                    msg.obj = "获取失败，服务器内部错误";
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }



    /**
     * 获取图片列表文件
     */
    private void loadAllImagePath() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                //发送消息到消息队列，触发主线程执行
                Message msg = Message.obtain();
                try {
                    //请求一个url
                    URL url = new URL("http://192.168.0.112:7001/Test/list.txt");
                    //打开连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置请求大写
                    conn.setRequestMethod("GET");
                    //conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
                    //获取服务器返回的状态码:200[ok];404[没有]；503[服务内部错误];302[重定向]
                    int responseCode = conn.getResponseCode();
                    if(responseCode==200){
                        //获取服务器返回的流
                        InputStream is = conn.getInputStream();
                        File file = new File(getCacheDir(),"list.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while (( len = is.read(buffer))!=-1){
                            fos.write(buffer, 0, len);
                        }
                        is.close();
                        fos.close();

                        //将文件列表放入数组
                        pathlist = new ArrayList<String>();
                        File file1 = new File(getCacheDir(),"list.txt");
                        FileInputStream fis = new FileInputStream(file1);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                        String line ;
                        while ((line = br.readLine())!=null){
                            pathlist.add(line);
                        }
                        fis.close();
                        br.close();
                        System.out.println("=======================================获取成功");
                        msg.what = MyHandler.GET_SUCCESS;
                        msg.obj = "获取成功";
                        mHandler.sendMessage(msg);
                    }else if(responseCode==404){
                        //发送消息到消息队列，触发主线程执行
                        msg.what = MyHandler.GET_FAILURE;
                        msg.obj = "获取失败，资源没找到";
                        mHandler.sendMessage(msg);
                    }else{
                        //发送消息到消息队列，触发主线程执行
                        msg.what = MyHandler.GET_ERROR;
                        msg.obj = "获取失败，服务器内部错误";
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    //发送消息到消息队列，触发主线程执行
                    msg.what = MyHandler.GET_ERROR;
                    msg.obj = "获取失败，服务器内部错误";
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }finally {
                    //这个子线程完毕了，标记一下
                    threadFlag = true;
                }
            }
        }.start();
    }

    /**
     * 根据图片扩展名，返回图片类型
     * @param path
     * @return
     */
    private Bitmap.CompressFormat getFileType(String path) {
        String fileType = path.substring(path.lastIndexOf(".")+1);
        System.out.println("fileType.toLowerCase():" + fileType.toLowerCase());
        switch( fileType.toLowerCase() )
        {
            case "jpg": {
                return Bitmap.CompressFormat.JPEG;
            }
            case "png": {
                return Bitmap.CompressFormat.PNG;
            }
            default: {
                return Bitmap.CompressFormat.JPEG;
            }
        }
    }


    /**
     * 上一张
     * @param view
     */
    public void prePic(View view){
        if(postion==0){
            postion = pathlist.size()-1;
        }else{
            postion--;
        }
        loadImageByPath(pathlist.get(postion));
    }

    /**
     * 下一张
     * @param view
     */
    public void nextPic(View view){
        if(postion==pathlist.size()-1){
            postion = 0;
        }else{
            postion++;
        }
        loadImageByPath(pathlist.get(postion));
    }

    /**
     * 取消掉该Handler对象的Message和Runnable
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
