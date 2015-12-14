package com.fanzhang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 范张 on 2015-12-06.
 */
public class FragMent2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //修改返回值
        //return super.onCreateView(inflater, container, savedInstanceState);
        //返回一个View对象
        return inflater.inflate(R.layout.fragment2,container,false);
    }
}
