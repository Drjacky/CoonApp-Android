package com.jodelapp.views.activities;

import com.jodelapp.utilities.StringUtils;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public final class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;
    private final RxDisposables disposables;

    @Inject
    public MainActivityPresenter(MainActivityContract.View view,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        this.view = view;
        this.disposables = rxDisposableFactory.get();
    }


    @Override
    public void onCreate() {
        view.loadToDoPage();
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }


}
