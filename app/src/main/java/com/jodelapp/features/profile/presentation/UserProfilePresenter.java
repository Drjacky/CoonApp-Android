package com.jodelapp.features.profile.presentation;

import android.util.Log;

import com.jodelapp.features.profile.models.UserProfilePresentationModel;
import com.jodelapp.features.profile.usecases.GetUserProfile;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class UserProfilePresenter <V extends UserProfileContract.View> extends BasePresenter<V> implements UserProfileContract.Presenter<V> {

    private final GetUserProfile getUserProfile;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserProfilePresenter(GetUserProfile getUserProfile,
                                ThreadTransformer threadTransformer,
                                RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        this.getUserProfile = getUserProfile;
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getUsersList() { // Get list of users on UserProfileView fragment created.
        getMvpView().showLoading();
        disposables.add(getUserProfile.call()
                .compose(threadTransformer.applySchedulers())
                .subscribe(userProfilePresentationModels -> {
                    getMvpView().hideLoading();
                    getMvpView().loadUserList(userProfilePresentationModels);
                }, throwable -> {
                    getMvpView().hideLoading();
                    Log.e("UserProfile", throwable.getMessage());
                }));
    }

}
