package com.example.asus.mvpmodule.common.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PermissionUtil extends AppCompatActivity {
    private static PermissionUtil sPermissionUtil;
    private final int REQUEST_PERMISSION_CODE = 66;
    private Activity mActivity;
    private PermissionUtil(Activity activity){this.mActivity = activity;};

    public static PermissionUtil getPermissionUtil(Activity activity) {
        if(sPermissionUtil == null){
            sPermissionUtil= new PermissionUtil(activity);
        }
        return sPermissionUtil;
    }

    public void requestPermission(String permission){
        if(ContextCompat.checkSelfPermission(mActivity,permission) != PackageManager.PERMISSION_GRANTED){
            Log.e("PermissionUtil", "ActivityCompat.shouldShowRequestPermissionRationale(mActivity,permission):" + ActivityCompat.shouldShowRequestPermissionRationale(mActivity,permission));
            if(ActivityCompat.shouldShowRequestPermissionRationale(mActivity,permission)){
                Log.e("PermissionUtil", "用户拒绝授权");
            }else{
                ActivityCompat.requestPermissions(mActivity,new String[]{permission},REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.e("PermissionUtil", "成功");
            mPerimssionCallBack.onSuccess(requestCode,grantResults);
        }else{
            Log.e("PermissionUtil", "失败");
            mPerimssionCallBack.onFailed(requestCode,grantResults);
        }
    }

    private PerimssionCallBack mPerimssionCallBack;

    public interface PerimssionCallBack{
        void onSuccess(int requestCode, int[] grantResults);
        void onFailed(int requestCode, int[] grantResults);
    }

    public void setPerimssionCallBack(PerimssionCallBack perimssionCallBack) {
        mPerimssionCallBack = perimssionCallBack;
    }
}
