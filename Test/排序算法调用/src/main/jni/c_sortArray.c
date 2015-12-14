#include <com_fanzhang_sortTest_MainActivity.h>

void insertSort(int datas[],int len){
    //
    int temp = 0;
    //外层的插入排序循环
    int i = 1;
    for (; i < len; i++){
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

void  Java_com_fanzhang_sortTest_MainActivity_insertSortC(JNIEnv *env, jobject obj, jintArray jia)
{
    jint* datas = (**env).GetIntArrayElements(env,jia,JNI_FALSE);
    jsize len = (**env).GetArrayLength(env,jia);

    insertSort(datas,len);
}
