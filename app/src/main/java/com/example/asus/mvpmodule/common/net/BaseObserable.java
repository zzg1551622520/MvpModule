package com.example.asus.mvpmodule.common.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * */
public abstract class BaseObserable implements Observer {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(Object value) {
        doNext(value);
    }

    @Override
    public void onError(Throwable e) {
        doError(e);
    }

    @Override
    public void onComplete() {
        if(!mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }

    protected abstract void doNext(Object value);

    protected abstract void doError(Throwable e);
}
