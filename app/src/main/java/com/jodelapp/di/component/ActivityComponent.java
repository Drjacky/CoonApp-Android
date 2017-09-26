package com.jodelapp.di.component;

import com.jodelapp.di.module.ActivityModule;
import com.jodelapp.di.scope.ActivityScope;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.jodelapp.features.profile.presentation.UserProfileView;
import com.jodelapp.features.todos.presentation.UserTodoListView;
import com.jodelapp.views.activities.main.MainActivity;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(UserProfileView fragment);
    void inject(UserPhotoListView fragment);
    void inject(UserTodoListView fragment);
}
