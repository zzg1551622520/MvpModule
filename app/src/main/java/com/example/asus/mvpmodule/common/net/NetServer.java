package com.example.asus.mvpmodule.common.net;

import com.example.asus.mvpmodule.common.bean.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetServer {
    @GET("meituApi?")
    Observable<News> getNews(@Query("page") int page);
}
