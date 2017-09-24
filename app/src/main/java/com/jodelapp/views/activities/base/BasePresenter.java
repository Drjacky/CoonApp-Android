package com.jodelapp.views.activities.base;

import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

public class BasePresenter<V extends BaseView> implements IBasePresenter<V> {

    private static final String TAG = "BasePresenter";

    private final ThreadTransformer mThreadTransformer;
    private final RxDisposables mDisposables;

    private V mBaseView;

    @Inject
    public BasePresenter(
            ThreadTransformer threadTransformer,
                         RxDisposableFactory rxDisposableFactory) {
        this.mThreadTransformer = threadTransformer;
        this.mDisposables = rxDisposableFactory.get();
    }

    @Override
    public void onAttach(V mvpView) {
        mBaseView = mvpView;
    }

    @Override
    public void onDetach() {
        mDisposables.clear();
        mBaseView = null;
    }


    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public V getMvpView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public ThreadTransformer getThreadTransformer() {
        return mThreadTransformer;
    }

    public RxDisposables getRxDisposables() {
        return mDisposables;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }
}

