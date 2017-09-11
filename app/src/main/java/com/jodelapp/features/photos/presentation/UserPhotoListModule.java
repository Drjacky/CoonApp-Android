package com.jodelapp.features.photos.presentation;


import dagger.Module;
import dagger.Provides;

@Module
public class UserPhotoListModule {

    private final UserPhotoListContract.View view;

    public UserPhotoListModule(UserPhotoListContract.View view) {
        this.view = view;
    }

    @Provides
    public UserPhotoListContract.View provideView() {
        return this.view;
    }

    @Provides
    public UserPhotoListContract.Presenter providePresenter(UserPhotoListPresenter presenter) {
        return presenter;
    }
}
