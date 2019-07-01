package com.example.asus.mvpmodule.common.mvp;

public interface MvpModelImp<V extends MvpViewImp> {
    void getData(int API_TAG,V v,Object...objects);
    void postData(int API_TAG,V v,Object...objects);
}
