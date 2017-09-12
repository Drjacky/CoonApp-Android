package com.jodelapp.features.photos.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.jodelapp.features.profile.presentation.UserProfileView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserPhotoListView extends Fragment implements UserPhotoListContract.View {

    private static final String USER_ID = "1";

    @Inject
    UserPhotoListContract.Presenter presenter;

    @Inject
    Context mContext;

    @Inject
    Resources mResources;

    @BindView(R.id.fragment_photos_rcyPhotos)
    RecyclerView mRecyclerViewPhotos;

    private UserPhotoListAdapter mUserPhotoListRecyclerAdapter;
    private List<UserPhotoPresentationModel> photosList = new ArrayList<>();
    private List<UserPhotoAlbumPresentationModel> mAlbumList;
    private GridLayoutManager layoutManager;
    private UserPhotoListComponent scopeGraph;
    private Unbinder unbinder;
    private int firstVisibleItemPosition, visibleItemCount, totalItemCount;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int nextPage = 0; // Containing the value of increased album id, on each page.

    public static UserPhotoListView getInstance() {
        return new UserPhotoListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        setupScopeGraph(App.get(getActivity()).getAppComponent());
        unbinder = ButterKnife.bind(this, view);
        viewInit();
        return view;
    }

    private void viewInit() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2); // Split photo grid to two columns.
        mRecyclerViewPhotos.setLayoutManager(mLayoutManager);
        layoutManager = ((GridLayoutManager) mRecyclerViewPhotos.getLayoutManager());
        mRecyclerViewPhotos.addItemDecoration(new GridRecyclerSpacingItemDecoration(2, ConvertDpToPx(10), true));
        mRecyclerViewPhotos.setItemAnimator(new DefaultItemAnimator());
        mUserPhotoListRecyclerAdapter = new UserPhotoListAdapter(mContext, photosList); // For the first time, before we get the albumId, photoList is null.
        mRecyclerViewPhotos.setAdapter(mUserPhotoListRecyclerAdapter);
        mRecyclerViewPhotos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadMoreItems();
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        UserProfileView userProfileViewFragment = (UserProfileView) getActivity()
                .getSupportFragmentManager()
                .findFragmentByTag("fragment_profile");

        presenter.getAlbumList(userProfileViewFragment == null ? USER_ID : userProfileViewFragment.getSelectedUserId()); // Default userId, if user didn't click on the UserProfileView fragment.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetached();
        unbinder.unbind();
    }

    @Override
    public void loadPhotoList(List<UserPhotoPresentationModel> providers, List<UserPhotoAlbumPresentationModel> albums) {
        mAlbumList = albums; // Keep list of albums for next usages in pagination.
        isLoading = false;

        photosList.addAll(providers);
        mUserPhotoListRecyclerAdapter.notifyDataSetChanged();
    }

    private void loadMoreItems() { // Get the next photos pack for next page.
        isLoading = true;
        nextPage = nextPage + 1;

        new Handler().postDelayed(() -> {
            if(nextPage < mAlbumList.size())
                presenter.getNextPhotos(mAlbumList.get(nextPage).getId());
            else
                isLastPage = true;
        }, 1000);

    }

    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerUserPhotoListComponent.builder()
                .appComponent(appComponent)
                .userPhotoListModule(new UserPhotoListModule(this))
                .build();
        scopeGraph.inject(this);
    }

    private int ConvertDpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mResources.getDisplayMetrics()));
    }

}
