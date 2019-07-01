package com.example.asus.mvpmodule.common.mvp;
/**
 * P 层
 * */
public class MvpPresenter<M extends MvpModelImp,V extends MvpViewImp> implements MvpPresenterImp{
    private M mM;
    private V mV;

    public MvpPresenter(M m, V v) {
        mM = m;
        mV = v;
    }


    @Override
    public void getData(int API_TAG, Object... objects) {
        if(mM!=null && mV!=null){
            mM.getData(API_TAG,mV,objects);
        }

    }

    @Override
    public void postData(int API_TAG, Object... objects) {
        if(mM!=null && mV!=null){
            mM.postData(API_TAG,mV,objects);
        }
    }
}
