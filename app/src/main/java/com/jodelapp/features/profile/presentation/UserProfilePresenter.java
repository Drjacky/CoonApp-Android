package com.jodelapp.features.profile.presentation;

import android.util.Log;
import com.jodelapp.features.profile.usecases.GetUserProfile;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import javax.inject.Inject;

public final class UserProfilePresenter implements UserProfileContract.Presenter {

    private final UserProfileContract.View view;
    private final GetUserProfile getUserProfile;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserProfilePresenter(UserProfileContract.View view,
                                GetUserProfile getUserProfile,
                                ThreadTransformer threadTransformer,
                                RxDisposableFactory factory) {
        this.view = view;
        this.getUserProfile = getUserProfile;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
    }

    @Override
    public void onAttached() { // Get list of users on UserProfileView fragment created.
        disposables.add(getUserProfile.call()
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        view::loadUserList,
                        error -> Log.e("UserProfile", error.getMessage())
                ));
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }
}
