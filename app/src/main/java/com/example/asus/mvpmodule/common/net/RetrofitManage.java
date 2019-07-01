package com.example.asus.mvpmodule.common.net;

import com.example.asus.mvpmodule.common.mvp.MvpViewImp;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManage {
    private static RetrofitManage sRetrofitManage;

    private RetrofitManage() {
    }
    public static RetrofitManage getInsingl() {
        if (sRetrofitManage == null) {
            sRetrofitManage = new RetrofitManage();
        }
        return sRetrofitManage;
    }

    //获取server对象
    public NetServer getNetServer(String baseurl){
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetServer.class);
    }

    //请求数据
    public  <O extends Observable> void requestData(O t, final int API_TAG, final MvpViewImp mvpViewImp) {
        t.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserable() {
                    @Override
                    protected void doNext(Object value) {
                        mvpViewImp.onSuccess(value, API_TAG);
                    }

                    @Override
                    protected void doError(Throwable e) {
                        mvpViewImp.onError(e);
                    }
                });
    }

}
