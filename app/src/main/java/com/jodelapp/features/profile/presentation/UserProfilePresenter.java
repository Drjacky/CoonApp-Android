package com.jodelapp.features.profile.presentation;

import android.util.Log;
import com.jodelapp.features.profile.usecases.GetUserProfile;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;
import javax.inject.Inject;

public final class UserProfilePresenter <V extends UserProfileContract.View> extends BasePresenter<V> implements UserProfileContract.Presenter<V> {

    //private final UserProfileContract.View view;
    private final GetUserProfile getUserProfile;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserProfilePresenter(/*UserProfileContract.View view,*/
                                GetUserProfile getUserProfile,
                                ThreadTransformer threadTransformer,
                                RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        //this.view = view;
        this.getUserProfile = getUserProfile;
        /*this.threadTransformer = threadTransformer;
        this.disposables = rxDisposableFactory.get();*/
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();

    }

    @Override
    public void getUsersList() { // Get list of users on UserProfileView fragment created.
        disposables.add(getUserProfile.call()
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        getMvpView()::loadUserList,
                        error -> Log.e("UserProfile", error.getMessage())
                ));
    }

/*    @Override
    public void onDetached() {
        disposables.clear();
    }*/
}
