package com.example.myapplication.media;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface IMusic {

    @GET
    Observable<Music> getMusicList(@Url String str);
    @GET("api/v2/music/{ID}")
    Observable<Song> getSong(@Path("ID") String id);
}
