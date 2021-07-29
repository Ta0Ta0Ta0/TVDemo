package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.practice.ItemInfo;
import com.example.myapplication.service.IHttpProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Practice1_1Activity extends AppCompatActivity {

    TextView responseText;
    private Retrofit retrofit;
    private IHttpProgram httpProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice11);
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
                .build();
        httpProgram = retrofit.create(IHttpProgram.class);
        Call<ItemInfo> call = httpProgram.getProgram("getPsList.shtml?catgId=2220637&templateId=44800196&pageSize=240&pageNo=1&abilityString={\"abilities\":[\"NxM\",\"timeShift\",\"4K-1|cp-TENCENT\"],\"businessGroupIds\":[],\"deviceGroupIds\":[\"2072\"],\"districtCode\":\"320200\",\"labelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"],\"userGroupIds\":[\"228\"],\"userLabelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"]}&serviceChannelId=");
        call.enqueue(new Callback<ItemInfo>() {
            @Override
            public void onResponse(Call<ItemInfo> call, Response<ItemInfo> response) {
                Log.d("retrofit测试：",response.body().getCount());
            }

            @Override
            public void onFailure(Call<ItemInfo> call, Throwable t) {
                Log.e("retrofit错误",t.getMessage(),t);
            }
        });
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

//    private void sendRequestWithOkhttp() {
//        final String url_example = "http://cosepg.t.taipan.jsa.bcs.ottcn.com:8084/ysten-epg/epg/getPsList." +
//                "shtml?catgId=2220637&templateId=44800196&pageSize=240&pageNo=1&abilityString=" +
//                "{\"abilities\":[\"NxM\",\"timeShift\",\"4K-1|cp-TENCENT\"],\"businessGroupIds\"" +
//                ":[],\"deviceGroupIds\":[\"2072\"],\"districtCode\":\"320200\",\"labelIds\":" +
//                "[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"],\"userGroupIds\":[\"228\"]" +
//                ",\"userLabelIds\":[\"3251\",\"3252\",\"3253\",\"3254\",\"3259\",\"328\"]}" +
//                "&serviceChannelId=\n";
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url(url_example).build();
//                    Log.d("测试1","成功");
//                    Response response = client.newCall(request).execute();
//                    Log.d("测试2","成功");
//                    String responseData = response.body().string();
//                    Log.d("测试3",responseData);
//                    showResponse(responseData);//自定义函数用于显示数据
//                } catch (IOException e) {
//                    System.out.println("出错了");
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//
//    private void showResponse(final String responseData) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                responseText.setText(responseData);
//            }
//        });
//    }
}