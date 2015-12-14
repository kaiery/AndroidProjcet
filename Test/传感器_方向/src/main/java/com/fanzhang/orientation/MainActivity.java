package com.fanzhang.orientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private MyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listener = new MyListener();
        //获取传感器的服务
        mSensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        //得到方向传感器
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //注册传感器监听器
        mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private class MyListener implements SensorEventListener {
        //当精确度变化的时候调用的方法
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
        //当传感器发现数据变化的时候调用的方法
        @Override
        public void onSensorChanged(SensorEvent event) {
            //0=North, 90=East, 180=South, 270=West
            //values[0]: Azimuth, angle between the magnetic north direction and the y-axis, around the z-axis (0 to 359). 0=North, 90=East, 180=South, 270=West
            //values[1]: Pitch, rotation around x-axis (-180 to 180), with positive values when the z-axis moves toward the y-axis.
            //values[2]: Roll, rotation around the y-axis (-90 to 90) increasing as the device moves clockwise.
            float angle = event.values[0];
            System.out.println("angle:"+angle);
        }
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(listener);
        listener = null;
        super.onDestroy();
    }
}
