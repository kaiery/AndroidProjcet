package com.fanzhang.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fanzhang.database.bean.UserBean;
import com.fanzhang.database.dataDao.DataDao;
import com.fanzhang.database.helper.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText et_name;
    private EditText et_phone;
    private EditText et_id;
    private String name;
    private String phone;
    private String id;
    private DataDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText)findViewById(R.id.et_name);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_id = (EditText)findViewById(R.id.et_id);
        dao = new DataDao(this,"fanzhang.db",1);
    }

    public void insert(View v){
        name = et_name.getText().toString();
        phone = et_phone.getText().toString();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){
            Toast.makeText(this,"不能为空",0).show();
        }else{
            long rowid = dao.insert(name, phone);
            if(rowid>0){
                Toast.makeText(this,"添加成功",0).show();
            }else{
                Toast.makeText(this,"添加失败",0).show();
            }
        }

    }

    public void delete(View v){
        id = et_id.getText().toString();
        if(TextUtils.isEmpty(id)){
            Toast.makeText(this,"不能为空",0).show();
        }else{
            int rowid =  dao.delete(Integer.parseInt(id));
            if(rowid==1){
                Toast.makeText(this,"删除成功",0).show();
            }else{
                Toast.makeText(this,"删除失败",0).show();
            }
        }
    }

    public void update(View v){
        id = et_id.getText().toString();
        phone = et_phone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"不能为空",0).show();
        }else{
            int rowid = dao.update(Integer.parseInt(id), phone);
            if(rowid==1){
                Toast.makeText(this,"修改成功",0).show();
            }else{
                Toast.makeText(this,"修改失败",0).show();
            }
        }
    }

    public void select(View v){
        List<UserBean> list = new ArrayList<UserBean>();
        System.out.println("+++++++++++++++++++++++++++");
        list = dao.select();
        if(list.size()>0){
            for (UserBean ub : list ) {
                System.err.println(ub.toString());
            }
            Toast.makeText(this,"查询成功",0).show();
        }else{
            Toast.makeText(this,"查询成功.但没有数据",0).show();
        }
    }
}
