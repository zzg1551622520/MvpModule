package com.example.asus.mvpmodule.common.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
/**
 * 加载进度view
 * */
public class LoadingView {
    private View contentView;//内容view
    private PopupWindow mLoadingView;
    private int offestX = 0;
    private int offestY = 0;
    private int width = 150;
    private int height = 150;
    private Context mContext;
    private Handler mHandler = new Handler();
    public LoadingView(int layoutId,Context context) {
        this.mContext =context;
        this.contentView = LayoutInflater.from(context).inflate(layoutId, null, false);
        creatPopupWindow();
    }

    public LoadingView(View contentView,Context context) {
        this.mContext = context;
        this.contentView = contentView;
        creatPopupWindow();
    }

    public LoadingView(Context context) {
        this.mContext = context;
        creatPopupWindow();
    }

    private void creatPopupWindow(){
        this.mLoadingView = new PopupWindow();
        mLoadingView.setContentView(contentView);
        mLoadingView.setWidth(width);
        mLoadingView.setHeight(height);
        mLoadingView.setFocusable(false);
        setBackGround(new ColorDrawable());
        setBackgroundAlpha(0.6f);
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    public void showLoading(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingView.showAtLocation(contentView,Gravity.CENTER,offestX,offestY);
            }
        },100);
    }

    public void cancelLoading(){
        mLoadingView.dismiss();
        mLoadingView = null;
    }

    public void setBackGround(Drawable drawable){
        mLoadingView.setBackgroundDrawable(drawable);
    }

    public void setDismissListener(PopupWindow.OnDismissListener dissmissClick){
        mLoadingView.setOnDismissListener(dissmissClick);
    }

    public View getContentView() {
        return contentView;
    }

    public int getOffestX() {
        return offestX;
    }

    public void setOffestX(int offestX) {
        this.offestX = offestX;
    }

    public int getOffestY() {
        return offestY;
    }

    public void setOffestY(int offestY) {
        this.offestY = offestY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
