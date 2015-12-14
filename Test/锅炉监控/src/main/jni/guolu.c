#include "stdlib.h"
#include "stdio.h"
#include "com_fanzhang_guolu_MainActivity.h"

#include <android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

int flag = 0;

/**
 * 反射机制调用java的showYaliValue方法，传递压力值
 */
void showValue2Java(JNIEnv *env,jobject obj,int value){
        //class
        jclass type = (*env)->FindClass(env,"com/fanzhang/guolu/MainActivity");
        //method
        jmethodID mid = (*env)->GetMethodID(env,type,"showYaliValue","(I)V");
        //obj
        //invoke
        (*env)->CallVoidMethod(env,obj,mid,value);
        //***************
        //******销毁本地反射，不然会引起内存泄漏********
        (*env)->DeleteLocalRef(env,type);
}

/**
 * 获得压力
 */
int getYali()
{
        //代码比较长
        return rand() % 100;
}

/*C代码开始监控
 * Class:     com_fanzhang_guolu_MainActivity
 * Method:    startMonitorFromC
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_fanzhang_guolu_MainActivity_startMonitorFromC(JNIEnv *env, jobject obj)
{
        LOGI("%d\n",1);
        flag = 1;
        while(flag){
                int value = getYali();
                showValue2Java(env,obj,value);
                sleep(1);
        }
}

/*C代码停止监控
 * Class:     com_fanzhang_guolu_MainActivity
 * Method:    stopMonitorFromC
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_fanzhang_guolu_MainActivity_stopMonitorFromC(JNIEnv *env, jobject obj)
{
        flag = 0;
}