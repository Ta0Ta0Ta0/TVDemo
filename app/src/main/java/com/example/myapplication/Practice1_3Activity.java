package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.myapplication.practice.ItemInfo;
import com.example.myapplication.practice.ItemInfoAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.SourceUriType;
import com.google.gson.Gson;

import java.io.IOException;

public class Practice1_3Activity extends AppCompatActivity {

    RecyclerView recyclerView;

    private Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                ItemInfo itemInfo = (ItemInfo) msg.obj;
//                System.out.println("handler测试:"+itemInfo.getProgramSeries().get(0).getName());

                ItemInfoAdapter adapter = new ItemInfoAdapter(itemInfo);
                recyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice13);
        sendRequestWithOkhttp();
        Fresco.initialize(this);
        recyclerView = (RecyclerView)findViewById(R.id.to_show);
        StaggeredGridLayoutManager layoutManager  =
                new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);


    }

    private void sendRequestWithOkhttp() {
        final String url_example = "http://cosepg.t.taipan.jsa.bcs.ottcn.com:8084/ysten-epg/epg/getPsList." +
                "shtml?catgId=2220637&templateId=44800196&pageSize=240&pageNo=1&abilityString=" +
                "{\"abilities\":[\"NxM\",\"timeShift\",\"4K-1|cp-TENCENT\"],\"businessGroupIds\"" +
                ":[],\"deviceGroupIds\":[\"2072\"],\"districtCode\":\"320200\",\"labelIds\":" +
                "[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"],\"userGroupIds\":[\"228\"]" +
                ",\"userLabelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"]}" +
                "&serviceChannelId=\n";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url_example).build();
//                    Log.d("测试1","成功");
                    Response response = client.newCall(request).execute();
//                    Log.d("测试2","成功");
                    String responseData = response.body().string();
//                    Log.d("测试3",responseData);

                    ItemInfo itemInfo = parseJSONWithGSON(responseData);//解析json

                    Message message = new Message();
                    message.what = 0;//标识
                    message.obj = itemInfo;
                    myHandler.sendMessage(message);


                } catch (IOException e) {
                    System.out.println("出错了");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ItemInfo parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        ItemInfo itemInfo = gson.fromJson(responseData, ItemInfo.class);
//        Log.d("测试4","成功");
//        Log.d("name",itemInfo.getProgramSeries().get(0).getName());
        return itemInfo;

    }
}