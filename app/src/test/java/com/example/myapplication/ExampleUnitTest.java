package com.example.myapplication;

import com.example.myapplication.practice.ItemInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void gson_test(){
        Gson gson = new Gson();
        String userJson = "{isDeveloper': false,'name': 'xiaoqiang','age': 26,'email': '578570174@qq.com','programSeries': [{'contentType': null,'psId': '1200914710'},{'contentType': 'vod','psId': '1200913315'}]}";
        User user = gson.fromJson(userJson, User.class);
        List<User.ProgramSeriesDTO> a = user.getProgramSeries();
        System.out.println(a.get(0).getPsId());
    }

}