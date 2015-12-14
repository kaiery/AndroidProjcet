#include "com_fanzhang_cLogin_MainActivity.h"
#include "stdio.h"
#include "stdlib.h"
/**
 * 登录逻辑
 */
int check(char* name,char* pass)
{
        if (strcmp(name,"andy") == 0) {//名字正确
                if (strcmp(pass,"123") == 0){//密码正确
                        return 200;
                } else {
                        return 503;//密码不正确
                }
        } else {
                return 404;
        }
}


// java中的字符串对象
// 调用字符串对象的getBytes方法，获取到的字节数组和c完全一样
//c中的字符指针
char* string2c(JNIEnv *env,jstring jstrObj){
        //调用字符串对象的getBytes方法（分析）
        //反射
        //1,获取class   java.lang.String
        //  jclass      (*FindClass)(JNIEnv*, const char*);
        jclass  type = (*env)->FindClass(env,"java/lang/String");
        //2,获取method  getBytes("utf-8")
        //  jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
        //  cmd 中执行：javap -s java.lang.String ，找到byte[] getBytes(java.lang.String)
        jmethodID mid = (*env)->GetMethodID(env,type,"getBytes","(Ljava/lang/String;)[B");
        //3,jstr java中传递过来的对象
        //4,调用 返回字节数组
        //  jobject     (*CallObjectMethod)(JNIEnv*, jobject, jmethodID, ...);
        //  jstring  char* cstr = "utf-8";
        //  把c的字符转成java
        jstring jencode = (*env)->NewStringUTF(env,"utf-8");
        //5、得到java中的字节数组
        jbyteArray jba = (*env)->CallObjectMethod(env,jstrObj,mid,jencode);
        //把 java中字节数组转成c字节数组 jbyte 就是signed char
        jbyte* datas = (**env).GetByteArrayElements(env,jba,JNI_FALSE);
        //获取数组的长度
        jsize len = (**env).GetArrayLength(env,jba);

        char* cstr = malloc(len + 1);// 1 加字符串的结束标记
        memcpy(cstr,datas,len);//拷贝字符串strcpy
        //添加\0字符串终结标识符
        cstr[len] = '\0';
        /*
        void        (*ReleaseByteArrayElements)(JNIEnv*, jbyteArray,   jbyte*, jint);
                                */
        //释放 字符串获取到的字节数组对象
        /**
         * void (*ReleaseByteArrayElements)(JNIEnv*, jbyteArray, jbyte*, jint);
           参数：
                env：JNI 接口指针。
                array：Java 数组对象。
                elems：指向数组元素的指针。
                mode：释放模式。 0 正常
         */
        (*env)->ReleaseByteArrayElements(env,jba,datas,0);
        return cstr;
}

jint  Java_com_fanzhang_cLogin_MainActivity_checkLogin  (JNIEnv * env, jobject obj, jstring jname, jstring jpass)
{
        //现成写好的调用方法：char* cname = (*env)->GetStringUTFChars(env,jname,0);
        char* cname = string2c(env,jname);
        char* cpass = string2c(env,jpass);
        int res =  check(cname,cpass);
        free(cname);
        free(cpass);
        return res;
}