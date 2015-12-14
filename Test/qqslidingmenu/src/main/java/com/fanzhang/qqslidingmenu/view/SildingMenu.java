package com.fanzhang.qqslidingmenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.fanzhang.qqslidingmenu.R;


public class SildingMenu extends HorizontalScrollView{

    /**
     * HorizontalScrollView中的线性布局
     */
    private LinearLayout mWapper;
    /**
     * 菜单元素
     */
    private ViewGroup mMenu;
    /**
     * 内容元素
     */
    private ViewGroup mContent;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;
    /**
     * 菜单宽度
     */
    private int mMenuWidth;
    /**
     * 菜单右侧偏移量
     */
    private int mMenuRightPadding = 50;

    private boolean once = false;

    /**
     * 默认状态
     */
    private boolean isOpen = false;

    /**
     * 未使用自定义属性时调用
     * @param context
     * @param attrs
     */
    public SildingMenu(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        //获得屏幕宽度
//        mScreenWidth = dm.widthPixels;
//        //dp转换为px
//        //将50DP值转换为等量的像素值
//        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());

        //如果使用了自定义属性，就调用三个参数的构造方法
        this(context,attrs,0);
    }

    /**
     * 一个参数的构造方法
     * @param context
     */
    public SildingMenu(Context context) {
        //将super改成this，调用2个参数的构造方法
        this(context, null);
    }

    /**
     * 使用了自定义属性时，就要调用3个参数的构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SildingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获得自定义属性数组
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SildingMenu,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i = 0; i < n ; i++) {
            //得到自定义属性
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.SildingMenu_rightPadding :
                    //给菜单右侧偏移量赋值，并给出默认值。
                    mMenuRightPadding = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        //回收垃圾
        a.recycle();

        //得到窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //得到显示指标
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //获得屏幕宽度
        mScreenWidth = dm.widthPixels;
        //dp转换为px
        //将50DP值转换为等量的像素值
        //mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }



    /**
     * 重写测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!once){
            //得到第一个布局
            mWapper = (LinearLayout) getChildAt(0);
            //得到菜单元素
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            //得到内容元素
            mContent = (ViewGroup) mWapper.getChildAt(1);
            //设置菜单的宽度
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            //设置内容的宽度
            mContent.getLayoutParams().width = mScreenWidth;
            //设置第一个布局的宽度,不用写了内部宽度决定了自身的宽度
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 重写布局
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            //通过偏移量将菜单先隐藏
            this.scrollTo(mMenuWidth,0);
        }
    }


    /**
     * 重写手势监听
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                //隐藏在左边的宽度 scrollX
                int scrollX = getScrollX();
                if(scrollX >= mMenuWidth/2){
                    //动画效果隐藏
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen = false;
                }else{
                    //动画效果完全显示菜单
                    this.smoothScrollTo(0,0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 打开菜单
     */
    public void openMenu(){
        if(isOpen){
            return;
        }else{
            //动画效果完全显示菜单
            this.smoothScrollTo(0,0);
            isOpen = true;
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu(){
        if(isOpen){
            //动画效果隐藏
            this.smoothScrollTo(mMenuWidth,0);
            isOpen = false;
        }else{
            return;
        }
    }

    /**
     * 切换
     */
    public void menuToggle(){
        if(isOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }
}
