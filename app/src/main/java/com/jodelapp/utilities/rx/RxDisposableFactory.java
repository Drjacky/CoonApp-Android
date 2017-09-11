package com.jodelapp.utilities.rx;


import com.jodelapp.utilities.rx.impl.CompositeDisposablesImpl;

import javax.inject.Inject;

public class RxDisposableFactory {

    @Inject
    public RxDisposableFactory() {
    }

    public RxDisposables get() {
        return new CompositeDisposablesImpl();
    }
}
