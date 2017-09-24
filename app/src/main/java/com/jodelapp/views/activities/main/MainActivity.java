package com.jodelapp.views.activities.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.jodelapp.App;
import com.jodelapp.di.component.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.jodelapp.features.profile.presentation.UserProfileView;
import com.jodelapp.features.todos.presentation.UserTodoListView;
import com.jodelapp.views.activities.base.BaseActivity;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    /*@Inject
    MainActivityContract.Presenter presenter;*/
    @Inject
    MainActivityContract.Presenter<MainActivityContract.View> mPresenter;

    @BindView(R.id.activity_main_btmNavigation)
    public BottomNavigationView btmNavigation;

    //private MainActivityComponent scopeGraph;

/*    @Override
    public void loadToDoPage() {
        btmNavigation.setSelectedItemId(R.id.bottom_navigation_main_activity_action_tasks); // To set bottomnavigation item to UserToDoListView fragment.

        getSupportFragmentManager().beginTransaction()
                .add(R.id.v_container, UserTodoListView.getInstance())
                .commit();
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setupScopeGraph(App.get(this).getAppComponent());
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        initViews();
        mPresenter.onAttach(this);
        //presenter.onCreate(); // The usage of this line replaced by below line.
        btmNavigation.setSelectedItemId(R.id.bottom_navigation_main_activity_action_tasks); // Instead of view.loadToDoPage(); in MainActivityPresenter.
    }

    @Override
    public void onDestroy() {
        //super.onDestroy();
        //presenter.onDestroy();
        mPresenter.onDetach();
        super.onDestroy();
    }

/*    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        scopeGraph.inject(this);
    }*/

    private void initViews() {
        //ButterKnife.bind(this);

        //BottomNavigation
        btmNavigation.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.bottom_navigation_main_activity_action_profile:
                    selectedFragment = UserProfileView.getInstance();
                    transaction.replace(R.id.v_container, selectedFragment, "fragment_profile");
                    //transaction.addToBackStack("fragment_profile");
                    break;
                case R.id.bottom_navigation_main_activity_action_photos:
                    selectedFragment = UserPhotoListView.getInstance();
                    transaction.replace(R.id.v_container, selectedFragment, "fragment_photos");
                    //transaction.addToBackStack("fragment_photos");
                    break;
                case R.id.bottom_navigation_main_activity_action_tasks:
                    selectedFragment = UserTodoListView.getInstance();
                    transaction.replace(R.id.v_container, selectedFragment, "fragment_tasks");
                    //transaction.addToBackStack("fragment_tasks");
                    break;
                default:
                    selectedFragment = UserTodoListView.getInstance();
            }

            transaction.commit();
            getSupportFragmentManager().executePendingTransactions();
            return true;
        });
    }

    @Override
    protected void setUp() {
    }
}
