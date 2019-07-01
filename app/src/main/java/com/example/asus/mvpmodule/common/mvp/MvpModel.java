package com.example.asus.mvpmodule.common.mvp;

import android.util.Log;

import com.example.asus.mvpmodule.common.config.API;
import com.example.asus.mvpmodule.common.config.UrlPath;
import com.example.asus.mvpmodule.common.net.RetrofitManage;

public class MvpModel implements MvpModelImp {
    @Override
    public void getData(int API_TAG, MvpViewImp mvpViewImp, Object... objects) {
        switch (API_TAG) {
            case API.news:
                RetrofitManage.getInsingl().requestData(RetrofitManage.getInsingl().getNetServer(UrlPath.BASE_URL).getNews((int)objects[0]),API_TAG,mvpViewImp);
                break;
            default:
                Log.e("API_ERROR", "API标识传入错误");
                break;
        }
    }

    @Override
    public void postData(int API_TAG, MvpViewImp mvpViewImp, Object... objects) {
        switch (API_TAG) {
           case API.news:
                break;
            default:
                Log.e("API_ERROR", "API标识传入错误");
                break;
        }
    }


}
