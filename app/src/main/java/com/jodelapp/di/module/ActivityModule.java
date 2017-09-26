package com.jodelapp.di.module;

import android.app.Activity;
import android.content.Context;
import com.jodelapp.di.scope.ActivityContext;
import com.jodelapp.di.scope.ActivityScope;
import com.jodelapp.features.photos.presentation.UserPhotoListContract;
import com.jodelapp.features.photos.presentation.UserPhotoListPresenter;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.jodelapp.features.photos.usecases.GetUserPhoto;
import com.jodelapp.features.photos.usecases.GetUserPhotoImpl;
import com.jodelapp.features.profile.presentation.UserProfileContract;
import com.jodelapp.features.profile.presentation.UserProfilePresenter;
import com.jodelapp.features.profile.usecases.GetUserProfile;
import com.jodelapp.features.profile.usecases.GetUserProfileImpl;
import com.jodelapp.features.todos.presentation.UserTodoListContract;
import com.jodelapp.features.todos.presentation.UserTodoListPresenter;
import com.jodelapp.features.todos.usecases.GetTodoListByUser;
import com.jodelapp.features.todos.usecases.GetTodoListByUserImpl;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.main.MainActivityContract;
import com.jodelapp.views.activities.main.MainActivityPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    MainActivityContract.Presenter<MainActivityContract.View> provideMainActivityPresenter(MainActivityPresenter<MainActivityContract.View>
                                                               presenter) {
        return presenter;
    }

    @Provides
    UserProfileContract.Presenter<UserProfileContract.View> provideUserProfileViewPresenter(UserProfilePresenter<UserProfileContract.View>
                                                                                                   presenter) {
        return presenter;
    }

    @Provides
    UserPhotoListContract.Presenter<UserPhotoListContract.View> provideUserPhotoListViewPresenter(UserPhotoListPresenter<UserPhotoListContract.View>
                                                                                                 presenter) {
        return presenter;
    }

    @Provides
    UserTodoListContract.Presenter<UserTodoListContract.View> provideUserTodoListViewPresenter(UserTodoListPresenter<UserTodoListContract.View> presenter) {
        return presenter;
    }

    @Provides
    public GetUserProfile provideGetAllAvailableProvidersUserProfile(GetUserProfileImpl usecase) {
        return usecase;
    }

    @Provides
    public GetTodoListByUser provideGetAllAvailableProvidersTodoListByUser(GetTodoListByUserImpl usecase) {
        return usecase;
    }

    @Provides
    public GetUserPhoto provideGetAllAvailableProvidersUserPhoto(GetUserPhotoImpl usecase) {
        return usecase;
    }

}
