package com.jodelapp.views.activities.main;

import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;

import javax.inject.Inject;

public final class MainActivityPresenter <V extends MainActivityContract.View> extends BasePresenter<V> implements MainActivityContract.Presenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public MainActivityPresenter(ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

}
