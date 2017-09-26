package com.jodelapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.jodelapp.di.scope.ApplicationContext;
import org.greenrobot.eventbus.EventBus;
import java.util.Locale;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    //@Singleton
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Resources provideAppResources() {
        return mApplication.getResources();
    }

    @Provides
    @Singleton
    Locale provideLocale() {
        return Locale.getDefault();
    }

/*    @Provides //Unused
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }*/

}
