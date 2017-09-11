package com.jodelapp.views.activities;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    public MainActivityContract.View provideView() {
        return this.view;
    }

    @Provides
    public MainActivityContract.Presenter providePresenter(MainActivityPresenter presenter) {
        return presenter;
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return ((AppCompatActivity) view).getSupportFragmentManager();
    }
}
