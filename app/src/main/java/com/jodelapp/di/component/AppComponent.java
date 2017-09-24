package com.jodelapp.di.component;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.jodelapp.App;
import com.jodelapp.di.module.AppModule;
import com.jodelapp.data.DataComponent;
import com.jodelapp.data.DataModule;
import com.jodelapp.di.scope.ApplicationContext;
import com.jodelapp.utilities.UtilsComponent;
import com.jodelapp.utilities.UtilsModule;
import org.greenrobot.eventbus.EventBus;
import java.util.Locale;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class,  DataModule.class})
public interface AppComponent extends UtilsComponent, DataComponent {

    void inject(App app);

    @ApplicationContext
    Context exposeAppContext();

    Resources exposeResources();

    Locale exposeLocale();

    EventBus exposeBus();

    Application exposeApplication();

}
