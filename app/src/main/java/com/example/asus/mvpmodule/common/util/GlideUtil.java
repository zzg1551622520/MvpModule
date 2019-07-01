package com.example.asus.mvpmodule.common.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.mvpmodule.common.BaseApplication;

public class GlideUtil {
    public static void loadImage(String imageUrl, ImageView view){
        Glide.with(BaseApplication.getAppContent()).load(imageUrl).into(view);
    }

    /**
     * 占位图
     * */
    public static void loadImage(String imageUrl, ImageView view,int resource){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(resource);
        Glide.with(BaseApplication.getAppContent()).load(imageUrl).apply(requestOptions).into(view);
    }

    //加载圆形图片
    public static void loadCircleImage(String imageUrl, ImageView view){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(BaseApplication.getAppContent()).load(imageUrl).apply(requestOptions).into(view);
    }
    //加载圆形图片  占位图
    public static void loadCircleImage(String imageUrl, ImageView view,int resource){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(resource);
        requestOptions.circleCrop();
        Glide.with(BaseApplication.getAppContent()).load(imageUrl).apply(requestOptions).into(view);
    }
}
