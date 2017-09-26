package com.jodelapp.features.todos.presentation;

import android.util.Log;

import com.jodelapp.features.todos.models.TodoPresentationModel;
import com.jodelapp.features.todos.usecases.GetTodoListByUser;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class UserTodoListPresenter <V extends UserTodoListContract.View> extends BasePresenter<V> implements UserTodoListContract.Presenter<V> {

    private static final String USER_ID = "1";
    private final GetTodoListByUser getTodoListByUser;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserTodoListPresenter(GetTodoListByUser getTodoListByUser,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        this.getTodoListByUser = getTodoListByUser;
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getTodosList() {
        getMvpView().showLoading();
        disposables.add(getTodoListByUser.call(USER_ID)
                .compose(threadTransformer.applySchedulers())
                .subscribe(todoPresentationModels -> {
                    getMvpView().hideLoading();
                    getMvpView().loadToDoList(todoPresentationModels);
                }, throwable -> {
                    getMvpView().hideLoading();
                    Log.e("UserToDo", throwable.getMessage());
                }));
    }

}
