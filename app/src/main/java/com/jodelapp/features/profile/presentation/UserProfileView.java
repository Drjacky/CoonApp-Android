package com.jodelapp.features.profile.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jodelapp.App;
import com.jodelapp.di.component.AppComponent;
import com.jodelapp.R;
import com.jodelapp.di.scope.ApplicationContext;
import com.jodelapp.features.profile.models.UserProfilePresentationModel;
import com.jodelapp.views.activities.base.BaseFragment;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserProfileView extends BaseFragment implements UserProfileContract.View{ // I would have preferred this name 'ProfileFragment'; and put all these fragments in the 'views.fragments' package, not in the 'features' package.

    /*@Inject
    UserProfileContract.Presenter presenter;*/
    @Inject
    UserProfileContract.Presenter<UserProfileContract.View> mPresenter;

    @Inject
    @ApplicationContext
    Context mContext;

    @BindView(R.id.fragment_profile_rcyUsers)
    RecyclerView rcyUsers;

    @BindView(R.id.fragment_profile_txtSelectedUser_id)
    TextView txtSelectedUserId;

    @BindView(R.id.fragment_profile_txtSelectedUser_name)
    TextView txtSelectedUserName;

    @BindView(R.id.fragment_profile_txtSelectedUser_username)
    TextView txtSelectedUserUsername;

    //private UserProfileComponent scopeGraph;
    //private Unbinder unbinder;
    private String mSelectedUserId;

    public static UserProfileView getInstance() {
        UserProfileView fragment = new UserProfileView();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
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
        mPresenter.getUsersList(); // Get list of users from server.
    }

    @Override
    public void onDestroyView() {
        //super.onDestroyView();
        //presenter.onDetached();
        //unbinder.unbind();
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void loadUserList(List<UserProfilePresentationModel> users) {
        UserProfileAdapter adapter = new UserProfileAdapter(users);
        rcyUsers.setAdapter(adapter);
        adapter.getPositionClicks().subscribe(this::loadSelectedUser); // Set SOME of selected user information. (Id, Name and Username)
        adapter.notifyDataSetChanged();
    }

    private void loadSelectedUser(UserProfilePresentationModel selectedUser) {
        mSelectedUserId = selectedUser.getId();
        txtSelectedUserId.setText(selectedUser.getId());
        txtSelectedUserName.setText(selectedUser.getName());
        txtSelectedUserUsername.setText(selectedUser.getUsername());
    }

    private void initViews() {
        rcyUsers.setLayoutManager(new LinearLayoutManager(mContext));
        rcyUsers.setHasFixedSize(true);
    }

/*    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerUserProfileComponent.builder()
                .appComponent(appComponent)
                .userProfileModule(new UserProfileModule(this))
                .build();
        scopeGraph.inject(this);
    }*/

    public String getSelectedUserId(){ // Keep latest selected user id, for get related albums from server.
        return mSelectedUserId;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

/*    void onBackClick() {
        getBaseActivity().onFragmentDetached(UserProfileView.class.getSimpleName());
    }*/

}
