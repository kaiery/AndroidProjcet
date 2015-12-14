package com.fanzhang.database.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context:上下文
     * @param name：数据库名称
     * @param factory：游标工厂:null 默认的游标工厂
     * @param version:版本:升级不能降级
     */
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 首次创建数据库时调用。这就是创建表和表的初始种群。
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE contactinfo ( \n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name  TEXT,\n" +
                "    phone TEXT \n" +
                ")");
    }

    /**
     * 时调用数据库需要升级。实现应该使用这种方法删除表,添加表或做其他事情需要升级到新模式版本。　　
     * SQLite ALTER TABLE文档可以在这里找到。如果您添加新列可以使用ALTER TABLE将它们插入到一个生活表。
     * 如果您重命名或删除列可以使用ALTER TABLE重命名旧表,然后创建新表,然后填充新表与旧表的内容。　　
     * 这个方法执行一个事务。如果抛出一个异常,所有更改将自动回滚。
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
