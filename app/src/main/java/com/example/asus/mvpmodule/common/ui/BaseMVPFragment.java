package com.example.asus.mvpmodule.common.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.mvpmodule.common.mvp.MvpPresenterImp;
import com.example.asus.mvpmodule.common.mvp.MvpViewImp;

import java.lang.ref.WeakReference;

/**
 *
 * */
public abstract class BaseMVPFragment<P extends MvpPresenterImp,D extends ViewDataBinding> extends Fragment implements View.OnClickListener, MvpViewImp {
    public P mP;
    public D dataBinding;
    private WeakReference<P> mPWeakReference;
    public String TAG ;
    public static final String EXTRA = "EXTRA_BUNDLE";
    private static final int REQUSET_CODE = 123;
    public BaseMVPActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        TAG = getClass().getSimpleName();
        mActivity = (BaseMVPActivity) getActivity();
        mP = getPresenter();
        attachPM();
        initView();
        setListener();
        fillData();
        return dataBinding.getRoot();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

    protected abstract void fillData();

    protected abstract P getPresenter();

    protected abstract void netSuccessResult(Object o,int API_TAG);

    protected abstract void click(View view);

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, "请求错误 ERROR :" + throwable);
    }

    @Override
    public void onSuccess(Object o, int API_TAG) {
        Log.e(TAG, "请求获取数据:" + o);
        netSuccessResult(o,API_TAG);
    }

    public View getFraRootView(){
        return dataBinding.getRoot();
    }

    protected void openActivity(Class c){
        Intent intent = new Intent(mActivity, c);
        startActivity(intent);
    }
    protected void openActivity(Class c,Bundle bundle){
        if(bundle==null)return;
        Intent intent = new Intent(mActivity, c);
        intent.putExtra(EXTRA,bundle);
        startActivity(intent);
    }

    protected void openActivityForResult(Class c){
        Intent intent = new Intent(mActivity, c);
        startActivityForResult(intent,REQUSET_CODE);
    }
    protected void openActivityForResult(Class c,Bundle bundle){
        if(bundle==null)return;
        Intent intent = new Intent(mActivity, c);
        intent.putExtra(EXTRA,bundle);
        startActivityForResult(intent,REQUSET_CODE);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataBinding.unbind();
        detachPM();
    }

}
