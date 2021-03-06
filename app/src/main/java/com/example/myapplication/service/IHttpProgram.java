package com.example.myapplication.service;


import com.example.myapplication.practice.ItemInfo;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface IHttpProgram {

    @GET
    Observable<ItemInfo> getProgram(@Url String str);
//    Call<ResponseBody> getProgram(@Query("catgId") int catgid,
//                                  @Query("templateId") int templateId,
//                                  @Query("pageSize") int pageSize,
//                                  @Query("pageNo") int pageNo);

}
