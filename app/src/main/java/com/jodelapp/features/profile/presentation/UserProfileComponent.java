package com.jodelapp.features.profile.presentation;

import com.jodelapp.di.component.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.features.profile.usecases.UserProfileUseCaseModule;
import dagger.Component;

//@ViewScope
//@Component(dependencies = AppComponent.class, modules = {UserProfileModule.class, UserProfileUseCaseModule.class})
public interface UserProfileComponent {

    //void inject(UserProfileView view);

}
