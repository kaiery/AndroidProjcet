package com.fanzhang.VideoPlayer;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private SurfaceView sv;
    private MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;
    private SeekBar sb;
    private TextView tv;
    private ProgressBar pb;
    private Timer timer;
    private TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (SurfaceView) findViewById(R.id.sv);
        sb = (SeekBar) findViewById(R.id.sb);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        //sb设置监听器
        sb.setOnSeekBarChangeListener(new sbChangeListener());
        //sv设置监听器
        //sv.setOnTouchListener(new svTouch());


        sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("被创建了");
                try {
                    //  rtmp://192.168.0.112/GreatWall/20151124011750_100042_22813213EnCode.mp4
                    mediaPlayer = new MediaPlayer();
                    //mediaPlayer = MediaPlayer.create(this, Uri.parse(Environment.getExternalStorageDirectory() + "/111.mp4"));
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    //mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
                    mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/1.mp4");
                    //播放视频，必须设置播放的容器，通过SV得到他的控制器holder
                    mediaPlayer.setDisplay(sv.getHolder());
                    //mediaPlayer.prepare();//准备播放同步的
                    //异步准备
                    mediaPlayer.prepareAsync();
                    //设置异步准备监听器
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        //准备完毕
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            pb.setVisibility(View.INVISIBLE);
                            mediaPlayer.start();
                            //视频播放完毕事件
                            mediaPlayer.setOnCompletionListener(new mediaPlayerCompletion());
                            //检查有没有之前播放的位置
                            int currentPosition = sharedPreferences.getInt("currentPosition", 0);
                            mediaPlayer.seekTo(currentPosition);

                            //开始计时
                            timer = new Timer();
                            timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    try {
                                        if(mediaPlayer!=null&& mediaPlayer.isPlaying()){
                                            //视频总长度
                                            int max = mediaPlayer.getDuration();
                                            //视频当前长度
                                            int progress = mediaPlayer.getCurrentPosition();
                                            sb.setMax(max);
                                            sb.setProgress(progress);
                                            //StringBuilder stringBuilder = new StringBuilder();
                                            //android.support.v4.util.TimeUtils.formatDuration(max,stringBuilder);
                                            //System.out.println(stringBuilder.toString());
                                            //tv.setText();
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            };
                            timer.schedule(timerTask,0,1000);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                System.out.println("被改变了");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("被销毁了");
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    //记录当前播放的位置
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putInt("currentPosition",currentPosition);
                    edit.commit();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    //销毁计时器
                    timer.cancel();
                    timerTask.cancel();
                    timer = null;
                    timerTask = null;
                }
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            //设置可见
            sb.setVisibility(View.VISIBLE);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    //睡眠3秒
                    SystemClock.sleep(3000);
                    //主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置隐藏
                            sb.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }.start();
        }
        return super.onTouchEvent(event);
    }



    /**
     * 拖动条设置改变事件监听器
     */
    private class sbChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(mediaPlayer!=null ){
                mediaPlayer.seekTo(sb.getProgress());

            }
        }
    }

    /**
     * 视频播放完毕事件
     */
    private class mediaPlayerCompletion implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("currentPosition",0);
            edit.commit();
        }
    }
}
