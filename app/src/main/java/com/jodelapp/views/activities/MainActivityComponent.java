package com.jodelapp.views.activities;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
