package com.example.asus.mvpmodule.common.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.example.asus.mvpmodule.R;
import com.example.asus.mvpmodule.common.mvp.MvpPresenterImp;
import com.example.asus.mvpmodule.common.mvp.MvpViewImp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * */
public abstract class BaseMVPActivity<P extends MvpPresenterImp,D extends ViewDataBinding>
        extends AppCompatActivity
        implements View.OnClickListener , MvpViewImp {
    public String TAG ;
    public static final String EXTRA = "EXTRA_BUNDLE";
    private static final int REQUSET_CODE = 123;

    public P mP;
    public D dataBinding;
    private WeakReference<P> mPWeakReference;

    private static List<Activity> mActivityList = new ArrayList<>();
    public TitleBarView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarColor(this,getResources().getColor(R.color.title_bg_color));
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mTitle = new TitleBarView(this);
        TAG = getClass().getSimpleName();
        mActivityList.add(this);
        mP = getPresenter();
        attachPM();
        initView();
        setListener();
        fillData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

    protected abstract void fillData();

    protected abstract void netSuccessResult(Object o,int API_TAG);

    protected abstract P getPresenter();

    protected abstract void click(View view);

    protected  void activityResult(int resultCode, Intent data){

    }

    protected void showLoading(){

    }

    protected void cancelLoading(){

    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, "请求错误 ERROR :" + throwable);
    }

    @Override
    public void onSuccess(Object o, int API_TAG) {
        Log.e(TAG, "请求获取数据:" + o);
        netSuccessResult(o,API_TAG);
    }

    @Override
    public void onClick(View v) {
        click(v);
    }
    private void attachPM(){
        mPWeakReference = new WeakReference<>(mP);
        mP = mPWeakReference.get();
    }

    private void detachPM(){
        if(mPWeakReference!=null)mPWeakReference.clear();
    }

    public static void clearActivitys(){
        for (int i = 0; i < mActivityList.size(); i++) {
            mActivityList.get(i).finish();
        }
    }

    protected void openActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
    protected void openActivity(Class c,Bundle bundle){
        if(bundle==null)return;
        Intent intent = new Intent(this, c);
        intent.putExtra(EXTRA,bundle);
        startActivity(intent);
    }
    protected void openActivityForResult(Class c){
        Intent intent = new Intent(this, c);
        startActivityForResult(intent,REQUSET_CODE);
    }
    protected void openActivityForResult(Class c,Bundle bundle){
        if(bundle==null)return;
        Intent intent = new Intent(this, c);
        intent.putExtra(EXTRA,bundle);
        startActivityForResult(intent,REQUSET_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBinding.unbind();
        mTitle.destory();
        mActivityList.remove(this);
        detachPM();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUSET_CODE){
            activityResult(resultCode,data);
        }
    }

}
