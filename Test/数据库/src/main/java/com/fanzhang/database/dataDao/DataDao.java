package com.fanzhang.database.dataDao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fanzhang.database.bean.UserBean;
import com.fanzhang.database.helper.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-15.
 */
public class DataDao {

    /**
     * 数据库帮助类
     */
    private DbHelper mHelper;

    private SQLiteDatabase db;

    /**
     * 构造方法，传递DbHelper所需的参数
     * @param context
     * @param name
     * @param ver
     */
    public DataDao(Context context,String name,int ver) {
        //、声明一个数据库类
        mHelper = new DbHelper(context,name,null,ver);
    }
    /**
     * 插入记录
     * @param name
     * @param phone
     */
    public long insert(String name ,String phone){
        //获取一个数据库（可写的）
        db = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("phone", phone);
        long rowid = db.insert("contactinfo", null, cv);
        db.close();
        return rowid;
    }

    public int delete(Integer id){
        int rowid = 0;
        //获取一个数据库（可写的）
        db = mHelper.getWritableDatabase();
        db.beginTransaction();  //手动设置开始事务
        try {
            rowid = db.delete("contactinfo", "id=?", new String[]{id.toString()});
            if(rowid==1){
                db.setTransactionSuccessful(); //设置事务处理成功，不设置会自动回滚不提交
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); //处理完成
            db.close();
        }
        return rowid;
    }

    public int update(Integer id ,String phone){
        int rowid = 0;
        //获取一个数据库（可写的）
        db = mHelper.getWritableDatabase();
        db.beginTransaction();  //手动设置开始事务
        try {
            ContentValues cv = new ContentValues();
            cv.put("phone", phone);
            rowid = db.update("contactinfo",cv,"id=?",new String[]{id.toString()});
            if(rowid==1){
                db.setTransactionSuccessful(); //设置事务处理成功，不设置会自动回滚不提交
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();//处理完成
            db.close();
        }
        return rowid;
    }

    public List<UserBean> select(){
        //获取一个数据库（可写的）
        db = mHelper.getReadableDatabase();
        List<UserBean> list = new ArrayList<UserBean>();
        Cursor cursor = db.rawQuery("select * from contactinfo ", null);
        if(cursor.getCount()==0){
            System.out.println("空数据");
            return list;
        }
        System.out.println("有数据:"+cursor.getCount()+"条");
        while (cursor.moveToNext()){
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            UserBean mUserBean = new UserBean(id,name,phone);
            list.add(mUserBean);
        }
        cursor.close();
        db.close();
        return list;
    }
}
