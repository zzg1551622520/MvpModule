package com.example.asus.mvpmodule.common;

import android.app.Application;
import android.content.Context;

import com.example.asus.mvpmodule.common.util.CarshHandleUtil;

/**
 * ASUS
 * */
public class BaseApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
       CarshHandleUtil.getInstance().init(this);
    }

    public static Context getAppContent() {
        return sContext;
    }

}
