package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import com.example.myapplication.practice.RequestWithOkhttp;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.example.myapplication.practice.ItemInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Practice1_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_practice12);
        Uri uri = Uri.parse("http://images.center.bcs.ottcn.com//images/ysten/cps/cmhlj/2021072114/cb0fc4f48f7c48f29ab4ee62f82f6392.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
        sendRequestWithOkhttp();
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
                    Log.d("测试1","成功");
                    Response response = client.newCall(request).execute();
                    Log.d("测试2","成功");
                    String responseData = response.body().string();
                    Log.d("测试3",responseData);
                    parseJSONWithGSON(responseData);//解析json
                    //自定义函数用于显示数据
                } catch (IOException e) {
                    System.out.println("出错了");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        ItemInfo itemInfoList = gson.fromJson(responseData, ItemInfo.class);
        Log.d("测试4","成功");

        Log.d("name",itemInfoList.getProgramSeries().get(0).getName());


    }


}