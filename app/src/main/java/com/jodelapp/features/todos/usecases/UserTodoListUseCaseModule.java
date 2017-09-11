package com.jodelapp.features.todos.usecases;


import dagger.Module;
import dagger.Provides;

@Module
public class UserTodoListUseCaseModule {

    @Provides
    public GetTodoListByUser provideGetAllAvailableProviders(GetTodoListByUserImpl usecase) {
        return usecase;
    }
}
