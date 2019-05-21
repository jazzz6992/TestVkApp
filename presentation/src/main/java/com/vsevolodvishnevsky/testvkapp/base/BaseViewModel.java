package com.vsevolodvishnevsky.testvkapp.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<R extends BaseRouter> extends ViewModel {

    protected R router;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public abstract void createInject();

    public void attachRouter(R router) {
        this.router = router;
    }

    public void detachRouter() {
        router.releaseActivity();
        router = null;
    }

    public BaseViewModel() {
        createInject();
    }

    public void handleException(Throwable t) {

    }


    public void onResume() {

    }

    public void onPause() {

    }

    public void onStart() {

    }

    public void onStop() {

    }

    @Override
    protected void onCleared() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        detachRouter();
        super.onCleared();
    }
}
