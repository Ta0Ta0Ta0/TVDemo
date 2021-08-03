package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;

import java.io.IOException;

import static java.lang.String.valueOf;

public class MediaPlayer_Demo extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_demo);

        mediaPlayer = new MediaPlayer();
        final String url = "http://anime-music-files.jijidown.com/5b8cab73b02de256ae1aaf42_128.mp3";
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Log.d("播放","播放测试1");
        mediaPlayer.prepareAsync();
        Log.d("播放","播放测试2");
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 装载完毕回调

                mediaPlayer.start();
                Log.d("播放",valueOf(mediaPlayer.isPlaying()));
            }

        });
    }
}