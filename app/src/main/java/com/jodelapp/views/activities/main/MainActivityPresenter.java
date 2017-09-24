package com.jodelapp.views.activities.main;

import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;

import javax.inject.Inject;

public final class MainActivityPresenter <V extends MainActivityContract.View> extends BasePresenter<V> implements MainActivityContract.Presenter<V> {

    //private final MainActivityContract.View view;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public MainActivityPresenter(/*MainActivityContract.View view,*/
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        //this.view = view;
        /*this.view = getMvpView();
        this.disposables = rxDisposableFactory.get();*/
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

/*    @Override
    public void onCreate() {
        //view.loadToDoPage();//Replaced by 'btmNavigation.setSelectedItemId(R.id.bottom_navigation_main_activity_action_tasks);' in 'MainActivity'
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }*/
}
