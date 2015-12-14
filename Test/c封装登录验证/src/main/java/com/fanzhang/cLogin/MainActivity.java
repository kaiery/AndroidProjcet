package com.fanzhang.cLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pass;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
    }

    /**
     * 处理登陆事件
     * @param v
     */
    public void check(View v){
        String name = et_name.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        int res = checkLogin(name, pass);
        switch (res) {
            case 200:
                Toast.makeText(this, "登陆成功", 1).show();
                break;
            case 404:
                Toast.makeText(this, "名字不存在", 1).show();
                break;
            case 503:
                Toast.makeText(this, "密码错误", 1).show();
                break;

            default:
                break;
        }
		/*//判断的逻辑 放到c中实现
		if (name.equals("andy") && pass.equals("123")){
			Toast.makeText(this, "登陆成功", 1).show();
		} else {
			Toast.makeText(this, "用户名或密码错误", 1).show();
		}*/
    }



    /**
     * 调用本地C的方法实现登录逻辑
     * @param name
     * @param pass
     * @return
     *  404 name不存在
     *  503 pass错误
     *  200  登陆成功
     */
    public native int checkLogin(String name,String pass);


    /**
     * 静态调用动态库
     */
    static{
        System.loadLibrary("checklogin");
    }
}
