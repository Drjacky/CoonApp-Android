package com.jodelapp;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Context appContext;

    public AppModule(Application application) {
        appContext = application.getBaseContext();
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return appContext;
    }

    @Provides
    @Singleton
    Resources provideAppResources() {
        return appContext.getResources();
    }

    @Provides
    @Singleton
    Locale provideLocale() {
        return Locale.getDefault();
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
