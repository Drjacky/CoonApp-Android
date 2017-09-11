package com.jodelapp.features.todos.presentation;


import dagger.Module;
import dagger.Provides;

@Module
public class UserTodoListModule {

    private final UserTodoListContract.View view;

    public UserTodoListModule(UserTodoListContract.View view) {
        this.view = view;
    }

    @Provides
    public UserTodoListContract.View provideView() {
        return this.view;
    }

    @Provides
    public UserTodoListContract.Presenter providePresenter(UserTodoListPresenter presenter) {
        return presenter;
    }
}
