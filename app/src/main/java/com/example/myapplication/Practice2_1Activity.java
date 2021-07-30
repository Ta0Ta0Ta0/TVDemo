package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Practice2_1Activity extends AppCompatActivity {

    ImageView imageView_1;
    ImageView imageView_2;
    ImageView imageView_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice21);
        imageView_1 = findViewById(R.id.pic_1);
        imageView_2 = findViewById(R.id.pic_2);
        imageView_3 = findViewById(R.id.pic_3);
        Log.d("测试1","成功");


        imageView_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.d("焦点获取","1号获取到焦点了");
                } else {

                }
            }
        });
        imageView_2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.d("焦点获取","2号获取到焦点了");
                } else {

                }
            }
        });
        imageView_3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.d("焦点获取","3号获取到焦点了");
                } else {

                }
            }
        });
        Log.d("测试2","成功");



    }
}