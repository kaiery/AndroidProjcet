package com.fanzhang.qqslidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;

import com.fanzhang.qqslidingmenu.view.SildingMenu;

public class MainActivity extends AppCompatActivity {

    private SildingMenu msilSildingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msilSildingMenu = (SildingMenu) findViewById(R.id.sm_menu);
    }

    public void  toggleMenu(View v){
        msilSildingMenu.menuToggle();
    }
}