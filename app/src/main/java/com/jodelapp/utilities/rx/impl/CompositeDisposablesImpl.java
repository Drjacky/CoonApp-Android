package com.jodelapp.utilities.rx.impl;


import com.jodelapp.utilities.rx.RxDisposables;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public final class CompositeDisposablesImpl implements RxDisposables {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CompositeDisposablesImpl() {
    }

    @Override
    public void add(Disposable subscription) {
        compositeDisposable.add(subscription);
    }


    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
