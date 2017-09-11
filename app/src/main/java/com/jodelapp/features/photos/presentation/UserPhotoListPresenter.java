package com.jodelapp.features.photos.presentation;

import javax.inject.Inject;

public final class UserPhotoListPresenter implements UserPhotoListContract.Presenter {

    private final UserPhotoListContract.View view;

    @Inject
    public UserPhotoListPresenter(UserPhotoListContract.View view) {
        // TODO: 8/13/17 your dependencies inject here
        this.view = view;
    }


    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }


}
