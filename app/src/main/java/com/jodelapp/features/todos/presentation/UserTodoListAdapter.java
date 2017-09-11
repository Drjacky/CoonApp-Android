package com.jodelapp.features.todos.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.todos.models.TodoPresentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserTodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<TodoPresentationModel> todoDataList = new ArrayList<>();

    public UserTodoListAdapter(List<TodoPresentationModel> todoDataList) {
        this.todoDataList.clear();
        this.todoDataList.addAll(todoDataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToDoItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TodoPresentationModel todoPresentationModel = todoDataList.get(position);
        ((ToDoItemViewHolder) holder).render(todoPresentationModel);
    }


    @Override
    public int getItemCount() {
        return todoDataList.size();
    }


    class ToDoItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_todo)
        TextView tvItemToDo;
        @BindView(R.id.tv_item_status)
        TextView tvItemStatus;

        ToDoItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(TodoPresentationModel todoPresentationModel) {
            tvItemToDo.setText(todoPresentationModel.getTitle());
            tvItemStatus.setText(todoPresentationModel.getStatus());
        }
    }
}
