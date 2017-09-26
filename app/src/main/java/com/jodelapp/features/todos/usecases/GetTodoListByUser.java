package com.jodelapp.features.todos.usecases;

import android.support.annotation.NonNull;
import com.jodelapp.features.todos.models.TodoPresentationModel;
import java.util.List;
import io.reactivex.Single;

public interface GetTodoListByUser {

    Single<List<TodoPresentationModel>> call(@NonNull String userId);
}
