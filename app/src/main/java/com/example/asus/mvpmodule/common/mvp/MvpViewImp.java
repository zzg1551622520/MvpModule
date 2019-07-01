package com.example.asus.mvpmodule.common.mvp;

public interface MvpViewImp<T>  {
    void onError(Throwable throwable);
    void onSuccess(T t,int API_TAG);
}
