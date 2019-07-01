package com.example.asus.mvpmodule;

import android.util.Log;
import android.view.View;

import com.example.asus.mvpmodule.common.config.API;
import com.example.asus.mvpmodule.common.mvp.MvpModel;
import com.example.asus.mvpmodule.common.mvp.MvpPresenter;
import com.example.asus.mvpmodule.common.ui.BaseMVPActivity;
import com.example.asus.mvpmodule.common.util.FragmentManagerUtil;
import com.example.asus.mvpmodule.databinding.ActivityMainBinding;


public class MainActivity extends BaseMVPActivity<MvpPresenter,ActivityMainBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void fillData() {
    }

    @Override
    protected void netSuccessResult(Object o, int API_TAG) {
        Log.e(TAG, "请求获取数据:" + o);
        Log.e(TAG, "请求API标识:" + API_TAG);
        switch (API_TAG){
            case API.news:

                break;
        }

    }

    @Override
    protected MvpPresenter getPresenter() {
        return new MvpPresenter(new MvpModel(),this);
    }

    @Override
    protected void setListener() {
        dataBinding.tv.setOnClickListener(this);
    }
    @Override
    protected void initView() {
        mTitle.setBarTitle("标题");
        dataBinding.tv.setText("1");
        FragmentManagerUtil fragmentManagerUtils = FragmentManagerUtil.getFragmentManagerUtils(getSupportFragmentManager());
        fragmentManagerUtils.addAndShowFragment(R.id.fra,new EmptyFragment());
    }

    @Override
    protected void click(View view) {
        if(view == dataBinding.tv){

        }
        if(mTitle.isClickLeft(view)){
            finish();
        }
    }

}
