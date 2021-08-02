package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //此处为事件产生的地方
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1_1 = (Button) findViewById(R.id.step1_1);
        Button button1_2 = (Button) findViewById(R.id.step1_2);
        Button button1_3 = (Button) findViewById(R.id.step1_3finish);
        Button button2_1 = (Button) findViewById(R.id.step2_1);
        Button buttonMusic = (Button) findViewById(R.id.music);


        button1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Practice1_1Activity.class);
                startActivity(intent);
            }
        });
        button1_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Practice1_2Activity.class);
                startActivity(intent);
            }
        });
        button1_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Practice1_3Activity.class);
                startActivity(intent);
            }
        });
        button2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Practice2_1Activity.class);
                startActivity(intent);
            }
        });
        button2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Practice2_1Activity.class);
                startActivity(intent);
            }
        });

    }

}