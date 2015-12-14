package com.fanzhang.dialogs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own actionReplace with your own actionReplace" +
                        " with your own actionReplace with your own actionReplace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    /**
     * 确定取消
     * @param view
     */
    public void click1(View view){
        //工厂模式
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定跟取消按钮，你选哪个？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "您点击了确定", 0).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "您点击了取消", 0).show();
            }
        });
        builder.show();
    }




    /**
     * 单选对话框
     * @param view
     */
    public void click2(View view){
        //工厂模式
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你的性别");
        final String[] items = {"男","女","人妖","女汉子"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "您点击了" + items[which], 0).show();
                //关闭对话框
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 复选对话框
     * @param view
     */
    public void click3(View view){
        //工厂模式
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你的兴趣爱好");
        final String[] items = {"打篮球","踢足球","看电影","玩游戏"};
        final boolean[] checked = {false,false,true,true};
        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "您点击了" + items[which]+isChecked, 0).show();
                checked[which] = isChecked;
            }
        });
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < checked.length; i++) {
                    if (checked[i] == true) {
                        sb.append(items[i] + " ");
                    }
                }
                Toast.makeText(getApplicationContext(), "您的兴趣爱好有：" + sb.toString(), 0).show();
            }
        });
        builder.show();
    }


    /**
     * 进度对话框
     * @param view
     */
    public void click4(View view){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setMessage("正在拼命加载");
        pd.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }.start();
    }


    /**
     * 真进度对话框
     * @param view
     */
    public void click5(View view){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);
        pd.setMessage("正在拼命加载");
        pd.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(300);
                        pd.setProgress(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }.start();
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
