package com.fanzhang.sortTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    static{
        System.loadLibrary("c_sortArray");
    }
    /**
     * 定义native方法 来调用c的实现 来完成排序
     * @param datas
     */
    public native void insertSortC(int datas[]);
    /**
     * 用插入排序算法实现排序
     * @param datas
     */
    public void insertSort(int datas[]){
        //
        int temp = 0;
        //外层的插入排序循环
        int i = 1;
        for (; i < datas.length; i++){
            temp = datas[i];

            int j  = (i - 1);
            for (; j >=0 ;j--){
                if (datas[j] > temp){ //比参照物的值大
                    datas[j + 1] = datas[j];//后瞬移一位
                } else {
                    break;//跳出循环
                }
            }
            datas[j + 1] = temp;
        }
    }

    public void init(int[] datas){
        for (int i = 0; i < datas.length ; i++){
            datas[i] = (int)(Math.random() * 100) + 1;
        }
    }

    /**
     *
     * @param datas
     */
    public void print(int[] datas){
        for (int i = 0; i < datas.length ;i++){
            System.out.print(datas[i] + ",");
        }
        System.out.println();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                super.run();
                int datas[] = new int[50000];
                init(datas);//给数组赋随机值
                //print(datas);
                long start = System.currentTimeMillis();
                //System.out.println("排序前：" + new Date());
                insertSort(datas);//Java
                //insertSortC(datas);//native
                long end = System.currentTimeMillis();
                System.out.println(end - start);
                //System.out.println("排序后：" );
                //print(datas);
            }
        }.start();
    }
}
