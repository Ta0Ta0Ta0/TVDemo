package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.layoutmanager.CenterGridLayoutManager;
import com.example.myapplication.practice.ItemInfo;
import com.example.myapplication.practice.ItemInfoAdapter;
import com.example.myapplication.service.IHttpProgram;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Practice1_1Activity extends AppCompatActivity {
    private static final String TAG = "Practice1_1Activity";
    TextView responseText;
    private Retrofit retrofit;
    private IHttpProgram httpProgram;
    private RecyclerView recyclerView;
    private ItemInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice13);
        Fresco.initialize(this);
        recyclerView = (RecyclerView)findViewById(R.id.to_show);
        StaggeredGridLayoutManager layoutManager =
                new CenterGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        responseText = (TextView)findViewById(R.id.response_txt);
//        sendRequestWithOkhttp();
        sendRequestWithRetrofit();
    }

    private void sendRequestWithRetrofit() {
        final String url_example ="http://cosepg.t.taipan.jsa.bcs.ottcn.com:8084/ysten-epg/epg/";

        //TODO:日志管理待做

        retrofit = new Retrofit.Builder()
                .baseUrl(url_example)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        httpProgram = retrofit.create(IHttpProgram.class);

        Observable<ItemInfo> call = httpProgram.getProgram("getPsList.shtml?catgId=2220637&templateId=44800196&pageSize=240&pageNo=1&abilityString={\"abilities\":[\"NxM\",\"timeShift\",\"4K-1|cp-TENCENT\"],\"businessGroupIds\":[],\"deviceGroupIds\":[\"2072\"],\"districtCode\":\"320200\",\"labelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"],\"userGroupIds\":[\"228\"],\"userLabelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"]}&serviceChannelId=");

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ItemInfo itemInfo) {
//                        Log.d("retrofit测试：",response.body().getCount());
                            adapter = new ItemInfoAdapter(itemInfo);
                            recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //        call.enqueue(new Callback<ItemInfo>() {
//            @Override
//            public void onResponse(Call<ItemInfo> call, Response<ItemInfo> response) {
//                Log.d("retrofit测试：",response.body().getCount());
//                adapter = new ItemInfoAdapter(response.body());
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<ItemInfo> call, Throwable t) {
//                Log.e("retrofit错误",t.getMessage(),t);
//            }
//        });
//        Call<ResponseBody> call = httpProgram.getProgram("getPsList.shtml?catgId=2220637&templateId=44800196&pageSize=240&pageNo=1&abilityString={\"abilities\":[\"NxM\",\"timeShift\",\"4K-1|cp-TENCENT\"],\"businessGroupIds\":[],\"deviceGroupIds\":[\"2072\"],\"districtCode\":\"320200\",\"labelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"],\"userGroupIds\":[\"228\"],\"userLabelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"]}&serviceChannelId=");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Log.d("测试：", response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("失败：", t.getMessage());
//            }
//        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_0){
            View focus = getWindow().getDecorView().findFocus();
            Log.d(TAG, "onKeyDown: focus = " + focus);
        }
        return super.onKeyDown(keyCode, event);
    }
}