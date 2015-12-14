package com.fanzhang.tween;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }

    /**
     * 透明度
     * @param view
     */
    public void alpha(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(alphaAnimation);
    }

    /**
     * 缩放
     *
     * @param view
     */
    public void scale(View view) {
        //fromX 缩放开始从X点
        //toX 缩放结束到X点
        //fromY 缩放开始从Y点
        //toY 缩放结束到Y点
        //pivotXType 中心点类型指定应该如何解释pivotXValue。动画之一。绝对的,动画。RELATIVE_TO_SELF或Animation.RELATIVE_TO_PARENT。
        //pivotXValue 他的X坐标点哪些对象正在扩大,指定为一个绝对的数字0是左边缘的位置。(这一点仍然是固定的,而对象变化的大小。)这个值可以是一个绝对数量如果pivotXType是绝对的,或一个百分比(1.0 100%)。
        //pivotYType
        //pivotYValue
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(scaleAnimation);
    }

    /**
     * 平移
     * @param view
     */
    public void trans(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                                                                        Animation.RELATIVE_TO_SELF, 2f,
                                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                                        Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(translateAnimation);
    }


    /**
     * 旋转
     * @param view
     */
    public void rotate(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        iv.startAnimation(rotateAnimation);
    }


    /**
     * 合集
     */
    public void set(View view){
        AnimationSet set = new AnimationSet(false);

        RotateAnimation rotateAnimation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 2f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        set.addAnimation(rotateAnimation);
        set.addAnimation(translateAnimation);
        set.addAnimation(scaleAnimation);

        iv.startAnimation(set);
    }

}
