package com.example.asus.mvpmodule.common.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.DeviceUtils;
import com.example.asus.mvpmodule.MainActivity;
import com.example.asus.mvpmodule.common.ui.BaseMVPActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarshHandleUtil implements Thread.UncaughtExceptionHandler {
    private static CarshHandleUtil INSTANCE;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!handleException(e) && mDefaultHandler!=null){
            mDefaultHandler.uncaughtException(t,e);
        }else{
            Intent intent = new Intent(mContext,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            BaseMVPActivity.clearActivitys();
            System.exit(0);
        }
    }

    private CarshHandleUtil(){}

    public static CarshHandleUtil getInstance() {
        if(INSTANCE == null){
            INSTANCE = new CarshHandleUtil();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 收集错误信息  上传服务器
        String model = DeviceUtils.getModel();
        String manufacturer = DeviceUtils.getManufacturer();
        String sdkVersionName = DeviceUtils.getSDKVersionName();
        String result = getErrorInfo(ex);

        Log.e("CarshHandleUtil 错误信息", result);
        Log.e("CarshHandleUtil 时间：", time);
        Log.e("CarshHandleUtil 设备型号：", model);
        Log.e("CarshHandleUtil 设备厂商：", manufacturer);
        Log.e("CarshHandleUtil 设备系统版本：" , sdkVersionName);
        return true;
    }

    private String getErrorInfo(Throwable ex){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }

}
