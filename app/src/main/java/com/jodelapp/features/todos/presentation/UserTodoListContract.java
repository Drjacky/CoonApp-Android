package com.jodelapp.features.todos.presentation;

import com.jodelapp.features.todos.models.TodoPresentationModel;
import com.jodelapp.views.activities.base.BaseView;
import com.jodelapp.views.activities.base.IBasePresenter;

import java.util.List;

public interface UserTodoListContract {

    interface View extends BaseView {

        void loadToDoList(List<TodoPresentationModel> providers);

    }

    interface Presenter <V extends UserTodoListContract.View> extends IBasePresenter<V> {

        void getTodosList();

        //void onDetached();
    }
}
