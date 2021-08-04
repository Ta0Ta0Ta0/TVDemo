package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.media.IMusic;
import com.example.myapplication.media.Music;
import com.example.myapplication.media.MusicAdapter;
import com.example.myapplication.media.Song;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static java.lang.String.valueOf;

public class MusicActivity extends AppCompatActivity {

    private Button button_start_pause;
    private Button button_next;
    private Button button_previous;
    private RecyclerView recyclerView;
    Retrofit retrofit;
    MusicAdapter adapter;
    int count = 0;//记录在第几首歌
    int old_count = -1;//与count比较后，判断播放/暂停操作
    String str;
    IMusic iMusic;//retrofit中定义的路由接口
    int sum_song;//获得歌曲总数
    RecyclerView.ViewHolder viewHolder;
    String id;//用于记录歌曲id
    int width;
    int height;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musci);

        Fresco.initialize(this);
        init();
        button_start_pause.setOnClickListener(this::onClick);
        button_next.setOnClickListener(this::onClick);
        button_previous.setOnClickListener(this::onClick);
        button_next.requestFocus();


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);

        //retrofit设置,显示列表
        final String url = "https://anime-music.jijidown.com/";
        retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        iMusic = retrofit.create(IMusic.class);
        httpServiceMusicList();




    }



    //歌曲列表的网络请求
    private void httpServiceMusicList() {


        Observable<Music> observable = iMusic.getMusicList("api/v2/music/search");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Music>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Music music) {
                        Log.d("测试music：",music.getRes().get(1).getAuthor());

                        sum_song = music.getRes().size();
                        adapter = new MusicAdapter(music);
                        recyclerView.setAdapter(adapter);
                        recyclerView.post(() -> {
                            viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
                            viewHolder.itemView.setSelected(true);
//                                width = child.getWidth();
//                                height = child.getHeight();
//                                Log.d("宽高",valueOf(height));
                        });

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("error:",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    //歌曲的网络请求
    private void httpServiceMusic(String id) {
        Log.d("歌曲访问","1");
        Observable<Song> observable = iMusic.getSong(id);
        Log.d("歌曲访问","2");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Song>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Song song) {
                        Log.d("歌曲访问",song.toString());
                        str = song.getRes().getPlay_url();
                        initMediaPlayer(str);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("歌曲访问",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 播放器设置
     * @param str
     */
    void initMediaPlayer(String str){
        mediaPlayer.reset();
        try {
            String str_temp = str.replace("https","http");
            mediaPlayer.setDataSource(str_temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.next:
                Log.d("按钮","next");
                next();
                break;
            case R.id.previous:
                Log.d("按钮","previous");
                previous();
                break;
            case R.id.start_pause:
                Log.d("按钮","start_pause");
                if(count == old_count){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }else{
                        mediaPlayer.start();
                    }
                }else{
                    viewHolder = recyclerView.findViewHolderForAdapterPosition(count);
                    viewHolder.itemView.setSelected(true);
                    id = (String) viewHolder.itemView.getTag(R.id.tag_first);
                    old_count = count;
                    Log.d("按钮",id);
                    httpServiceMusic(id);
                }

                break;

        }
    }

    /**
     * 按钮初始化
     */
    void init(){
        button_start_pause = (Button) findViewById(R.id.start_pause);
        button_next = (Button) findViewById(R.id.next);
        button_previous = (Button) findViewById(R.id.previous);
        recyclerView = (RecyclerView) findViewById(R.id.music_menu);
    }

    /**
     * 下一首歌
     */
    void next(){
        if(count < sum_song-1){
            if (viewHolder != null) {
                viewHolder.itemView.setSelected(false);
            }
            count++;
            viewHolder = recyclerView.findViewHolderForAdapterPosition(count);
            if (viewHolder != null) {
                viewHolder.itemView.setSelected(true);
//            recyclerView.scrollBy(2*width,2*height);
            }else{
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@androidx.annotation.NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        viewHolder = recyclerView.findViewHolderForAdapterPosition(count);
                        if (viewHolder != null) {
                            viewHolder.itemView.setSelected(true);

                        }
                        recyclerView.removeOnScrollListener(this);
                    }
                });
            }
            recyclerView.scrollToPosition(count);

        }

    }

    /**
     * 上一首歌
     */
    void previous(){

        if(count > 0){
            if (viewHolder != null) {
                viewHolder.itemView.setSelected(false);
            }
            count--;
            viewHolder = recyclerView.findViewHolderForAdapterPosition(count);
            if (viewHolder != null) {
                viewHolder.itemView.setSelected(true);
//            recyclerView.scrollBy(2*width,2*height);
            }else{
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@androidx.annotation.NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        viewHolder = recyclerView.findViewHolderForAdapterPosition(count);
                        if (viewHolder != null) {
                            viewHolder.itemView.setSelected(true);
                        }
                        recyclerView.removeOnScrollListener(this);
                    }
                });
            }
            recyclerView.scrollToPosition(count);

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}