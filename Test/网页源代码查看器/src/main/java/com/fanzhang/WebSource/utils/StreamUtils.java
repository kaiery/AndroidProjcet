package com.fanzhang.WebSource.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 范张 on 2015-11-21.
 */
public class StreamUtils {

    /**
     * 读取流数据，返回字符串
     * @param inputStream
     * @return
     */
    public static String readStreamToString(InputStream inputStream){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ( (len = inputStream.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            inputStream.close();
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取流数据，返回bitmap
     * @param inputStream
     * @return
     */
    public static Bitmap readStreamToBitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }
}
