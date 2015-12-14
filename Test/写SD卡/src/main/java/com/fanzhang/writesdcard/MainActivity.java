package com.fanzhang.writesdcard;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void  click(View view){
        //检查SD卡
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //检查SD卡剩余空间
            File sdfile = Environment.getExternalStorageDirectory();
            long sdsize = sdfile.getFreeSpace();
            if(sdsize>1024*1024){
                //定义文件
                File mfFile = new File(Environment.getExternalStorageDirectory(),"2015超级福利.rar");
                try {
                    //定义文件输出流
                    FileOutputStream fos = new FileOutputStream(mfFile);
                    //定义缓冲
                    byte[] buffer = new byte[1024];
                    for (int i = 0; i < 1024; i++) {
                        //输出流写字节
                        fos.write(buffer);
                    }
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void  click1(View view){
        //定义文件
        File mfFile = new File(Environment.getExternalStorageDirectory(),"2015超级福利.rar");
        if (mfFile.exists()){
            mfFile.delete();
        }
    }
}
