package com.jodelapp;

import android.app.Application;
import android.content.Context;
import com.jodelapp.di.component.AppComponent;
import com.jodelapp.di.component.DaggerAppComponent;
import com.jodelapp.di.module.AppModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponents();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    private void initComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
