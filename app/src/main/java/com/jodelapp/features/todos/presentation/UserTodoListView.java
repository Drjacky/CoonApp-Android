package com.jodelapp.features.todos.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jodelapp.App;
import com.jodelapp.di.component.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.todos.models.TodoPresentationModel;
import com.jodelapp.views.activities.base.BaseFragment;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserTodoListView extends BaseFragment implements UserTodoListContract.View {

   /* @Inject
    UserTodoListContract.Presenter presenter;*/
   @Inject
   UserTodoListContract.Presenter<UserTodoListContract.View> mPresenter;

    @BindView(R.id.fragment_todo_rcyUserTodos)
    RecyclerView lsUserToDos;

    private UserTodoListComponent scopeGraph;
    //private Unbinder unbinder;

    public static UserTodoListView getInstance() {
        return new UserTodoListView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todos, container, false);
        //setupScopeGraph(App.get(getActivity()).getAppComponent());
        getActivityComponent().inject(this);
        //unbinder = ButterKnife.bind(this, view);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        initViews();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTodosList();
    }

    @Override
    public void onDestroyView() {
        //super.onDestroyView();
        //mPresenter.onDetached();
        //unbinder.unbind();
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void loadToDoList(List<TodoPresentationModel> toDos) {
        UserTodoListAdapter adapter = new UserTodoListAdapter(toDos);
        lsUserToDos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        lsUserToDos.setLayoutManager(new LinearLayoutManager(getActivity()));
        lsUserToDos.setHasFixedSize(true);
    }


/*    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerUserTodoListComponent.builder()
                .appComponent(appComponent)
                .userTodoListModule(new UserTodoListModule(this))
                .build();
        scopeGraph.inject(this);
    }*/

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
