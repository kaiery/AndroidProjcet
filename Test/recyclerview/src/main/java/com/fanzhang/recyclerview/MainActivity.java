package com.fanzhang.recyclerview;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.fanzhang.adapter.ListAdapter;
import com.fanzhang.adapter.StraggerdAdpater;
import com.fanzhang.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

/****
 * 添加依赖包
 * recyclerview-v7-23.1.0
 * cardview-v7-23.1.0
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    /***
     * recyclerview组件
     */
    private RecyclerView mRecyclerView;
    /***
     * 下拉刷新组件
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;


    /***
     * 要显示的数据集合
     */
    private List<DataBean> mList = new ArrayList<DataBean>();
    private List<DataBean> mStraggerDatas = new ArrayList<DataBean>();

    /***
     * 模拟icon数据
     */
    private int[] mListIcons = new int[]{
            R.mipmap.g1,
            R.mipmap.g2,
            R.mipmap.g3,
            R.mipmap.g4,
            R.mipmap.g5,
            R.mipmap.g6,
            R.mipmap.g7,
            R.mipmap.g8,
            R.mipmap.g9,
            R.mipmap.g10,
            R.mipmap.g11,
            R.mipmap.g12,
            R.mipmap.g13,
            R.mipmap.g14,
            R.mipmap.g15,
            R.mipmap.g16,
            R.mipmap.g17,
            R.mipmap.g18,
            R.mipmap.g19,
            R.mipmap.g20,
            R.mipmap.g21,
            R.mipmap.g22,
            R.mipmap.g23,
            R.mipmap.g24,
            R.mipmap.g25,
            R.mipmap.g26,
            R.mipmap.g27,
            R.mipmap.g28,
            R.mipmap.g29
    };
    private int[] mStraggeredIcons = new int[]{R.mipmap.p1,
            R.mipmap.p2,
            R.mipmap.p3,
            R.mipmap.p4,
            R.mipmap.p5,
            R.mipmap.p6,
            R.mipmap.p7,
            R.mipmap.p8,
            R.mipmap.p9,
            R.mipmap.p10,
            R.mipmap.p11,
            R.mipmap.p12,
            R.mipmap.p13,
            R.mipmap.p14,
            R.mipmap.p15,
            R.mipmap.p16,
            R.mipmap.p17,
            R.mipmap.p18,
            R.mipmap.p19,
            R.mipmap.p20,
            R.mipmap.p21,
            R.mipmap.p22,
            R.mipmap.p23,
            R.mipmap.p24,
            R.mipmap.p25,
            R.mipmap.p26,
            R.mipmap.p27,
            R.mipmap.p28,
            R.mipmap.p29,
            R.mipmap.p30,
            R.mipmap.p31,
            R.mipmap.p32,
            R.mipmap.p33,
            R.mipmap.p34,
            R.mipmap.p35,
            R.mipmap.p36,
            R.mipmap.p37,
            R.mipmap.p38,
            R.mipmap.p39,
            R.mipmap.p40,
            R.mipmap.p41,
            R.mipmap.p42,
            R.mipmap.p43,
            R.mipmap.p44};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        //监听刷新
        mSwipeRefreshLayout.setOnRefreshListener(this);


        //0、模拟加载数据
        for (int i = 0; i < mListIcons.length; i++) {
            DataBean db = new DataBean();
            db.setContent("内容 - " + i);
            db.setIcon(mListIcons[i]);
            mList.add(db);
        }
        for (int i = 0; i < mStraggeredIcons.length; i++)
        {
            DataBean db = new DataBean();
            db.setContent("内容 - " + i);
            db.setIcon(mStraggeredIcons[i]);
            mStraggerDatas.add(db);
        }
        //list效果
        initList();

        //
    }


    private void initList() {
        //实现listview的效果
        //可以垂直滑动，也可以水平滑动，数据反向加载
        //使用方法如下：
        //1、设置布局管理器
        //1.1、先创建一个线性布局管理器，参数：上下文、方向、是否反向加载
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //1.2、设置布局
        mRecyclerView.setLayoutManager(layoutManager);
        //2、设置adapter
        //2.1、新建java类（ListAdapter）
        //2.2、传入数据
        mRecyclerView.setAdapter(new ListAdapter(this, mList));
    }

    private void initGrid() {
        //实现Gridview的效果
        //可以垂直滑动，也可以水平滑动，数据反向加载
        //使用方法如下：
        //1、设置布局管理器
        //1.1、先创建一个网格布局管理器，参数：上下文、列数、方向、是否反向加载
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        //1.2、设置布局
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //2、设置adapter
        //2.1、新建java类（ListAdapter）
        //2.2、传入数据
        mRecyclerView.setAdapter(new ListAdapter(this, mList));
    }

    private void initStragger() {
        //实现listView的效果
        // 可以垂直滑动，也可以水平滑动，数据反向加载
        //设置布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //设置adapter
        mRecyclerView.setAdapter(new StraggerdAdpater(this,mStraggerDatas));
    }

    /***
     * 重写刷新
     * 下拉刷新时的回调
     */
    @Override
    public void onRefresh() {
        //AsyncTask,是android提供的轻量级的异步类,可以直接继承AsyncTask,在类中实现异步操作,并提供接口反馈当前
        //异步执行的程度(可以通过接口实现UI进度更新),最后反馈执行的结果给UI主线程
        new AsyncTask<Void,Void,Void>(){
            //后台执行回调
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //休眠3秒
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获得mRecyclerView的adapter
                RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
                if(adapter instanceof  ListAdapter){
                    //模拟给list加载数据
                    for (int i = 0; i < mListIcons.length; i++) {
                        DataBean db = new DataBean();
                        db.setContent("内容 - " + i);
                        db.setIcon(mListIcons[i]);
                        mList.add(db);
                    }
                }else if(adapter instanceof StraggerdAdpater){
                    //模拟给瀑布流加载数据
                    for (int i = 0; i < mStraggeredIcons.length; i++)
                    {
                        DataBean db = new DataBean();
                        db.setContent("内容 - " + i);
                        db.setIcon(mStraggeredIcons[i]);
                        mStraggerDatas.add(db);
                    }
                }
                return null;
            }
            //重写提交执行
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                //获得mRecyclerView的adapter
                RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
                //adapter刷新
                adapter.notifyDataSetChanged();
                //通知刷新的view刷新完成
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }.execute();
    }















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,
                menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list) {
            initList();
            return true;
        } else if (id == R.id.action_grid) {
            initGrid();
            return true;
        } else if (id == R.id.action_stragger) {
            initStragger();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
