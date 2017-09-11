package com.jodelapp.features.todos.presentation;


import com.jodelapp.features.todos.models.TodoPresentationModel;

import java.util.List;

public interface UserTodoListContract {

    interface View {

        void loadToDoList(List<TodoPresentationModel> providers);
    }

    interface Presenter {

        void onAttached();

        void onDetached();
    }
}
