package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.myapplication.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.valueOf;

public class MediaPlayer_Demo extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceView video;
    private SurfaceHolder holder;

    private SeekBar bar;
    Timer timer,timer1,timer2;
    TimerTask task,task1,task2;
    boolean start = true;

    private boolean isLongPress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_demo);

        bar = (SeekBar)findViewById(R.id.bar);

        video = (SurfaceView)findViewById(R.id.video);
        holder = video.getHolder();
        holder.addCallback(call_holder);



        bar.setOnSeekBarChangeListener(bar_listen);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    int total = mediaPlayer.getDuration();//得到视频总时长
                    bar.setMax(total);//进度条设置最大值
                    int progress  = mediaPlayer.getCurrentPosition();
                    bar.setProgress(progress);//进度条设置当前值
                }
            }
        };

        timer.schedule(task,500,500);


    }

    /**
     * surfaceView的回调函数
     */
    private final Callback call_holder = new Callback() {
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            final String url = "http://vod.bunediy.com/20200522/NzmyjZWU/index.m3u8";
            try {
//                mediaPlayer.setDataSource(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+
//                        getPackageName()+"/"+R.raw.smzh);
                mediaPlayer.setDataSource(url);
            } catch (IOException e) {
                Log.d("视频：",e.getMessage());
                e.printStackTrace();
            }

            mediaPlayer.setDisplay(holder);
            mediaPlayer.prepareAsync();//将资源异步缓存到内存中

            mediaPlayer.setOnPreparedListener((MediaPlayer::start));
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            if(mediaPlayer != null){
                mediaPlayer.stop();
                mediaPlayer.release();
                timer.cancel();
            }
        }
    };

    /**
     * 进度条改变时的监视器
     */
    private final SeekBar.OnSeekBarChangeListener bar_listen = new SeekBar.OnSeekBarChangeListener() {
        /**
         * 进度条发生变化时调用
         * @param seekBar
         * @param progress
         * @param fromUser
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("测试","进度条变化了");
        }

        /**
         * 进度条开始拖动时触发
         * @param seekBar
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("测试onStartTrackingTouch","进度条开始拖动了");
        }

        /**
         * 进度条停止时触发
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d("测试onStopTrackingTouch","进度条停止了");
        }
    };



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if(event.getRepeatCount() == 0){
                    event.startTracking();//跟踪按钮
                    isLongPress = false;
                }else{

                    isLongPress = true;
                }
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d("测试onKeyLongPress","1");
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN){
            Log.d("left长按","1");


            timer1 = new Timer();
            task1 = new TimerTask() {
                @Override
                public void run() {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-mediaPlayer.getDuration()/30);
                }
            };
            timer1.schedule(task1,0,500);

        }else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN){
            Log.d("right长按","1");

            timer2 = new Timer();
            task2 = new TimerTask() {
                @Override
                public void run() {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+mediaPlayer.getDuration()/30);
                }
            };
            timer2.schedule(task2,0,500);


        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("具体按键",valueOf(keyCode == KeyEvent.KEYCODE_DPAD_CENTER)+" "+event.getAction());
        if (isLongPress){
            if(timer1 != null){
                timer1.cancel();
                timer1 = null;
            }else if (timer2 != null){
                timer2.cancel();
                timer2 = null;
            }
            isLongPress = false;
            return true;
        }else{
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.getAction() == KeyEvent.ACTION_UP) {
                if (start) {
                    Log.d("Enter按钮", "暂停");
                    mediaPlayer.pause();
                    start = false;
                } else {
                    Log.d("Enter按钮", "继续");
                    mediaPlayer.start();
                    start = true;

                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d("left按钮", "1");
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);


            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d("right按钮", "1");
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}