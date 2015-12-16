package com.fanzhang.viewpagerdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ImageView cursor;// 图片指示器
    private int offset = 0;// 图片指示器偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 图片指示器宽度
    /**
     * viewPager组件
     */
    private ViewPager mViewPager;
    /**
     * 加载指示器的LinearLayout组件
     */
    private LinearLayout mNumLayout;
    /**
     * 加载tab页面的list
     */
    List<View> viewList;
    /**
     * 上一个指示器按钮
     */
    Button mPreSelectedBt;

    /**
     * 初始化组件
     */
    private void initView() {
        mNumLayout = (LinearLayout) findViewById(R.id.mNumLayout);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        cursor= (ImageView) findViewById(R.id.cursor);
    }

    /**
     * 入口
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使屏幕不显示标题栏(必须要在setContentView方法执行前执行)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏，使内容全屏显示(必须要在setContentView方法执行前执行)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //初始化组件
        initView();

        //初始化动画
        InitImageView();

        LayoutInflater mInflater = getLayoutInflater().from(this);

        View v1 = mInflater.inflate(R.layout.layout1, null);
        View v2 = mInflater.inflate(R.layout.layout2, null);
        View v3 = mInflater.inflate(R.layout.layout3, null);

        //添加页面数据
        viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);

        //初始化指示器
        Bitmap bitmap = BitmapFactory. decodeResource(getResources(), R.mipmap.icon_dot_darw);
        for (int i = 0; i < viewList.size(); i++) {
            Button bt = new Button(this );
            bt.setLayoutParams( new ViewGroup.LayoutParams(bitmap.getWidth(),bitmap.getHeight()));
            if(i==0){
                bt.setBackgroundResource(R.mipmap.icon_dot_normal );
            }else{
                bt.setBackgroundResource(R.mipmap.icon_dot_darw );
            }
            mNumLayout.addView(bt);
        }
        //设置第一个指示器为当前状态
        Button currentBt = (Button)mNumLayout.getChildAt(0);
        currentBt.setBackgroundResource(R.mipmap.icon_dot_normal);
        mPreSelectedBt = currentBt;

        //实例化适配器
        mViewPager.setAdapter(new MyPagerAdapter(viewList));
        mViewPager.setCurrentItem(0); //设置默认当前页
        //监听页面改变
        mViewPager.addOnPageChangeListener(new MyPagerChange());



        View view = viewList.get(0);
        TextView textView = (TextView) view.findViewById(R.id.text_1);
        textView.setText("我是第一页");
        Button button = (Button) view.findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "你点击了按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化图片指示器，这个就是页卡滑动时，下面的横线也滑动的效果，
     * 其实就是将图片指示器的位置移动一些距离，图形位置发生了变化，
     */
    private void InitImageView() {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.indicator).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }

    /**
     * 监听页面位置改变
     */
    private class MyPagerChange implements ViewPager.OnPageChangeListener {
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

        /**
         * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到 调用
         * @param position 当前页面，及你点击滑动的页面
         * @param positionOffset 当前页面偏移的百分比
         * @param positionOffsetPixels :当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         *此方法是页面跳转完后得到调用，position是你当前选中的页面的Position（位置编号）。
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            /**
             * float fromXDelta 动画开始的点离当前View X坐标上的差值
             * float toXDelta 动画结束的点离当前View X坐标上的差值
             * float fromYDelta 动画开始的点离当前View Y坐标上的差值
             * float toYDelta 动画开始的点离当前View Y坐标上的差值
             */
            Animation animation = new TranslateAnimation(one*currIndex, one*position, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = position;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);


            if (mPreSelectedBt != null){
                mPreSelectedBt .setBackgroundResource(R.mipmap.icon_dot_darw);
            }
            Button currentBt = (Button)mNumLayout.getChildAt(position);
            currentBt.setBackgroundResource(R.mipmap.icon_dot_normal );
            mPreSelectedBt = currentBt;
            //Log.i("INFO", "current item:" + position);
        }

        /**
         * 滚动状态改变时调用的方法。用于发现当用户拖动开始,当寻呼机自动解决当前页面,或当它完全停止/空闲
         * @param state ==1的时辰默示正在滑动，state==2的时辰默示滑动完毕了，state==0的时辰默示什么都没做
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    /**
     * Pager的适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        public MyPagerAdapter(List<View> viewList) {

        }

        /**
         * 设置窗体标题文字
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        /**
         * //获取当前窗体界面数
         * @return
         */
        @Override
        public int getCount() {
            return viewList.size();
        }

        /**
         * 初始化position位置的界面
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position), 0);
            return viewList.get(position);
        }

        /**
         * 判断是否由对象生成界面
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁position位置的界面
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            //Log.i("INFO", "destroy item:"+position);
            container.removeView(viewList.get(position));
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }
    }
}
