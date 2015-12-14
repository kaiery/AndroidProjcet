package com.fanzhang.CallLog;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getCallLog(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://call_log/calls");
        String resolverType = resolver.getType(uri);
        System.out.println("resolverType:"+resolverType);
        Cursor cursor = resolver.query(uri, new String[]{"number", "date", "type"}, null, null, null);
        while (cursor.moveToNext()){
            String number = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            System.out.println("number:" + number + "--date:" + date + "--type:" + type);
        }
        cursor.close();
    }
}
