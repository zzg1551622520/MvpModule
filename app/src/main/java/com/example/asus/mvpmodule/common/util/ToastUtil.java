package com.example.asus.mvpmodule.common.util;

import android.util.Log;
import android.widget.Toast;

import com.example.asus.mvpmodule.common.BaseApplication;

public class ToastUtil {
    private static Toast mToast;
    public static void showShortToast(String content){
       if(mToast == null){
           mToast = Toast.makeText(BaseApplication.getAppContent(),content,Toast.LENGTH_SHORT);
       }else{
           mToast.setText(content);
       }
       mToast.show();
    }

    public static void showLongToast(String content){
        if(mToast == null){
            mToast = Toast.makeText(BaseApplication.getAppContent(),content,Toast.LENGTH_LONG);
        }else{
            mToast.setText(content);
        }
        mToast.show();
    }

}
