package com.cursonjiang.retrofitdemo.API;

import com.cursonjiang.retrofitdemo.model.gitmodel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by root on 15/6/13.
 */
public interface gitapi {

    /**
     * 使用GET方法请求
     * 响应结果会存储到gitmodel实例中
     *
     * @param user     用户名
     * @param response 响应结果
     */
    @GET("/users/{user}")
    public void getFeed(@Path("user") String user, Callback<gitmodel> response);
}

