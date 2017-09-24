package com.jodelapp.features.todos.presentation;

import android.util.Log;
import com.jodelapp.features.todos.usecases.GetTodoListByUser;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;
import javax.inject.Inject;

public final class UserTodoListPresenter <V extends UserTodoListContract.View> extends BasePresenter<V> implements UserTodoListContract.Presenter<V> {

    private static final String USER_ID = "1";
    //private final UserTodoListContract.View view;
    private final GetTodoListByUser getTodoListByUser;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserTodoListPresenter(/*UserTodoListContract.View view,*/
                                 GetTodoListByUser getTodoListByUser,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        //this.view = view;
        this.getTodoListByUser = getTodoListByUser;
        /*this.threadTransformer = threadTransformer;
        this.disposables = rxDisposableFactory.get();*/
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getTodosList() {
        disposables.add(getTodoListByUser.call(USER_ID)
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        getMvpView()::loadToDoList,
                        error -> Log.e("UserToDo", error.getMessage())
                ));
    }

/*    @Override
    public void onDetached() {
        disposables.clear();
    }*/
}
